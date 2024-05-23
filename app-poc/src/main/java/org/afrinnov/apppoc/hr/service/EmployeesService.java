package org.afrinnov.apppoc.hr.service;

import org.afrinnov.apppoc.hr.dto.EmployeeLite;

import java.util.List;

public interface EmployeesService {
    List<EmployeeLite> getEmployeesLite();
}
