package com.casaSolaire.controllers;

import com.casaSolaire.controllers.api.ClientApi;
import com.casaSolaire.dto.ClientDto;
import com.casaSolaire.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ClientController implements ClientApi {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }


    @Override
    public ResponseEntity<ClientDto> save(ClientDto clientDto) {
        return ResponseEntity.ok(clientService.save(clientDto));
    }

    @Override
    public ResponseEntity<ClientDto> findById(Long id) {
        return ResponseEntity.ok(clientService.findById(id));
    }

    @Override
    public List<ClientDto> findAll() {
        return clientService.findAll();
    }

    @Override
    public void delete(Long id) {
        clientService.delete(id);
    }
}
