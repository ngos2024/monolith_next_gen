package org.afrinnov.apppoc.pay.repository;

import org.afrinnov.apppoc.pay.entity.PMonth;
import org.afrinnov.apppoc.pay.entity.PayEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PayRepository extends JpaRepository<PayEntity, Long> {
    List<PayEntity> findByYearAndMonthAndPaidTrue(int year, PMonth month);

    boolean existsByMatriculeAndYearAndMonthAndPaidTrue(String matricule, int year, PMonth month);
}