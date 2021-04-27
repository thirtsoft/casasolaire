package com.casaSolaire.controllers;

import com.casaSolaire.controllers.api.NoteApi;
import com.casaSolaire.dto.NoteDto;
import com.casaSolaire.services.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class NoteController implements NoteApi {

    private final NoteService noteService;

    @Autowired
    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }


    @Override
    public ResponseEntity<NoteDto> save(NoteDto noteDto) {
        return ResponseEntity.ok(noteService.save(noteDto));
    }

    @Override
    public ResponseEntity<NoteDto> findById(Long id) {
        return ResponseEntity.ok(noteService.findById(id));
    }

    @Override
    public List<NoteDto> findAll() {
        return noteService.findAll();
    }

    @Override
    public void delete(Long id) {
        noteService.delete(id);
    }
}
