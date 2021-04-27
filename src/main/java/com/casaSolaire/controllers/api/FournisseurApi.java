package com.casaSolaire.controllers.api;

import com.casaSolaire.dto.FournisseurDto;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.casaSolaire.utils.Constants.APP_ROOT;

public interface FournisseurApi {

    @PostMapping(value = APP_ROOT + "/fournisseurs/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<FournisseurDto> save(@RequestBody FournisseurDto fournisseurDto);

    @GetMapping(value = APP_ROOT + "/fournisseurs/{idFournisseur}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<FournisseurDto> findById(@PathVariable("idFournisseur") Long id);

    @GetMapping(value = APP_ROOT + "/fournisseurs/all", produces = MediaType.APPLICATION_JSON_VALUE)
    List<FournisseurDto> findAll();

    @DeleteMapping(value = APP_ROOT + "/fournisseurs/delete/{idFournisseur}")
    void delete(@PathVariable("idFournisseur") Long id);
}
