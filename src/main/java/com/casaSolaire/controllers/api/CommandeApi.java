package com.casaSolaire.controllers.api;

import com.casaSolaire.dto.CommandeDto;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.casaSolaire.utils.Constants.APP_ROOT;

public interface CommandeApi {

    @PostMapping(value = APP_ROOT + "/commandes/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CommandeDto> save(@RequestBody CommandeDto commandeDto);

    @GetMapping(value = APP_ROOT + "/commandes/{idCommande}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CommandeDto> findById(@PathVariable("idCommande") Long id);

    @GetMapping(value = APP_ROOT + "/commandes/all", produces = MediaType.APPLICATION_JSON_VALUE)
    List<CommandeDto> findAll();

    @DeleteMapping(value = APP_ROOT + "/commandes/delete/{idCommande}")
    void delete(@PathVariable("idCommande") Long id);
}
