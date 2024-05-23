package org.afrinnov.apppoc.cra.rest.input;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.afrinnov.apppoc.cra.entity.MMonth;

@Getter
@Setter
public class MonthlyCreate {
    @NotEmpty
    private String matricule;
    @NotNull
    private MMonth month;
}
