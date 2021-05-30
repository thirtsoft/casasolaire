package com.casaSolaire.services;

import com.casaSolaire.dto.AddressDto;

import java.util.List;

public interface AddressService {

    AddressDto save(AddressDto addressDto);

    AddressDto findById(Long id);

    List<AddressDto> findAll();

    void delete(Long id);
}
