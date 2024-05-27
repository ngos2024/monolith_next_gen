package org.afrinnov.onelogic.hr.cra.rest;

import lombok.RequiredArgsConstructor;
import org.afrinnov.onelogic.hr.cra.service.CraAlertService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cra/alert")
@RequiredArgsConstructor
public class CraAlertController {
    private final CraAlertService craAlertService;

    @PostMapping
    public void alert() {
        craAlertService.alert();
    }




}
