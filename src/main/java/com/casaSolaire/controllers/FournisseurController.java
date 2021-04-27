package com.casaSolaire.controllers;

import com.casaSolaire.controllers.api.FournisseurApi;
import com.casaSolaire.dto.FournisseurDto;
import com.casaSolaire.services.FournisseurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FournisseurController implements FournisseurApi {

    private final FournisseurService fournisseurService;

    @Autowired
    public FournisseurController(FournisseurService fournisseurService) {
        this.fournisseurService = fournisseurService;
    }


    @Override
    public ResponseEntity<FournisseurDto> save(FournisseurDto fournisseurDto) {
        return ResponseEntity.ok(fournisseurService.save(fournisseurDto));
    }

    @Override
    public ResponseEntity<FournisseurDto> findById(Long id) {
        return ResponseEntity.ok(fournisseurService.findById(id));
    }

    @Override
    public List<FournisseurDto> findAll() {
        return fournisseurService.findAll();
    }

    @Override
    public void delete(Long id) {
        fournisseurService.delete(id);
    }
}
