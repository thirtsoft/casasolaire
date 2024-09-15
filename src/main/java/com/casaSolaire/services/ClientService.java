package com.casaSolaire.services;

import com.casaSolaire.dto.CategoryDto;
import com.casaSolaire.dto.ClientDto;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

public interface ClientService {

    ClientDto save(ClientDto clientDto);

    ClientDto update(Long id, ClientDto clientDto);

    BigDecimal countNumberOfClient();

    ClientDto findById(Long id);

    List<ClientDto> findAll();

    List<ClientDto> findByOrderByIdDesc();

    void delete(Long id);
}
