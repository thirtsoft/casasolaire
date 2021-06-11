package com.casaSolaire.services;

import com.casaSolaire.dto.NoteDto;

import java.util.List;

public interface NoteService {

    NoteDto save(NoteDto noteDto);

    NoteDto update(Long id, NoteDto noteDto);

    NoteDto findById(Long id);

    List<NoteDto> findAll();

    void delete(Long id);
}
