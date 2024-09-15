package com.casaSolaire.services;

import com.casaSolaire.dto.FournisseurDto;
import com.casaSolaire.dto.ScategoryDto;

import java.util.List;

public interface ScategoryService {

    ScategoryDto save(ScategoryDto ScategoryDto);

    ScategoryDto update(Long id, ScategoryDto ScategoryDto);

    ScategoryDto findById(Long id);

    ScategoryDto findByLibelle(String libelle);

    List<ScategoryDto> findAll();

    List<ScategoryDto> findByOrderByIdDesc();

    void delete(Long id);

}
