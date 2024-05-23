package org.afrinnov.apppoc.employee.repository;

import org.afrinnov.apppoc.employee.entity.EmployeeEntity;
import org.afrinnov.apppoc.employee.spi.model.EmployeeInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {

    Optional<EmployeeEntity> findByMatricule(String matricule);


    Optional<EmployeeInfo> getByMatricule(String matricule);

    @Query("select e from EmployeeEntity e order by e.name")
    List<EmployeeInfo> getEmployees();


}
