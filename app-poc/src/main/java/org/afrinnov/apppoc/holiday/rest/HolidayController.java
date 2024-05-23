package org.afrinnov.apppoc.holiday.rest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.afrinnov.apppoc.holiday.rest.input.HolidayConfirm;
import org.afrinnov.apppoc.holiday.rest.input.HolidayCreate;
import org.afrinnov.apppoc.holiday.service.HolidayService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/holiday")
@RequiredArgsConstructor
public class HolidayController {
    private final HolidayService holidayService;

    @PostMapping
    public void create(@RequestBody @Valid HolidayCreate holidayCreate) {
        holidayService.create(holidayCreate);
    }

    @PostMapping("/accepted")
    public void accept(@RequestBody @Valid HolidayConfirm confirm) {
        holidayService.accept(confirm);
    }

    @PostMapping("/reject")
    public void reject(@RequestBody @Valid HolidayConfirm confirm) {
        holidayService.reject(confirm);
    }
}
