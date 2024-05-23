package org.afrinnov.apppoc.employee.service;

import org.afrinnov.apppoc.employee.dto.EmployeeId;
import org.afrinnov.apppoc.employee.rest.input.EmployeeCreate;
import org.afrinnov.apppoc.employee.rest.input.EmployeeEdit;
import org.afrinnov.apppoc.employee.spi.model.EmployeeInfo;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    EmployeeId create(EmployeeCreate employee);

    EmployeeId edit(EmployeeId employeeId, EmployeeEdit employee);

    List<EmployeeInfo> getEmployees();

    Optional<EmployeeInfo> getEmployee(String employeeId);
}
