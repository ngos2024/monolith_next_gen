package org.afrinnov.onelogic.hr.cra.rest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.afrinnov.onelogic.hr.cra.rest.input.MonthlyCreate;
import org.afrinnov.onelogic.hr.cra.rest.input.MonthlyEdit;
import org.afrinnov.onelogic.hr.cra.service.CraService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cra/saisir")
@RequiredArgsConstructor
public class CraController {
    private final CraService craService;

    @PostMapping
    public void create(@RequestBody @Valid MonthlyCreate monthlyCreate) {
        craService.create(monthlyCreate);
    }

    @PutMapping
    public void edit(@RequestBody @Valid MonthlyEdit monthlyEdit) {
        craService.edit(monthlyEdit);
    }




}
