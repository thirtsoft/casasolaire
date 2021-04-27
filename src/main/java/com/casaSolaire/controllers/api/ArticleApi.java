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

public interface ArticleApi {

    @PostMapping(value = APP_ROOT + "/articles/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer un Article",
            notes = "Cette méthode permet d'enregistrer et modifier un Article", response = ArticleDto.class )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'Article a été crée / modifié"),
            @ApiResponse(code = 400, message = "Aucun Address  crée / modifié")

    })
    ResponseEntity<ArticleDto> save(@RequestBody ArticleDto articleDto);

    @GetMapping(value = APP_ROOT + "/articles/{idArticle}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher un Article par ID",
            notes = "Cette méthode permet de chercher un Article par son ID", response = ArticleDto.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'Article a été trouver"),
            @ApiResponse(code = 404, message = "Aucun Article n'existe avec cette ID pas dans la BD")

    })
    ResponseEntity<ArticleDto> findById(@PathVariable("idArticle") Long id);

    @GetMapping(value = APP_ROOT + "/articles/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des Articles",
            notes = "Cette méthode permet de chercher et renvoyer la liste des Articles", responseContainer = "List<ArticleDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des Address / une liste vide")
    })
    List<ArticleDto> findAll();

    @DeleteMapping(value = APP_ROOT + "/articles/delete/{idArticle}")
    @ApiOperation(value = "Supprimer un Article par son ID",
            notes = "Cette méthode permet de supprimer un Article par son ID", response = ArticleDto.class )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'Article a été supprimé")
    })
    void delete(@PathVariable("idArticle") Long id);
}
