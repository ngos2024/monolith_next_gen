package org.afrinnov.onelogic.hr.employee.dto;

public record EmployeeId(String id) {
    public static EmployeeId of(String id) {
        return new EmployeeId(id);
    }
}
