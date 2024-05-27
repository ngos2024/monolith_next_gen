package org.afrinnov.onelogic.hr.holiday.spi;

import java.time.Month;

public interface HolidayApiService {
    long count(String matricule, int currentYear, Month month);
}
