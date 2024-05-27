package org.afrinnov.onelogic.hr.holiday.service;

import org.afrinnov.onelogic.hr.holiday.rest.input.HolidayConfirm;
import org.afrinnov.onelogic.hr.holiday.rest.input.HolidayCreate;

public interface HolidayService {
    void create(HolidayCreate holidayCreate);

    void accept(HolidayConfirm confirm);

    void reject(HolidayConfirm confirm);
}
