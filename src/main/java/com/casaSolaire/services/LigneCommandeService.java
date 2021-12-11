package com.casaSolaire.services;

import com.casaSolaire.dto.LigneCommandeDto;

import java.util.List;

public interface LigneCommandeService {

    LigneCommandeDto save(LigneCommandeDto ligneCommandeDto);

    LigneCommandeDto findById(Long id);

    List<LigneCommandeDto> findAll();

    List<LigneCommandeDto> findByOrderByIdDesc();

    List<LigneCommandeDto> findArticlesGroupByProductIdOrderByCreatedDateDesc();

    List<LigneCommandeDto> findListLigneCommandeByCommandeId(Long comId);

    List<LigneCommandeDto> findTop200LigneCommandeOrderByIdDesc();

    void delete(Long id);
}
