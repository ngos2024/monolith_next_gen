package org.afrinnov.onelogic.hr.holiday.repository;

import org.afrinnov.onelogic.hr.holiday.entity.HolidayEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface HolidayRepository extends JpaRepository<HolidayEntity, Long> {
    Optional<HolidayEntity> findByMatriculeAndDate(String matricule, LocalDate date);

    long countByMatriculeAndAcceptedTrueAndDateBetween(String matricule, LocalDate dateStart, LocalDate dateEnd);
}