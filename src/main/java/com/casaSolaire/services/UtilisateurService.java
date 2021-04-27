package com.casaSolaire.services;

import com.casaSolaire.dto.UtilisateurDto;

import java.util.List;

public interface UtilisateurService {

    UtilisateurDto save(UtilisateurDto utilisateurDto);

    UtilisateurDto findById(Long id);

    List<UtilisateurDto> findAll();

    void delete(Long id);
}
