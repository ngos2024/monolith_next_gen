package org.afrinnov.onelogic.hr.employee.rest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.afrinnov.onelogic.hr.employee.dto.EmployeeId;
import org.afrinnov.onelogic.hr.employee.rest.input.EmployeeCreate;
import org.afrinnov.onelogic.hr.employee.rest.input.EmployeeEdit;
import org.afrinnov.onelogic.hr.employee.service.EmployeeService;
import org.afrinnov.onelogic.hr.employee.spi.model.EmployeeInfo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @PostMapping
    public EmployeeId registry(@RequestBody @Valid EmployeeCreate employee) {
        return employeeService.create(employee);
    }

    @PutMapping("/{employeeId}")
    public EmployeeId registry(@PathVariable String employeeId, @RequestBody @Valid EmployeeEdit employee) {
        return employeeService.edit(EmployeeId.of(employeeId), employee);
    }

    @GetMapping
    public List<EmployeeInfo> getEmployees() {
        return employeeService.getEmployees();
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<EmployeeInfo> getEmployees(@PathVariable String employeeId) {
        return ResponseEntity.of(employeeService.getEmployee(employeeId));
    }
}
