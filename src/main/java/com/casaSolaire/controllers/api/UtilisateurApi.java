package com.casaSolaire.controllers.api;

import com.casaSolaire.dto.UtilisateurDto;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.casaSolaire.utils.Constants.APP_ROOT;

public interface UtilisateurApi {

    @PostMapping(value = APP_ROOT + "/utilisateurs/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<UtilisateurDto> save(@RequestBody UtilisateurDto utilisateurDto);

    @GetMapping(value = APP_ROOT + "/utilisateurs/{idUtilisateur}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<UtilisateurDto> findById(@PathVariable("idUtilisateur") Long id);

    @GetMapping(value = APP_ROOT + "/utilisateurs/all", produces = MediaType.APPLICATION_JSON_VALUE)
    List<UtilisateurDto> findAll();

    @DeleteMapping(value = APP_ROOT + "/utilisateurs/delete/{idUtilisateur}")
    void delete(@PathVariable("idUtilisateur") Long id);
}
