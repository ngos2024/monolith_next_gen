package org.afrinnov.apppoc.cra.rest.input;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.afrinnov.apppoc.cra.entity.MMonth;

import java.time.Month;

public record MonthlyEdit(@NotEmpty String matricule,
                          @NotNull MMonth month) {
}
