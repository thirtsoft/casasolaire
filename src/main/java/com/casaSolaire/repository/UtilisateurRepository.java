package com.casaSolaire.repository;

import com.casaSolaire.models.Category;
import com.casaSolaire.models.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
}
