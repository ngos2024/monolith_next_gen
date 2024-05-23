package org.afrinnov.apppoc.holiday.rest.input;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record HolidayCreate(@NotEmpty String matricule, @NotNull @FutureOrPresent LocalDate date) {
}
