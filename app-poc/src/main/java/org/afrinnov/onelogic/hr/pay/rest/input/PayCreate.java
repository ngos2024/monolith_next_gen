package org.afrinnov.onelogic.hr.pay.rest.input;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.afrinnov.onelogic.hr.pay.entity.PMonth;

public record PayCreate(@NotEmpty String matricule,
                        @NotNull PMonth month) {
}
