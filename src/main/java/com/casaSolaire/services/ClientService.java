package com.casaSolaire.services;

import com.casaSolaire.dto.CategoryDto;
import com.casaSolaire.dto.ClientDto;

import java.util.List;

public interface ClientService {

    ClientDto save(ClientDto clientDto);

    ClientDto update(Long id, ClientDto clientDto);

    ClientDto findById(Long id);

    List<ClientDto> findAll();

    List<ClientDto> findByOrderByIdDesc();

    void delete(Long id);
}
