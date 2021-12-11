package com.casaSolaire.controllers.api;

import com.casaSolaire.dto.LigneCommandeDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.casaSolaire.utils.Constants.APP_ROOT;

public interface LigneCommandeApi {

    @PostMapping(value = APP_ROOT + "/lignecommandes/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<LigneCommandeDto> save(@RequestBody LigneCommandeDto ligneCommandeDto);

    @GetMapping(value = APP_ROOT + "/lignecommandes/findById/{idLignecommande}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<LigneCommandeDto> findById(@PathVariable("idLignecommande") Long id);

    @GetMapping(value = APP_ROOT + "/lignecommandes/all", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<LigneCommandeDto>> findAll();

    @GetMapping(value = APP_ROOT + "/lignecommandes/searchAllLigneCommandeOrderByIdDesc", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des LigneCommandes par ordre descroissante",
            notes = "Cette méthode permet de chercher et renvoyer la liste des LigneCommandes par ordre descroissante",
            responseContainer = "List<LigneCommandeDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des LigneCommandes  par ordre descroissante / une liste vide")
    })
    ResponseEntity<List<LigneCommandeDto>> getAllLigneCommandeOrderByIdDesc();

    @GetMapping(value = APP_ROOT + "/lignecommandes/findListArticleGroupByIdDesc", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des LigneCommandes par ordre descroissante",
            notes = "Cette méthode permet de chercher et renvoyer la liste des LigneCommandes par ordre descroissante",
            responseContainer = "List<LigneCommandeDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des Articles  par ordre descroissante / une liste vide")
    })
    ResponseEntity<List<LigneCommandeDto>> getArticlesGroupByProductIdOrderByCreatedDateDesc();

    @GetMapping(value = APP_ROOT + "/lignecommandes/searchAllLigneCommandesByCommandeId/{comId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des LigneCommandes  par commande ID",
            notes = "Cette méthode permet de chercher et renvoyer la liste des LigneCommandes par  commande ID",
            responseContainer = "List<LigneCommandeDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des LigneCommandes  par commande ID / une liste vide")
    })
    ResponseEntity<List<LigneCommandeDto>> getAllLigneCommandesByCommandeId(@PathVariable("comId") Long comId);

    @GetMapping(value = APP_ROOT + "/lignecommandes/searchTopLigneCommandesOrderByIdDesc", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des 200 dernières LigneCommandes  par ordre décroissant",
            notes = "Cette méthode permet de chercher et renvoyer la liste des 200 derniers LigneCommandes par ID décroissant",
            responseContainer = "List<LigneCommandeDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des 200 dernières LigneCommandes  par ID décroissant / une liste vide")
    })
    ResponseEntity<List<LigneCommandeDto>> getTop200LigneCommandesOrderByIdDesc();

    @DeleteMapping(value = APP_ROOT + "/lignecommandes/delete/{idLignecommande}")
    @ApiOperation(value = "Supprimer un LigneCommande par son ID",
            notes = "Cette méthode permet de supprimer une LigneCommande par son ID", response = LigneCommandeDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La LigneCommande a été supprimé")
    })
    void delete(@PathVariable("idLignecommande") Long id);
}
