package com.casaSolaire.controllers.api;

import com.casaSolaire.dto.ClientDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

import static com.casaSolaire.utils.Constants.APP_ROOT;

public interface ClientApi {

    @PostMapping(value = APP_ROOT + "/clients/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer un Client",
            notes = "Cette méthode permet d'enregistrer et modifier un Client", response = ClientDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le Client a été crée / modifié"),
            @ApiResponse(code = 400, message = "Aucun Client  crée / modifié")

    })
    ResponseEntity<ClientDto> save(@RequestBody ClientDto clientDto);

    @GetMapping(value = APP_ROOT + "/clients/findById/{idClient}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher un Client par ID",
            notes = "Cette méthode permet de chercher un Client par son ID", response = ClientDto.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le Client a été trouver"),
            @ApiResponse(code = 404, message = "Aucun Client n'existe avec cette ID pas dans la BD")

    })
    ResponseEntity<ClientDto> findById(@PathVariable("idClient") Long id);

    @GetMapping(value = APP_ROOT + "/clients/countNumberOfClient", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi le nombre de Client",
            notes = "Cette méthode permet de chercher et renvoyer le nombre de Client")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le nombre de Client / le nombre est nulle")
    })
    BigDecimal countNumberOfClients();

    @GetMapping(value = APP_ROOT + "/clients/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des Clients",
            notes = "Cette méthode permet de chercher et renvoyer la liste des Clients", responseContainer = "List<ClientDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des Clients / une liste vide")
    })
    List<ClientDto> findAll();

    @GetMapping(value = APP_ROOT + "/clients/searchAllClientsOrderByIdDesc", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des Clients par ordre descroissante",
            notes = "Cette méthode permet de chercher et renvoyer la liste des Clients par ordre descroissante",
            responseContainer = "List<ClientDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des Categories  par ordre descroissante / une liste vide")
    })
    ResponseEntity<List<ClientDto>> getAllClientsOrderByIdDesc();

    @DeleteMapping(value = APP_ROOT + "/clients/delete/{idClient}")
    @ApiOperation(value = "Supprimer un Client par son ID",
            notes = "Cette méthode permet de supprimer un ClientDto par son ID", response = ClientDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le Client a été supprimé")
    })
    void delete(@PathVariable("idClient") Long id);
}
