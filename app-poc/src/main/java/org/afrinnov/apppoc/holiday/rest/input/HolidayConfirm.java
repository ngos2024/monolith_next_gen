package org.afrinnov.apppoc.holiday.rest.input;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record HolidayConfirm(@NotEmpty String matricule, @NotNull LocalDate date) {
}
