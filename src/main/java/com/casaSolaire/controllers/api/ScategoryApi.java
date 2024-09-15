package com.casaSolaire.controllers.api;

import com.casaSolaire.dto.FournisseurDto;
import com.casaSolaire.dto.NotificationDto;
import com.casaSolaire.dto.ScategoryDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.casaSolaire.utils.Constants.APP_ROOT;

public interface ScategoryApi {

    @PostMapping(value = APP_ROOT + "/scategories/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer une Scategory",
            notes = "Cette méthode permet d'enregistrer et modifier une Category", response = ScategoryDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "La Scategory a été crée / modifié"),
            @ApiResponse(code = 400, message = "Aucun Scategory  crée / modifié")

    })
    ResponseEntity<ScategoryDto> save(@RequestBody ScategoryDto scategoryDto);

    @PutMapping(value = APP_ROOT + "/scategories/update/{idScategory}",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Modifier une Scategory par son ID",
            notes = "Cette méthode permet de modifier une Scategory par son ID", response = ScategoryDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La Scategory a été modifiée"),
            @ApiResponse(code = 400, message = "La Scategory a n'est pas modifiée")
    })
    ResponseEntity<ScategoryDto> update(@PathVariable("idScategory") Long id, @RequestBody ScategoryDto scategoryDto);


    @GetMapping(value = APP_ROOT + "/scategories/findById/{idScategory}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher une Scategory par ID",
            notes = "Cette méthode permet de chercher une Scategory par son ID", response = ScategoryDto.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La Scategory a été trouver"),
            @ApiResponse(code = 404, message = "Aucun Scategory n'existe avec cette ID pas dans la BD")

    })
    ResponseEntity<ScategoryDto> findById(@PathVariable("idScategory") Long id);

    @GetMapping(value = APP_ROOT + "/scategories/searchbyLibelle/{libelle}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher une Scategory par Designation",
            notes = "Cette méthode permet de chercher une Scategory par sa Libelle", response = ScategoryDto.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La Scategory avec la desingation Designation a été trouver"),
            @ApiResponse(code = 404, message = "Aucun Category n'existe avec cette Designation pas dans la BD")

    })
    ResponseEntity<ScategoryDto> findByLibelle(@PathVariable("libelle") String libelle);

    @GetMapping(value = APP_ROOT + "/scategories/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des SCategories",
            notes = "Cette méthode permet de chercher et renvoyer la liste des SCategories", responseContainer = "List<ScategoryDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des SCategories / une liste vide")
    })
    List<ScategoryDto> findAll();

    @GetMapping(value = APP_ROOT + "/scategories/searchAllSubCategoriesOrderByIdDesc", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des Articles par ordre descroissante",
            notes = "Cette méthode permet de chercher et renvoyer la liste des Scategories par ordre descroissante",
            responseContainer = "List<ScategoryDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des Scategories  par ordre descroissante / une liste vide")
    })
    ResponseEntity<List<ScategoryDto>> getAllSubCategoriesOrderByIdDesc();

    @DeleteMapping(value = APP_ROOT + "/scategories/delete/{idScategory}")
    @ApiOperation(value = "Supprimer une SCategory par son ID",
            notes = "Cette méthode permet de supprimer une SCategory par son ID", response = ScategoryDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La SCategory a été supprimé")
    })
    void delete(@PathVariable("idScategory") Long id);

}
