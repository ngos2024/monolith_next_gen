package org.afrinnov.apppoc.holiday.service;

import org.afrinnov.apppoc.holiday.rest.input.HolidayConfirm;
import org.afrinnov.apppoc.holiday.rest.input.HolidayCreate;

public interface HolidayService {
    void create(HolidayCreate holidayCreate);

    void accept(HolidayConfirm confirm);

    void reject(HolidayConfirm confirm);
}
