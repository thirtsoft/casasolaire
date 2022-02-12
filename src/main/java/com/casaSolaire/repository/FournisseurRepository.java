package com.casaSolaire.repository;

import com.casaSolaire.models.Fournisseur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface FournisseurRepository extends JpaRepository<Fournisseur, Long> {

    @Query("select count(p) from Fournisseur p ")
    BigDecimal countNumberOfFournisseur();

    List<Fournisseur> findByOrderByIdDesc();
}
