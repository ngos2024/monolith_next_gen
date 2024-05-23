package org.afrinnov.apppoc.hr.rest;

import lombok.RequiredArgsConstructor;
import org.afrinnov.apppoc.hr.dto.EmployeeLite;
import org.afrinnov.apppoc.hr.service.EmployeesService;
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
