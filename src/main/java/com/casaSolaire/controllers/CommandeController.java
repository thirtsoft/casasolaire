package com.casaSolaire.controllers;

import com.casaSolaire.controllers.api.CommandeApi;
import com.casaSolaire.dto.CommandeDto;
import com.casaSolaire.services.CommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CommandeController implements CommandeApi {

    private final CommandeService commandeService;

    @Autowired
    public CommandeController(CommandeService commandeService) {
        this.commandeService = commandeService;
    }


    @Override
    public ResponseEntity<CommandeDto> save(CommandeDto ommandeDto) {
        return ResponseEntity.ok(commandeService.save(ommandeDto));
    }

    @Override
    public ResponseEntity<CommandeDto> findById(Long id) {
        return ResponseEntity.ok(commandeService.findById(id));
    }

    @Override
    public List<CommandeDto> findAll() {
        return commandeService.findAll();
    }

    @Override
    public void delete(Long id) {
        commandeService.delete(id);
    }
}
