package com.casaSolaire.controllers.api;

import com.casaSolaire.dto.CategoryDto;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.casaSolaire.utils.Constants.APP_ROOT;

public interface CategoryApi {

    @PostMapping(value = APP_ROOT + "/categories/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CategoryDto> save(@RequestBody CategoryDto categoryDto);

    @GetMapping(value = APP_ROOT + "/categories/{idCategory}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CategoryDto> findById(@PathVariable("idCategory") Long id);

    @GetMapping(value = APP_ROOT + "/categories/{designation]", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CategoryDto> findByDesignation(@PathVariable("designation") String designation);

    @GetMapping(value = APP_ROOT + "/categories/all", produces = MediaType.APPLICATION_JSON_VALUE)
    List<CategoryDto> findAll();

    @DeleteMapping(value = APP_ROOT + "/categories/delete/{idCategory}")
    void delete(@PathVariable("idCategory") Long id);
}
