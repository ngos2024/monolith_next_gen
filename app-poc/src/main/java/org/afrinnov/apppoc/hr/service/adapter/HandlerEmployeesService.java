package org.afrinnov.apppoc.hr.service.adapter;

import lombok.RequiredArgsConstructor;
import org.afrinnov.apppoc.cra.spi.CraApiService;
import org.afrinnov.apppoc.employee.spi.EmployeeApiService;
import org.afrinnov.apppoc.employee.spi.model.EmployeeInfo;
import org.afrinnov.apppoc.holiday.spi.HolidayApiService;
import org.afrinnov.apppoc.hr.dto.EmployeeLite;
import org.afrinnov.apppoc.hr.service.EmployeesService;
import org.afrinnov.apppoc.pay.spi.PayApiService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Month;
import java.time.MonthDay;
import java.time.Year;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class HandlerEmployeesService implements EmployeesService {
    private final EmployeeApiService employeeApiService;
    private final CraApiService craApiService;
    private final PayApiService payApiService;
    private final HolidayApiService holidayApiService;

    @Override
    public List<EmployeeLite> getEmployeesLite() {
        int currentYear = Year.now().getValue();
        Month month = MonthDay.now().getMonth();
        return employeeApiService.getEmployees().stream()
                .map(employee -> mapEmployeeToDto(currentYear, month, employee))
                .toList();
    }

    private EmployeeLite mapEmployeeToDto(int currentYear, Month month, EmployeeInfo employee) {
        return EmployeeLite.builder()
                .employee(employee)
                .paid(payApiService.isPaid(employee.getMatricule(), currentYear, month))
                .craEdited(craApiService.isEdited(employee.getMatricule(), currentYear, month))
                .countHoliday(holidayApiService.count(employee.getMatricule(), currentYear, month))
                .build();
    }
}
