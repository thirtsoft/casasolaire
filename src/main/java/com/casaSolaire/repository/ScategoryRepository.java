package com.casaSolaire.repository;

import com.casaSolaire.models.Scategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ScategoryRepository extends JpaRepository<Scategory, Long> {

    Optional<Scategory> findScategoryByLibelle(String libelle);
}
