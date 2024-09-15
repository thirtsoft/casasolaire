package com.casaSolaire.controllers.api;


import com.casaSolaire.dto.CommandeDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

import static com.casaSolaire.utils.Constants.APP_ROOT;

public interface CommandeApi {

    @PostMapping(value = APP_ROOT + "/commandes/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer une Commande",
            notes = "Cette méthode permet d'ajouter une Commande", response = CommandeDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "La Commande a été crée"),
            @ApiResponse(code = 400, message = "Aucune Commande  crée / modifié")

    })
    ResponseEntity<CommandeDto> save(@RequestBody CommandeDto commandeDto);

    @PostMapping(value = APP_ROOT + "/commandes/saveWithAddresses", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer une Commande",
            notes = "Cette méthode permet d'ajouter une Commande", response = CommandeDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "La Commande a été crée"),
            @ApiResponse(code = 400, message = "Aucune Commande  crée / modifié")

    })
    ResponseEntity<CommandeDto> saveWithAddresses(@RequestBody CommandeDto commandeDto);

    @PostMapping(value = APP_ROOT + "/commandes/saveWithLoginUtilisateur", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer une Commande",
            notes = "Cette méthode permet d'ajouter une Commande", response = CommandeDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "La Commande a été crée"),
            @ApiResponse(code = 400, message = "Aucune Commande  crée / modifié")

    })
    ResponseEntity<CommandeDto> saveWithLoginUser(@RequestBody CommandeDto commandeDto, @RequestParam Long id);

    @PutMapping(value = APP_ROOT + "/commandes/update/{idCommande}",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Modifier une Commande par son ID",
            notes = "Cette méthode permet de modifier une Commande par son ID", response = CommandeDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'Artilce a été modifiée"),
            @ApiResponse(code = 400, message = "L'Artilce a n'est pas modifiée")
    })
    ResponseEntity<CommandeDto> update(@PathVariable("idCommande") Long id, @RequestBody CommandeDto commandeDto);

    @PatchMapping(value = APP_ROOT + "/commandes/updateStatusOfCommande/{id}")
    @ApiOperation(value = "Modifier une Commande par son Status",
            notes = "Cette méthode permet de modifier une Commande par son Status", response = CommandeDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le status de la Commande a été modifié")
    })
    ResponseEntity<CommandeDto> updateStatusOfCommande(@RequestParam("status") String status, @PathVariable("id") String id);

    @GetMapping(value = APP_ROOT + "/commandes/findById/{idCommande}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher une Commande par ID",
            notes = "Cette méthode permet de chercher une Commande par son ID", response = CommandeDto.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La Commande a été trouver"),
            @ApiResponse(code = 404, message = "Aucune Commande n'existe avec cette ID pas dans la BD")

    })
    ResponseEntity<CommandeDto> findById(@PathVariable("idCommande") Long id);

    @GetMapping(value = APP_ROOT + "/commandes/countNumberOfCommande", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi le nombre de Commande",
            notes = "Cette méthode permet de chercher et renvoyer le nombre de Commande")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le nombre de Commande / le nombre est nulle")
    })
    BigDecimal countNumberOfCommande();

    @GetMapping(value = APP_ROOT + "/commandes/countNumberOfOrdersInMonth", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi le nombre de Commande du mois",
            notes = "Cette méthode permet de chercher et renvoyer le nombre de Commande du mois")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le nombre de Commande / le nombre est nulle")
    })
    BigDecimal countNumberOfOrdersInMonth();

    @GetMapping(value = APP_ROOT + "/commandes/countNumberOfOrdersByPendingStatus", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi le nombre de Commande encours",
            notes = "Cette méthode permet de chercher et renvoyer le nombre de Commande encours")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le nombre de Commande / le nombre est nulle")
    })
    BigDecimal countNumberOfOrdersByStatusPending();

    @GetMapping(value = APP_ROOT + "/commandes/sumTotalOfCommandeByDay", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi le montant de Commande du jour",
            notes = "Cette méthode permet de chercher et renvoyer le montant de Commande du jour encours")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le montant des Commande par jour / somme nulle")
    })
    BigDecimal sumTotaleOfCommandeByDay();

    @GetMapping(value = APP_ROOT + "/commandes/sumTotalOfCommandeByMonth", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi le montant de Commande du moi",
            notes = "Cette méthode permet de chercher et renvoyer le montant de Commande du moi encours")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le montant des Commande par moi / somme nulle")
    })
    BigDecimal sumTotaleOfCommandeByMonth();

    @GetMapping(value = APP_ROOT + "/commandes/sumTotalOfCommandeByYear", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi le montant de Commande d'une années",
            notes = "Cette méthode permet de chercher et renvoyer le montant de Commande de l'année encours")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le montant des Commande par années / somme nulle")
    })
    BigDecimal sumTotaleOfCommandeByYear();

    @GetMapping(value = APP_ROOT + "/commandes/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des Commandes",
            notes = "Cette méthode permet de chercher et renvoyer la liste des Commandes", responseContainer = "List<CommandeDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des Commandes / une liste vide")
    })
    ResponseEntity<List<CommandeDto>> findAll();

    @GetMapping(value = APP_ROOT + "/commandes/searchAllComandesOrderByIdDesc", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des Articles par ordre descroissante",
            notes = "Cette méthode permet de chercher et renvoyer la liste des Articles par ordre descroissante",
            responseContainer = "List<CommandeDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des Articles  par ordre descroissante / une liste vide")
    })
    ResponseEntity<List<CommandeDto>> getAllCommandesOrderByIdDesc();

    @GetMapping(value = APP_ROOT + "/commandes/findListOrderByStatuePending", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des Commandes dont le status encours",
            notes = "Cette méthode permet de chercher et renvoyer la liste des Commandes dont le status est encours", responseContainer = "List<CommandeDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des Commandes / une liste vide")
    })
    ResponseEntity<List<CommandeDto>> getListOrderByStatusPending();

    @GetMapping(value = APP_ROOT + "/commandes/findListOrderByStatuePayed", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des Commandes payees",
            notes = "Cette méthode permet de chercher et renvoyer la liste des Commandes payees", responseContainer = "List<CommandeDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des Commandes payees / une liste vide")
    })
    ResponseEntity<List<CommandeDto>> getListOrderByStatusPayed();

    @GetMapping(value = APP_ROOT + "/commandes/searchCommandeByUserIdOrderByIdDesc/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des Commandes par user",
            notes = "Cette méthode permet de chercher et renvoyer la liste des Commandes par user", responseContainer = "List<CommandeDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des Commandes par user / une liste vide")
    })
    ResponseEntity<List<CommandeDto>> getCommandesByUserOrderByIdDesc(@PathVariable(name = "id") Long id);

    @GetMapping(value = APP_ROOT + "/commandes/searchCommandeByBillingAddressIdDesc/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des Commandes par user",
            notes = "Cette méthode permet de chercher et renvoyer la liste des Commandes par user", responseContainer = "List<CommandeDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des Commandes par user / une liste vide")
    })
    ResponseEntity<List<CommandeDto>> getCommandesByBillingAddressOrderByIdDesc(@PathVariable(name = "id") Long id);

 /*   @GetMapping(value = APP_ROOT + "/commandes/searchCommandeByShippingAddressIdDesc/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des Commandes par user",
            notes = "Cette méthode permet de chercher et renvoyer la liste des Commandes par user", responseContainer = "List<CommandeDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des Commandes par user / une liste vide")
    })
    ResponseEntity<List<CommandeDto>> getCommandesByShippingAddressByIdDesc(@PathVariable(name = "id") Long id);
*/
    @GetMapping(value = APP_ROOT + "/commandes/numberOfCommandeByDay", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste du nombre de Commandes par jour",
            notes = "Cette méthode permet de chercher et renvoyer la liste du nombre de Commandes par jour", responseContainer = "List<CommandeDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste du nombre de Commandes par jour / une liste vide")
    })
    List<?> countNumberOfCommandeByDay();

    @GetMapping(value = APP_ROOT + "/commandes/numberOfCommandeByMonth", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste du nombre de Commandes par moi",
            notes = "Cette méthode permet de chercher et renvoyer la liste du nombre de Commandes par moi", responseContainer = "List<CommandeDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste du nombre de Commandes par moi / une liste vide")
    })
    List<?> countNumberOfCommandeByMonth();

    @GetMapping(value = APP_ROOT + "/commandes/sumTotaleOfCommandeByMonthByList", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des sommes des Commandes par moi",
            notes = "Cette méthode permet de chercher et renvoyer liste des somme des Commandes par moi", responseContainer = "List<CommandeDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des sommes des Commandes par moi / une liste vide")
    })
    List<?> getSumTotaleOfCommandeByMonth();


    @GetMapping(value = APP_ROOT + "/commandes/sumTotaleOfCommandeByYearList", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des sommes des Commandes par années",
            notes = "Cette méthode permet de chercher et renvoyer liste des somme des Commandes par années", responseContainer = "List<CommandeDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des sommes des Commandes par années / une liste vide")
    })
    List<?> getSumTotalOfOrdersByYears();

    @GetMapping(value = APP_ROOT + "/commandes/searchCommandesByUtilisateurIdByPageables", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des Commande par client",
            notes = "Cette méthode permet de chercher et renvoyer la liste des Commande par Client par pages", responseContainer = "Page<CommandeDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des Commande par Client par pages / une liste vide")
    })
    Page<CommandeDto> getCommandesByUtilisateurIdByPageables(@RequestParam("userId") Long userId, @RequestParam(name = "page") int page,
                                                             @RequestParam(name = "size") int size);

    @DeleteMapping(value = APP_ROOT + "/commandes/delete/{idCommande}")
    @ApiOperation(value = "Supprimer une Commande par son ID",
            notes = "Cette méthode permet de supprimer une Commande par son ID", response = CommandeDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La CommandeDto a été supprimé")
    })
    void delete(@PathVariable("idCommande") Long id);
}
