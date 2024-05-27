package org.afrinnov.onelogic.hr.pay.service;

import org.afrinnov.onelogic.hr.pay.rest.input.PayCreate;

public interface PayService {
    void create(PayCreate create);

    void validate();
}
