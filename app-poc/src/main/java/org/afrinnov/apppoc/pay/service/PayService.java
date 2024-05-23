package org.afrinnov.apppoc.pay.service;

import org.afrinnov.apppoc.pay.rest.input.PayCreate;

public interface PayService {
    void create(PayCreate create);

    void validate();
}
