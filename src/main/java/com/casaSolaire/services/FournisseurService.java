package com.casaSolaire.services;

import com.casaSolaire.dto.ClientDto;
import com.casaSolaire.dto.FournisseurDto;

import java.util.List;

public interface FournisseurService {

    FournisseurDto save(FournisseurDto fournisseurDto);

    FournisseurDto update(Long id, FournisseurDto fournisseurDto);

    FournisseurDto findById(Long id);

    List<FournisseurDto> findAll();

    List<FournisseurDto> findByOrderByIdDesc();

    void delete(Long id);
}
