package org.afrinnov.onelogic.hr.employee.repository;

import org.afrinnov.onelogic.hr.employee.entity.EmployeeEntity;
import org.afrinnov.onelogic.hr.employee.spi.model.EmployeeInfo;
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
