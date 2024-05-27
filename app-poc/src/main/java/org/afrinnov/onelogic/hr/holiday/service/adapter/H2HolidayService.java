package org.afrinnov.onelogic.hr.holiday.service.adapter;

import lombok.RequiredArgsConstructor;
import org.afrinnov.onelogic.hr.employee.spi.EmployeeApiService;
import org.afrinnov.onelogic.hr.employee.spi.model.EmployeeInfo;
import org.afrinnov.onelogic.hr.holiday.entity.HolidayEntity;
import org.afrinnov.onelogic.hr.holiday.repository.HolidayRepository;
import org.afrinnov.onelogic.hr.holiday.rest.input.HolidayConfirm;
import org.afrinnov.onelogic.hr.holiday.rest.input.HolidayCreate;
import org.afrinnov.onelogic.hr.holiday.service.HolidayService;
import org.afrinnov.onelogic.hr.holiday.spi.HolidayApiService;
import org.afrinnov.onelogic.hr.notify.MailMessage;
import org.afrinnov.onelogic.hr.notify.Nature;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
@Transactional
public class H2HolidayService implements HolidayService, HolidayApiService {

    private final HolidayRepository holidayRepository;
    private final EmployeeApiService employeeApiService;
    private final ApplicationEventPublisher events;

    @Override
    public void create(HolidayCreate holidayCreate) {
        employeeApiService.check(holidayCreate.matricule());
        HolidayEntity entity = HolidayEntity.builder()
                .date(holidayCreate.date())
                .matricule(holidayCreate.matricule())
                .build();
        holidayRepository.save(entity);
    }

    @Override
    public void accept(HolidayConfirm confirm) {
        employeeApiService.check(confirm.matricule());
        HolidayEntity holiday = holidayRepository.findByMatriculeAndDate(confirm.matricule(), confirm.date())
                .orElseThrow(getIllegalArgumentExceptionSupplier(confirm));
        if (holiday.isAccepted()) {
            throw new IllegalArgumentException(String.format("Le congé de [%s] du jour %s est déjà validé", confirm.matricule(), confirm.date()));
        }
        holiday.setAccepted(true);
        Optional<EmployeeInfo> employeeInfo = employeeApiService.getEmployee(confirm.matricule());
        if (employeeInfo.isPresent()) {
            EmployeeInfo employee = employeeInfo.get();
            MailMessage message = MailMessage.builder()
                    .matricule(confirm.matricule())
                    .email(employee.getEmail())
                    .name(employee.getName())
                    .nature(Nature.ALERT_HOLIDAY_CONFIRMED)
                    .data(Map.of("date", confirm.date()))
                    .build();
            events.publishEvent(message);
        }
    }

    @Override
    public void reject(HolidayConfirm confirm) {
        employeeApiService.check(confirm.matricule());
        HolidayEntity holiday = holidayRepository.findByMatriculeAndDate(confirm.matricule(), confirm.date())
                .orElseThrow(getIllegalArgumentExceptionSupplier(confirm));
        if (!holiday.isAccepted()) {
            throw new IllegalArgumentException(String.format("Le congé de [%s] du jour %s est déjà rejeté", confirm.matricule(), confirm.date()));
        }
        holiday.setAccepted(false);
        Optional<EmployeeInfo> employeeInfo = employeeApiService.getEmployee(confirm.matricule());
        if (employeeInfo.isPresent()) {
            EmployeeInfo employee = employeeInfo.get();
            MailMessage message = MailMessage.builder()
                    .matricule(confirm.matricule())
                    .email(employee.getEmail())
                    .name(employee.getName())
                    .nature(Nature.ALERT_HOLIDAY_REJECT)
                    .data(Map.of("date", confirm.date()))
                    .build();
            events.publishEvent(message);
        }
    }


    private static Supplier<IllegalArgumentException> getIllegalArgumentExceptionSupplier(HolidayConfirm confirm) {
        return () -> new IllegalArgumentException(String.format("Il n y a pas de congé prévu pour [%s] ce jour %s", confirm.matricule(), confirm.date()));
    }

    @Override
    public long count(String matricule, int currentYear, Month month) {
        YearMonth yearMonth=YearMonth.of(currentYear, month);
        LocalDate startDate = yearMonth.atDay(1);
        LocalDate endDate = yearMonth.atEndOfMonth();
        return holidayRepository.countByMatriculeAndAcceptedTrueAndDateBetween(matricule, startDate, endDate);
    }
}
