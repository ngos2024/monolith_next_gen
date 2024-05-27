package org.afrinnov.onelogic.hr.employee.spi.model;

/**
 * Projection for {@link org.afrinnov.onelogic.hr.employee.entity.EmployeeEntity}
 */
public interface EmployeeInfo {
    String getMatricule();

    String getName();

    String getCountry();

    String getEmail();
}