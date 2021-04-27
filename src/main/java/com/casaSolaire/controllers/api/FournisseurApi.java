package com.casaSolaire.controllers.api;

import com.casaSolaire.dto.ClientDto;
import com.casaSolaire.dto.CommandeDto;
import com.casaSolaire.dto.FournisseurDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.casaSolaire.utils.Constants.APP_ROOT;

public interface FournisseurApi {

    @PostMapping(value = APP_ROOT + "/fournisseurs/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer un Fournisseur",
            notes = "Cette méthode permet d'enregistrer et modifier un Fournisseur", response = FournisseurDto.class )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le Fournisseur a été crée / modifié"),
            @ApiResponse(code = 400, message = "Aucun Fournisseur  crée / modifié")

    })
    ResponseEntity<FournisseurDto> save(@RequestBody FournisseurDto fournisseurDto);

    @GetMapping(value = APP_ROOT + "/fournisseurs/{idFournisseur}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher un Fournisseur par ID",
            notes = "Cette méthode permet de chercher un Fournisseur par son ID", response = FournisseurDto.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le Fournisseur a été trouver"),
            @ApiResponse(code = 404, message = "Aucun Fournisseur n'existe avec cette ID pas dans la BD")

    })
    ResponseEntity<FournisseurDto> findById(@PathVariable("idFournisseur") Long id);

    @GetMapping(value = APP_ROOT + "/fournisseurs/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des Fournisseurs",
            notes = "Cette méthode permet de chercher et renvoyer la liste des Fournisseurs", responseContainer = "List<FournisseurDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des Fournisseurs / une liste vide")
    })
    List<FournisseurDto> findAll();

    @DeleteMapping(value = APP_ROOT + "/fournisseurs/delete/{idFournisseur}")
    @ApiOperation(value = "Supprimer un Fournisseur par son ID",
            notes = "Cette méthode permet de supprimer un Fournisseur par son ID", response = FournisseurDto.class )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le Fournisseur a été supprimé")
    })
    void delete(@PathVariable("idFournisseur") Long id);
}
