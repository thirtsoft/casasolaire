package com.casaSolaire.controllers;

import com.casaSolaire.controllers.api.StateApi;
import com.casaSolaire.dto.StateDto;
import com.casaSolaire.services.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class StateController implements StateApi {

    private final StateService stateService;

    @Autowired
    public StateController(StateService stateService) {
        this.stateService = stateService;
    }


    @Override
    public ResponseEntity<StateDto> save(StateDto stateDto) {
        return ResponseEntity.ok(stateService.save(stateDto));
    }

    @Override
    public ResponseEntity<StateDto> update(Long id, StateDto stateDto) {
        stateDto.setId(id);
        return ResponseEntity.ok(stateService.save(stateDto));
    }

    @Override
    public ResponseEntity<StateDto> findById(Long id) {
        return ResponseEntity.ok(stateService.findById(id));
    }

    @Override
    public ResponseEntity<StateDto> findByDesignation(String designation) {
        return null;
    }

    @Override
    public List<StateDto> findAll() {
        return stateService.findAll();
    }

    @Override
    public ResponseEntity<List<StateDto>> getAllStatesOrderByIdDesc() {
        List<StateDto> stateDtoList = stateService.findByOrderByIdDesc();
        return new ResponseEntity<>(stateDtoList, HttpStatus.OK);
    }

    @Override
    public List<StateDto> getAllStateByCountryCode(String code) {
        return stateService.findAllStateByCountryCode(code);
    }

    @Override
    public void delete(Long id) {
        stateService.delete(id);
    }

}
