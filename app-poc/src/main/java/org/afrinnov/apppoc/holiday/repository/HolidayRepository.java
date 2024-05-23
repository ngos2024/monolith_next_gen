package org.afrinnov.apppoc.holiday.repository;

import org.afrinnov.apppoc.holiday.entity.HolidayEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface HolidayRepository extends JpaRepository<HolidayEntity, Long> {
    Optional<HolidayEntity> findByMatriculeAndDate(String matricule, LocalDate date);

    List<HolidayEntity> findByMatriculeAndAcceptedTrueAndDateBetween(String matricule, LocalDate dateStart, LocalDate dateEnd);

    long countByMatriculeAndAcceptedTrueAndDateBetween(String matricule, LocalDate dateStart, LocalDate dateEnd);
}