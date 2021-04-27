package com.casaSolaire.controllers.api;

import com.casaSolaire.dto.NoteDto;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.casaSolaire.utils.Constants.APP_ROOT;

public interface NoteApi {

    @PostMapping(value = APP_ROOT + "/notes/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<NoteDto> save(@RequestBody NoteDto noteDto);

    @GetMapping(value = APP_ROOT + "/notes/{idNote}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<NoteDto> findById(@PathVariable("idNote") Long id);

    @GetMapping(value = APP_ROOT + "/notes/all", produces = MediaType.APPLICATION_JSON_VALUE)
    List<NoteDto> findAll();

    @DeleteMapping(value = APP_ROOT + "/notes/delete/{idNote}")
    void delete(@PathVariable("idNote") Long id);
}
