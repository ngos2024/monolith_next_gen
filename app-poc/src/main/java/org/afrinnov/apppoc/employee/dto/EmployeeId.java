package org.afrinnov.apppoc.employee.dto;

public record EmployeeId(String id) {
    public static EmployeeId of(String id) {
        return new EmployeeId(id);
    }
}
