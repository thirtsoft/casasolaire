package com.casaSolaire.controllers.api;


import com.casaSolaire.dto.CommandeDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.casaSolaire.utils.Constants.APP_ROOT;

public interface CommandeApi {

    @PostMapping(value = APP_ROOT + "/commandes/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer une Commande",
            notes = "Cette méthode permet d'enregistrer et modifier un Commande", response = CommandeDto.class )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La Commande a été crée / modifié"),
            @ApiResponse(code = 400, message = "Aucun Commande  crée / modifié")

    })
    ResponseEntity<CommandeDto> save(@RequestBody CommandeDto commandeDto);

    @GetMapping(value = APP_ROOT + "/commandes/{idCommande}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher une Commande par ID",
            notes = "Cette méthode permet de chercher une Commande par son ID", response = CommandeDto.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La Commande a été trouver"),
            @ApiResponse(code = 404, message = "Aucun Commande n'existe avec cette ID pas dans la BD")

    })
    ResponseEntity<CommandeDto> findById(@PathVariable("idCommande") Long id);

    @GetMapping(value = APP_ROOT + "/commandes/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des Commandes",
            notes = "Cette méthode permet de chercher et renvoyer la liste des Commandes", responseContainer = "List<CommandeDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des Commandes / une liste vide")
    })
    List<CommandeDto> findAll();

    @DeleteMapping(value = APP_ROOT + "/commandes/delete/{idCommande}")
    @ApiOperation(value = "Supprimer une Commande par son ID",
            notes = "Cette méthode permet de supprimer une Commande par son ID", response = CommandeDto.class )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La Commande a été supprimé")
    })
    void delete(@PathVariable("idCommande") Long id);
}
