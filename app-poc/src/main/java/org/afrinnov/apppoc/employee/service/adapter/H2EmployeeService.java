package org.afrinnov.apppoc.employee.service.adapter;

import lombok.RequiredArgsConstructor;
import org.afrinnov.apppoc.employee.dto.EmployeeId;
import org.afrinnov.apppoc.employee.entity.EmployeeEntity;
import org.afrinnov.apppoc.employee.repository.EmployeeRepository;
import org.afrinnov.apppoc.employee.rest.input.EmployeeCreate;
import org.afrinnov.apppoc.employee.rest.input.EmployeeEdit;
import org.afrinnov.apppoc.employee.service.EmployeeService;
import org.afrinnov.apppoc.employee.spi.EmployeeApiService;
import org.afrinnov.apppoc.employee.spi.model.EmployeeInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.apache.commons.lang3.RandomStringUtils.randomNumeric;

@Service
@RequiredArgsConstructor
@Transactional
public class H2EmployeeService implements EmployeeService, EmployeeApiService {
    private final EmployeeRepository employeeRepository;

    @Override
    public EmployeeId create(EmployeeCreate employee) {
        String matricule = generateMatricule();
        EmployeeEntity entity = EmployeeEntity.builder()
                .country(employee.country())
                .name(employee.name())
                .matricule(matricule)
                .email(employee.email())
                .build();
        employeeRepository.save(entity);
        return EmployeeId.of(matricule);
    }

    @Override
    public EmployeeId edit(EmployeeId employeeId, EmployeeEdit employee) {
        EmployeeEntity entity = employeeRepository.findByMatricule(employeeId.id()).orElseThrow();
        Optional.ofNullable(employee.name()).ifPresent(entity::setName);
        Optional.ofNullable(employee.country()).ifPresent(entity::setCountry);
        Optional.ofNullable(employee.email()).ifPresent(entity::setEmail);
        return employeeId;
    }

    @Override
    public List<EmployeeInfo> getEmployees() {
        return employeeRepository.getEmployees();
    }

    @Override
    public Optional<EmployeeInfo> getEmployee(String employeeId) {
        return employeeRepository.getByMatricule(employeeId);
    }


    private String generateMatricule() {
        return randomNumeric(5);
    }

    @Override
    public void check(String matricule) {
        employeeRepository.findByMatricule(matricule)
                .orElseThrow(() -> new IllegalArgumentException("Employye not found: [" + matricule + "]"));
    }
}
