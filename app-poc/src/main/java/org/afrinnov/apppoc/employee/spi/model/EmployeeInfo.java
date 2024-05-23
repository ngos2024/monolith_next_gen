package org.afrinnov.apppoc.employee.spi.model;

/**
 * Projection for {@link org.afrinnov.apppoc.employee.entity.EmployeeEntity}
 */
public interface EmployeeInfo {
    String getMatricule();

    String getName();

    String getCountry();

    String getEmail();
}