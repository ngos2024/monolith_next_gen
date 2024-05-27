package org.afrinnov.onelogic.hr.employee.spi;

import org.afrinnov.onelogic.hr.employee.spi.model.EmployeeInfo;

import java.util.List;
import java.util.Optional;

public interface EmployeeApiService {
    void check(String matricule);

    Optional<EmployeeInfo> getEmployee(String matricule);

    List<EmployeeInfo> getEmployees();
}
