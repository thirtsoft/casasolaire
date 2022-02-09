package com.casaSolaire.repository;

import com.casaSolaire.models.Client;
import com.casaSolaire.models.Fournisseur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FournisseurRepository extends JpaRepository<Fournisseur, Long> {
    List<Fournisseur> findByOrderByIdDesc();
}
