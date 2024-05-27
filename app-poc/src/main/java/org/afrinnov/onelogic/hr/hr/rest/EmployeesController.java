package org.afrinnov.onelogic.hr.hr.rest;

import lombok.RequiredArgsConstructor;
import org.afrinnov.onelogic.hr.hr.dto.EmployeeLite;
import org.afrinnov.onelogic.hr.hr.service.EmployeesService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/hr/employees")
@RequiredArgsConstructor
public class EmployeesController {
    private final EmployeesService employeesService;

    @GetMapping
    public List<EmployeeLite> getEmployeeLite() {
        return employeesService.getEmployeesLite();
    }
}
