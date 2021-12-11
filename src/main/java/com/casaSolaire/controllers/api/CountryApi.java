package com.casaSolaire.controllers.api;

import com.casaSolaire.dto.CountryDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.casaSolaire.utils.Constants.APP_ROOT;

public interface CountryApi {

    @PostMapping(value = APP_ROOT + "/countries/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer un Country",
            notes = "Cette méthode permet d'ajouter une Country", response = CountryDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "La Country a été crée"),
            @ApiResponse(code = 400, message = "Aucun Country  crée / modifié")
    })
    ResponseEntity<CountryDto> save(@RequestBody CountryDto countryDto);

    @PutMapping(value = APP_ROOT + "/countries/update/{idCountry}",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Modifier une Country par son ID",
            notes = "Cette méthode permet de modifier une Country par son ID", response = CountryDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La Country a été modifiée"),
            @ApiResponse(code = 400, message = "La Country a n'est pas modifiée")
    })
    ResponseEntity<CountryDto> update(@PathVariable("idCountry") Long id, @RequestBody CountryDto countryDto);

    @GetMapping(value = APP_ROOT + "/countries/{idCountry}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher une Country par ID",
            notes = "Cette méthode permet de chercher une Country par son ID", response = CountryDto.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La Country a été trouver"),
            @ApiResponse(code = 404, message = "Aucun Country n'existe avec cette ID pas dans la BD")
    })
    ResponseEntity<CountryDto> findById(@PathVariable("idCountry") Long id);

    @GetMapping(value = APP_ROOT + "/countries/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des countries",
            notes = "Cette méthode permet de chercher et renvoyer la liste des countries", responseContainer = "List<CountryDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des countries / une liste vide")
    })
    List<CountryDto> findAll();

    @GetMapping(value = APP_ROOT + "/countries/searchAllCountryOrderByIdDesc", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des Country par ordre descroissante",
            notes = "Cette méthode permet de chercher et renvoyer la liste des Country par ordre descroissante",
            responseContainer = "List<CountryDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des Country par ordre descroissante / une liste vide")
    })
    ResponseEntity<List<CountryDto>> getAllCountryOrderByIdDesc();

    @DeleteMapping(value = APP_ROOT + "/countries/delete/{idCountry}")
    @ApiOperation(value = "Supprimer un Country par son ID",
            notes = "Cette méthode permet de supprimer une Country par son ID", response = CountryDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La Country a été supprimé")
    })
    void delete(@PathVariable("idCountry") Long id);
}
