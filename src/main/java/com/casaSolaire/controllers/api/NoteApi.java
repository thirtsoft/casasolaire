package com.casaSolaire.controllers.api;

import com.casaSolaire.dto.NoteDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.casaSolaire.utils.Constants.APP_ROOT;

public interface NoteApi {

    @PostMapping(value = APP_ROOT + "/notes/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer un Note",
            notes = "Cette méthode permet d'enregistrer et modifier un Note", response = NoteDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le Note a été crée / modifié"),
            @ApiResponse(code = 400, message = "Aucun Note  crée / modifié")

    })
    ResponseEntity<NoteDto> save(@RequestBody NoteDto noteDto);

    @GetMapping(value = APP_ROOT + "/notes/findById/{idNote}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher un Note par ID",
            notes = "Cette méthode permet de chercher un Note par son ID", response = NoteDto.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le Note a été trouver"),
            @ApiResponse(code = 404, message = "Aucun Note n'existe avec cette ID pas dans la BD")

    })
    ResponseEntity<NoteDto> findById(@PathVariable("idNote") Long id);

    @GetMapping(value = APP_ROOT + "/notes/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des Notes",
            notes = "Cette méthode permet de chercher et renvoyer la liste des Notes", responseContainer = "List<NoteDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des Notes / une liste vide")
    })
    List<NoteDto> findAll();

    @DeleteMapping(value = APP_ROOT + "/notes/delete/{idNote}")
    @ApiOperation(value = "Supprimer une Note par son ID",
            notes = "Cette méthode permet de supprimer une Note par son ID", response = NoteDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La Note a été supprimé")
    })
    void delete(@PathVariable("idNote") Long id);
}
