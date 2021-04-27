package com.casaSolaire.controllers.api;

import com.casaSolaire.dto.AddressDto;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.casaSolaire.utils.Constants.APP_ROOT;

public interface AddressApi {

    @PostMapping(value = APP_ROOT + "/addresses/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<AddressDto> save(@RequestBody AddressDto addressDto);

    @GetMapping(value = APP_ROOT + "/addresses/{idAddress}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<AddressDto> findById(@PathVariable("idAddress") Long id);

    @GetMapping(value = APP_ROOT + "/addresses/all", produces = MediaType.APPLICATION_JSON_VALUE)
    List<AddressDto> findAll();

    @DeleteMapping(value = APP_ROOT + "/addresses/delete/{idAddress}")
    void delete(@PathVariable("idAddress") Long id);
}
