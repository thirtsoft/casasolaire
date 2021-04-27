package com.casaSolaire.services;

import com.casaSolaire.dto.CommandeDto;

import java.util.List;

public interface CommandeService {

    CommandeDto save(CommandeDto commandeDto);

    CommandeDto findById(Long id);

    List<CommandeDto> findAll();

    void delete(Long id);
}
