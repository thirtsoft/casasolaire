package com.casaSolaire.services;

import com.casaSolaire.dto.FournisseurDto;

import java.math.BigDecimal;
import java.util.List;

public interface FournisseurService {

    FournisseurDto save(FournisseurDto fournisseurDto);

    FournisseurDto update(Long id, FournisseurDto fournisseurDto);

    BigDecimal countNumberOfFournisseur();

    FournisseurDto findById(Long id);

    List<FournisseurDto> findAll();

    List<FournisseurDto> findByOrderByIdDesc();

    void delete(Long id);
}
