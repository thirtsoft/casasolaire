package com.casaSolaire.controllers.api;

import com.casaSolaire.dto.AddressDto;
import com.casaSolaire.dto.ArticleDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.casaSolaire.utils.Constants.APP_ROOT;

public interface AddressApi {

    @PostMapping(value = APP_ROOT + "/addresses/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer une Address",
            notes = "Cette méthode permet d'enregistrer et modifier une Address", response = AddressDto.class )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'Address a été crée / modifié"),
            @ApiResponse(code = 400, message = "Aucun Address  crée / modifié")

    })
    ResponseEntity<AddressDto> save(@RequestBody AddressDto addressDto);

    @GetMapping(value = APP_ROOT + "/addresses/findById/{idAddress}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher un Address par ID",
            notes = "Cette méthode permet de chercher une Address par son ID", response = AddressDto.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'Address a été trouver"),
            @ApiResponse(code = 404, message = "Aucun Address n'existe avec cette ID pas dans la BD")

    })
    ResponseEntity<AddressDto> findById(@PathVariable("idAddress") Long id);

    @GetMapping(value = APP_ROOT + "/addresses/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des Address",
            notes = "Cette méthode permet de chercher et renvoyer la liste des Address", responseContainer = "List<AddressDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des Address / une liste vide")
    })
    List<AddressDto> findAll();

    @GetMapping(value = APP_ROOT + "/addresses/searchAllAddressOrderByIdDesc", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des Address par ordre descroissante",
            notes = "Cette méthode permet de chercher et renvoyer la liste des Address par ordre descroissante",
            responseContainer = "List<AddressDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des Articles  par ordre descroissante / une liste vide")
    })
    ResponseEntity<List<AddressDto>> getAllAddressesOrderByIdDesc();

    @DeleteMapping(value = APP_ROOT + "/addresses/delete/{idAddress}")
    @ApiOperation(value = "Supprimer une Address par son ID",
            notes = "Cette méthode permet de supprimer une Address par son ID", response = AddressDto.class )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La Charge a été supprimé")
    })
    void delete(@PathVariable("idAddress") Long id);
}
