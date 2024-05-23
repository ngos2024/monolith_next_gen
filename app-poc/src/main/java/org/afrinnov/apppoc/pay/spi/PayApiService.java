package org.afrinnov.apppoc.pay.spi;

import java.time.Month;

public interface PayApiService {
    boolean isPaid(String matricule, int currentYear, Month month);
}
