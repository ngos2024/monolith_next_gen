package org.afrinnov.apppoc.cra.service;

import org.afrinnov.apppoc.cra.rest.input.MonthlyCreate;
import org.afrinnov.apppoc.cra.rest.input.MonthlyEdit;

public interface CraService {
    void create(MonthlyCreate monthlyCreate);

    void edit(MonthlyEdit monthlyEdit);
}
