package com.casaSolaire.services;

import com.casaSolaire.dto.CountryDto;

import java.math.BigDecimal;
import java.util.List;

public interface CountryService {

    CountryDto save(CountryDto countryDto);

    CountryDto update(Long id, CountryDto countryDto);

    BigDecimal countNumberOfCountry();

    CountryDto findById(Long id);

    List<CountryDto> findAll();

    List<CountryDto> findByOrderByIdDesc();

    void delete(Long id);

}
