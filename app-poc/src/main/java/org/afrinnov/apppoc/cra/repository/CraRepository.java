package org.afrinnov.apppoc.cra.repository;

import org.afrinnov.apppoc.cra.entity.CraEntity;
import org.afrinnov.apppoc.cra.entity.MMonth;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Month;
import java.util.List;
import java.util.Optional;

public interface CraRepository extends JpaRepository<CraEntity, Long> {
    boolean existsByMatriculeAndMonthAndYearAndEditedTrue(String matricule, MMonth month, int year);

    Optional<CraEntity> findByMatriculeAndYearAndMonth(String matricule, int year, MMonth month);

    List<CraEntity> findByYearAndMonthAndEditedFalse(int year, MMonth month);

    boolean existsByMatriculeAndYearAndMonthAndEditedTrue(String matricule, int year, MMonth month);
}