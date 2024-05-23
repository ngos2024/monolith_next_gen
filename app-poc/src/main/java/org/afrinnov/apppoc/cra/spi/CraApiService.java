package org.afrinnov.apppoc.cra.spi;

import java.time.Month;

public interface CraApiService {
    boolean isEdited(String matricule, int currentYear, Month month);
}
