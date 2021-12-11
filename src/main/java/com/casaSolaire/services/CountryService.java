package com.casaSolaire.services;

import com.casaSolaire.dto.CountryDto;

import java.util.List;

public interface CountryService {

    CountryDto save(CountryDto countryDto);

    CountryDto update(Long id, CountryDto countryDto);

    CountryDto findById(Long id);

    List<CountryDto> findAll();

    List<CountryDto> findByOrderByIdDesc();

    void delete(Long id);

}
