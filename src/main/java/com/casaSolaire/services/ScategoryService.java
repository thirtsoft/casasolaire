package com.casaSolaire.services;

import com.casaSolaire.dto.ScategoryDto;

import java.util.List;

public interface ScategoryService {

    ScategoryDto save(ScategoryDto scategoryDto);

    ScategoryDto findById(Long id);

    ScategoryDto findByLibelle(String libelle);

    List<ScategoryDto> findAll();

    void delete(Long id);

}
