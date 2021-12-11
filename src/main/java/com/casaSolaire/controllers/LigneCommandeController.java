package com.casaSolaire.controllers;

import com.casaSolaire.controllers.api.LigneCommandeApi;
import com.casaSolaire.dto.LigneCommandeDto;
import com.casaSolaire.services.LigneCommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class LigneCommandeController implements LigneCommandeApi {

    private final LigneCommandeService ligneCommandeService;

    @Autowired
    public LigneCommandeController(LigneCommandeService ligneCommandeService) {
        this.ligneCommandeService = ligneCommandeService;
    }

    @Override
    public ResponseEntity<LigneCommandeDto> save(LigneCommandeDto ligneCommandeDto) {
        return ResponseEntity.ok(ligneCommandeService.save(ligneCommandeDto));
    }

    @Override
    public ResponseEntity<LigneCommandeDto> findById(Long id) {
        return ResponseEntity.ok(ligneCommandeService.findById(id));
    }

    @Override
    public ResponseEntity<List<LigneCommandeDto>> findAll() {
        List<LigneCommandeDto> ligneCommandeDtoList = ligneCommandeService.findAll();
        return new ResponseEntity<>(ligneCommandeDtoList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<LigneCommandeDto>> getAllLigneCommandeOrderByIdDesc() {
        List<LigneCommandeDto> ligneCommandeDtoList = ligneCommandeService.findByOrderByIdDesc();
        return new ResponseEntity<>(ligneCommandeDtoList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<LigneCommandeDto>> getArticlesGroupByProductIdOrderByCreatedDateDesc() {
        List<LigneCommandeDto> ligneCommandeDtoList = ligneCommandeService.findArticlesGroupByProductIdOrderByCreatedDateDesc();
        return new ResponseEntity<>(ligneCommandeDtoList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<LigneCommandeDto>> getAllLigneCommandesByCommandeId(Long comId) {
        List<LigneCommandeDto> ligneCommandeDtoList = ligneCommandeService.findListLigneCommandeByCommandeId(comId);
        return new ResponseEntity<>(ligneCommandeDtoList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<LigneCommandeDto>> getTop200LigneCommandesOrderByIdDesc() {
        List<LigneCommandeDto> ligneCommandeDtoList = ligneCommandeService.findTop200LigneCommandeOrderByIdDesc();
        return new ResponseEntity<>(ligneCommandeDtoList, HttpStatus.OK);
    }

    @Override
    public void delete(Long id) {
        ligneCommandeService.delete(id);
    }
}
