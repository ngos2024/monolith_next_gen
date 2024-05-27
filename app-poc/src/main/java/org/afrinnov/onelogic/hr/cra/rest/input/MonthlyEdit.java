package org.afrinnov.onelogic.hr.cra.rest.input;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.afrinnov.onelogic.hr.cra.entity.MMonth;

public record MonthlyEdit(@NotEmpty String matricule,
                          @NotNull MMonth month) {
}
