package com.casaSolaire.controllers.api;

import com.casaSolaire.dto.ArticleDto;
import com.casaSolaire.dto.CategoryDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.casaSolaire.utils.Constants.APP_ROOT;

public interface CategoryApi {

    @PostMapping(value = APP_ROOT + "/categories/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer une Category",
            notes = "Cette méthode permet d'enregistrer et modifier une Category", response = CategoryDto.class )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La Category a été crée / modifié"),
            @ApiResponse(code = 400, message = "Aucun Category  crée / modifié")

    })
    ResponseEntity<CategoryDto> save(@RequestBody CategoryDto categoryDto);

    @GetMapping(value = APP_ROOT + "/categories/{idCategory}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher une Category par ID",
            notes = "Cette méthode permet de chercher une Category par son ID", response = CategoryDto.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La Category a été trouver"),
            @ApiResponse(code = 404, message = "Aucun Category n'existe avec cette ID pas dans la BD")

    })
    ResponseEntity<CategoryDto> findById(@PathVariable("idCategory") Long id);

    @GetMapping(value = APP_ROOT + "/categories/{designation]", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher une Category par Designation",
            notes = "Cette méthode permet de chercher une Category par sa Designation", response = CategoryDto.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La Category avec la desingation Designation a été trouver"),
            @ApiResponse(code = 404, message = "Aucun Category n'existe avec cette Designation pas dans la BD")

    })
    ResponseEntity<CategoryDto> findByDesignation(@PathVariable("designation") String designation);

    @GetMapping(value = APP_ROOT + "/categories/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des Categories",
            notes = "Cette méthode permet de chercher et renvoyer la liste des Categories", responseContainer = "List<CategoryDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des Categories / une liste vide")
    })
    List<CategoryDto> findAll();

    @DeleteMapping(value = APP_ROOT + "/categories/delete/{idCategory}")
    @ApiOperation(value = "Supprimer une Category par son ID",
            notes = "Cette méthode permet de supprimer une Category par son ID", response = CategoryDto.class )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La Category a été supprimé")
    })
    void delete(@PathVariable("idCategory") Long id);
}
