package com.casaSolaire.controllers.api;

import com.casaSolaire.dto.NewsletterDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

import static com.casaSolaire.utils.Constants.APP_ROOT;

public interface NewsletterApi {

    @PostMapping(value = APP_ROOT + "/newsletters/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "S'inscrire au Newsletter",
            notes = "Cette méthode permet de s'inscrire au Newsletter", response = NewsletterDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Le Newsletter a été crée"),
            @ApiResponse(code = 400, message = "Aucun Newsletter  crée / modifié")
    })
    ResponseEntity<NewsletterDto> save(@RequestBody NewsletterDto newsletterDto);

    @PutMapping(value = APP_ROOT + "/newsletters/update/{idNewsletter}",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Modifier un Newsletter par son ID",
            notes = "Cette méthode permet de modifier un Newsletter par son ID", response = NewsletterDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le Newsletter a été modifiée"),
            @ApiResponse(code = 400, message = "Le Newsletter a n'est pas modifiée")
    })
    ResponseEntity<NewsletterDto> update(@PathVariable("idNewsletter") Long id, @RequestBody NewsletterDto newsletterDto);

    @GetMapping(value = APP_ROOT + "/newsletters/findById/{idNewsletter}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher un Newsletter par ID",
            notes = "Cette méthode permet de chercher une Newsletter par son ID", response = NewsletterDto.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La Newsletter a été trouver"),
            @ApiResponse(code = 404, message = "Aucun Newsletter n'existe avec cette ID pas dans la BD")
    })
    ResponseEntity<NewsletterDto> findById(@PathVariable("idNewsletter") Long id);

    @GetMapping(value = APP_ROOT + "/newsletters/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des Newsletter",
            notes = "Cette méthode permet de chercher et renvoyer la liste des Newsletter", responseContainer = "List<NewsletterDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des Newsletter / une liste vide")
    })
    ResponseEntity<List<NewsletterDto>> findAll();

    @GetMapping(value = APP_ROOT + "/newsletters/searchAllNewslettersOrderByIdDesc", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des Newsletter par ordre descroissante",
            notes = "Cette méthode permet de chercher et renvoyer la liste des Newsletter par ordre descroissante",
            responseContainer = "List<NewsletterDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des Newsletter  par ordre descroissante / une liste vide")
    })
    ResponseEntity<List<NewsletterDto>> getAllNewslettersOrderByIdDesc();

    @GetMapping(value = APP_ROOT + "/newsletters/countNumberOfNewsletters", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi le nombre de Newsletter",
            notes = "Cette méthode permet de chercher et renvoyer le nombre de Newsletter")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le nombre de Newsletter / le nombre est nulle")
    })
    BigDecimal countNumberOfNewsletters();

    @DeleteMapping(value = APP_ROOT + "/newsletters/delete/{idNewsletter}")
    @ApiOperation(value = "Supprimer un Newsletter par son ID",
            notes = "Cette méthode permet de supprimer une Newsletter par son ID", response = NewsletterDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La Newsletter a été supprimé")
    })
    void delete(@PathVariable("idNewsletter") Long id);

}
