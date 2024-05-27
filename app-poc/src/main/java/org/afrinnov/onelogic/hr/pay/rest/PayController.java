package org.afrinnov.onelogic.hr.pay.rest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.afrinnov.onelogic.hr.pay.rest.input.PayCreate;
import org.afrinnov.onelogic.hr.pay.service.PayService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pay")
@RequiredArgsConstructor
public class PayController {
    private final PayService payService;

    @PostMapping
    public void create(@RequestBody @Valid PayCreate create) {
        payService.create(create);
    }

    @PostMapping("/validate")
    public void validate() {
        payService.validate();
    }
}
