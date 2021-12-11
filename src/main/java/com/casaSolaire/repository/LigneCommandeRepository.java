package com.casaSolaire.repository;

import com.casaSolaire.models.LigneCommande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LigneCommandeRepository extends JpaRepository<LigneCommande, Long> {

    @Query("select (p.productName), count(p) from LigneCommande p group by (p.productId)")
    List<LigneCommande> findArticlesGroupByProductId();

    List<LigneCommande> findByOrderByIdDesc();

    @Query("select p from LigneCommande p where p.commande.id =:num")
    List<LigneCommande> ListLigneCommandeByCommandeId(@Param("num") Long comId);

    List<LigneCommande> findTop200ByOrderByIdDesc();

}
