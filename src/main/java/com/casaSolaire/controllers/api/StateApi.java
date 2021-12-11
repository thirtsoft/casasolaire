package com.casaSolaire.controllers.api;

import com.casaSolaire.dto.StateDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.casaSolaire.utils.Constants.APP_ROOT;

public interface StateApi {

    @PostMapping(value = APP_ROOT + "/states/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer un State",
            notes = "Cette méthode permet d'ajouter une State", response = StateDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "La State a été crée"),
            @ApiResponse(code = 400, message = "Aucun State  crée / modifié")
    })
    ResponseEntity<StateDto> save(@RequestBody StateDto stateDto);

    @PutMapping(value = APP_ROOT + "/states/update/{idState}",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Modifier une State par son ID",
            notes = "Cette méthode permet de modifier une State par son ID", response = StateDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La State a été modifiée"),
            @ApiResponse(code = 400, message = "La State a n'est pas modifiée")
    })
    ResponseEntity<StateDto> update(@PathVariable("idState") Long id, @RequestBody StateDto stateDto);

    @GetMapping(value = APP_ROOT + "/states/{idState}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher une State par ID",
            notes = "Cette méthode permet de chercher une State par son ID", response = StateDto.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La State a été trouver"),
            @ApiResponse(code = 404, message = "Aucun State n'existe avec cette ID pas dans la BD")
    })
    ResponseEntity<StateDto> findById(@PathVariable("idState") Long id);

    @GetMapping(value = APP_ROOT + "/states/searchbyDesignation/{designation}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher une State par Designation",
            notes = "Cette méthode permet de chercher une State par son Libelle", response = StateDto.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La State a été trouver"),
            @ApiResponse(code = 404, message = "Aucun State n'existe avec cette ID pas dans la BD")
    })
    ResponseEntity<StateDto> findByDesignation(@PathVariable("designation") String designation);

    @GetMapping(value = APP_ROOT + "/states/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des states",
            notes = "Cette méthode permet de chercher et renvoyer la liste des states", responseContainer = "List<StateDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des states / une liste vide")
    })
    List<StateDto> findAll();

    @GetMapping(value = APP_ROOT + "/states/searchAllStatesOrderByIdDesc", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des States par ordre descroissante",
            notes = "Cette méthode permet de chercher et renvoyer la liste des States par ordre descroissante",
            responseContainer = "List<StateDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des States par ordre descroissante / une liste vide")
    })
    ResponseEntity<List<StateDto>> getAllStatesOrderByIdDesc();

    @GetMapping(value = APP_ROOT + "/states/searchStateByCountryCode", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des states",
            notes = "Cette méthode permet de chercher et renvoyer la liste des states", responseContainer = "List<StateDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des states / une liste vide")
    })
    List<StateDto> getAllStateByCountryCode(@RequestParam(name = "code") String code);

    @DeleteMapping(value = APP_ROOT + "/states/delete/{idState}")
    @ApiOperation(value = "Supprimer un State par son ID",
            notes = "Cette méthode permet de supprimer une State par son ID", response = StateDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La State a été supprimé")
    })
    void delete(@PathVariable("idState") Long id);

}
