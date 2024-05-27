package org.afrinnov.onelogic.hr.cra.spi;

import java.time.Month;

public interface CraApiService {
    boolean isEdited(String matricule, int currentYear, Month month);
}
