package com.casaSolaire.services.impl;

import com.casaSolaire.dto.NoteDto;
import com.casaSolaire.exceptions.ResourceNotFoundException;
import com.casaSolaire.models.Note;
import com.casaSolaire.repository.NoteRepository;
import com.casaSolaire.services.NoteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class NoteServiceImpl implements NoteService {

    @Autowired
    private final NoteRepository noteRepository;

    public NoteServiceImpl(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }


    @Override
    public NoteDto save(NoteDto noteDto) {
        return NoteDto.fromEntityToDto(
                noteRepository.save(
                        NoteDto.fromDtoToEntity(noteDto)
                )
        );

    }

    @Override
    public NoteDto findById(Long id) {
        if (id == null) {
            log.error("Note Id is null");
            return null;
        }

        Optional<Note> note = noteRepository.findById(id);

        return Optional.of(NoteDto.fromEntityToDto(note.get())).orElseThrow(() ->
                new ResourceNotFoundException(
                        "Not note with l'Id = " + id + "n'a été found")
        );
    }

    @Override
    public List<NoteDto> findAll() {
        return noteRepository.findAll().stream()
                .map(NoteDto::fromEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        if (id == null) {
            log.error("Note Id is null");
            return;
        }
        noteRepository.deleteById(id);

    }
}
