package org.afrinnov.onelogic.hr.employee.rest.input;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public record EmployeeCreate(@NotEmpty String name, @NotEmpty String country, @NotEmpty @Email String email) {
}
