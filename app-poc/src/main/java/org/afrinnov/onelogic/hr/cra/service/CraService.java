package org.afrinnov.onelogic.hr.cra.service;

import org.afrinnov.onelogic.hr.cra.rest.input.MonthlyCreate;
import org.afrinnov.onelogic.hr.cra.rest.input.MonthlyEdit;

public interface CraService {
    void create(MonthlyCreate monthlyCreate);

    void edit(MonthlyEdit monthlyEdit);
}
