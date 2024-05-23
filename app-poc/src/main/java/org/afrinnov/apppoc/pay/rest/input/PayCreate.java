package org.afrinnov.apppoc.pay.rest.input;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.afrinnov.apppoc.pay.entity.PMonth;

public record PayCreate(@NotEmpty String matricule,
                        @NotNull PMonth month) {
}
