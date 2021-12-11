package com.casaSolaire.repository;

import com.casaSolaire.models.HistoriqueLogin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

public interface HistoriqueLoginRepository extends JpaRepository<HistoriqueLogin, Long> {

    @Query("select count(f) from HistoriqueLogin f ")
    BigDecimal countNumberOfHistoriqueLogins();

    List<HistoriqueLogin> findByOrderByIdDesc();

}
