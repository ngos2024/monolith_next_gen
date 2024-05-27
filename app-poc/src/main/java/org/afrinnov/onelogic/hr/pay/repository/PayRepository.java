package org.afrinnov.onelogic.hr.pay.repository;

import org.afrinnov.onelogic.hr.pay.entity.PMonth;
import org.afrinnov.onelogic.hr.pay.entity.PayEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PayRepository extends JpaRepository<PayEntity, Long> {
    List<PayEntity> findByYearAndMonthAndPaidTrue(int year, PMonth month);

    boolean existsByMatriculeAndYearAndMonthAndPaidTrue(String matricule, int year, PMonth month);
}