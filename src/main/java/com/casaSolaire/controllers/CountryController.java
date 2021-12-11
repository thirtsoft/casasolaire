package com.casaSolaire.controllers;

import com.casaSolaire.controllers.api.CountryApi;
import com.casaSolaire.dto.CountryDto;
import com.casaSolaire.services.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class CountryController implements CountryApi {

    private final CountryService countryService;

    @Autowired
    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }


    @Override
    public ResponseEntity<CountryDto> save(CountryDto countryDto) {
        return ResponseEntity.ok(countryService.save(countryDto));
    }

    @Override
    public ResponseEntity<CountryDto> update(Long id, CountryDto countryDto) {
        countryDto.setId(id);
        return ResponseEntity.ok(countryService.save(countryDto));
    }

    @Override
    public ResponseEntity<CountryDto> findById(Long id) {
        return ResponseEntity.ok(countryService.findById(id));
    }

    @Override
    public List<CountryDto> findAll() {
        return countryService.findAll();
    }

    @Override
    public ResponseEntity<List<CountryDto>> getAllCountryOrderByIdDesc() {
        List<CountryDto> countryDtoList = countryService.findByOrderByIdDesc();
        return new ResponseEntity<>(countryDtoList, HttpStatus.OK);
    }

    @Override
    public void delete(Long id) {
        countryService.delete(id);
    }

}
