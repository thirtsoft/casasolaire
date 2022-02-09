package com.casaSolaire.services;

import com.casaSolaire.dto.AddressDto;
import com.casaSolaire.dto.ArticleDto;

import java.util.List;

public interface AddressService {

    AddressDto save(AddressDto addressDto);

    AddressDto findById(Long id);

    List<AddressDto> findAll();

    List<AddressDto> findByOrderByIdDesc();

    void delete(Long id);
}
