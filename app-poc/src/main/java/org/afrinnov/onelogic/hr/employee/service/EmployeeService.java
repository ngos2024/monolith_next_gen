package org.afrinnov.onelogic.hr.employee.service;

import org.afrinnov.onelogic.hr.employee.dto.EmployeeId;
import org.afrinnov.onelogic.hr.employee.rest.input.EmployeeCreate;
import org.afrinnov.onelogic.hr.employee.rest.input.EmployeeEdit;
import org.afrinnov.onelogic.hr.employee.spi.model.EmployeeInfo;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    EmployeeId create(EmployeeCreate employee);

    EmployeeId edit(EmployeeId employeeId, EmployeeEdit employee);

    List<EmployeeInfo> getEmployees();

    Optional<EmployeeInfo> getEmployee(String employeeId);
}
