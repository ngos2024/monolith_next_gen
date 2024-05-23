package org.afrinnov.apppoc.employee.spi;

import org.afrinnov.apppoc.employee.spi.model.EmployeeInfo;

import java.util.List;
import java.util.Optional;

public interface EmployeeApiService {
    void check(String matricule);

    Optional<EmployeeInfo> getEmployee(String matricule);

    List<EmployeeInfo> getEmployees();
}
