package com.casaSolaire.controllers;

import com.casaSolaire.controllers.api.CheckoutApi;
import com.casaSolaire.dto.UtilisateurDto;
import com.casaSolaire.dto.checkout.Purchase;
import com.casaSolaire.dto.checkout.PurchaseResponse;
import com.casaSolaire.models.Utilisateur;
import com.casaSolaire.services.CheckoutService;
import com.casaSolaire.services.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@CrossOrigin
@RestController
public class CheckoutController implements CheckoutApi {

    private final CheckoutService checkoutService;

    private final UtilisateurService utilisateurService;

    @Autowired
    public CheckoutController(CheckoutService checkoutService,

                              UtilisateurService utilisateurService) {
        this.checkoutService = checkoutService;
        this.utilisateurService = utilisateurService;
    }

    @Override
    public ResponseEntity<PurchaseResponse> purchase(Purchase purchase) {
        PurchaseResponse response = this.checkoutService.placeToOrder(purchase);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PurchaseResponse> purchaseWithUser(Purchase purchase, Long id) {
        UtilisateurDto utilisateurDto = Optional.of(utilisateurService.findById(id)).get();
        Utilisateur utilisateur = UtilisateurDto.fromDtoToEntity(utilisateurDto);

        purchase.setUtilisateur(utilisateur);

        PurchaseResponse response = this.checkoutService.placeToOrderWithUser(purchase);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PurchaseResponse> purchaseWithLoginUser(Purchase purchase, Long id) {

        UtilisateurDto utilisateurDto = Optional.of(utilisateurService.findById(id)).get();

        Utilisateur utilisateur = UtilisateurDto.fromDtoToEntity(utilisateurDto);

        purchase.setUtilisateur(utilisateur);

        PurchaseResponse response = this.checkoutService.placeToOrderWithUser(purchase);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
