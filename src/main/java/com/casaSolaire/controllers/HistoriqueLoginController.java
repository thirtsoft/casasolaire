package com.casaSolaire.controllers;

import com.casaSolaire.controllers.api.HistoriqueLoginApi;
import com.casaSolaire.dto.HistoriqueLoginDto;
import com.casaSolaire.services.HistoriqueLoginService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@CrossOrigin
@RestController
public class HistoriqueLoginController implements HistoriqueLoginApi {

    private final HistoriqueLoginService historiqueLoginService;

    public HistoriqueLoginController(HistoriqueLoginService historiqueLoginService) {
        this.historiqueLoginService = historiqueLoginService;
    }

    @Override
    public ResponseEntity<HistoriqueLoginDto> save(HistoriqueLoginDto historiqueLoginDto) {
        HistoriqueLoginDto historiqueLoginDtoResult = historiqueLoginService.saveHistoriqueLogin(historiqueLoginDto);
        return new ResponseEntity<>(historiqueLoginDtoResult, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<HistoriqueLoginDto> update(Long id, HistoriqueLoginDto historiqueLoginDto) {
        return null;
    }

    @Override
    public ResponseEntity<HistoriqueLoginDto> findById(Long id) {
        HistoriqueLoginDto historiqueLoginDto = historiqueLoginService.findHistoriqueLoginById(id);
        return new ResponseEntity<>(historiqueLoginDto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<HistoriqueLoginDto>> findAll() {
        List<HistoriqueLoginDto> historiqueLoginDtos = historiqueLoginService.findAllHistoriqueLogins();
        return new ResponseEntity<>(historiqueLoginDtos, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<HistoriqueLoginDto>> getAllHistoriqueLoginDtoOrderByIdDesc() {
        List<HistoriqueLoginDto> historiqueLoginDtos = historiqueLoginService.findAllHistoriqueLoginsOrderDesc();
        return new ResponseEntity<>(historiqueLoginDtos, HttpStatus.OK);
    }

    @Override
    public BigDecimal countNumberOfHistoriqueLogins() {
        return historiqueLoginService.countNumberOfHistoriqueLogins();
    }

    @Override
    public void delete(Long id) {
        historiqueLoginService.deleteHistoriqueLogin(id);
    }


}
