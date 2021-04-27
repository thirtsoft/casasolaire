package com.casaSolaire.services;

import com.casaSolaire.dto.ArticleDto;
import com.casaSolaire.dto.NoteDto;
import com.casaSolaire.dto.UtilisateurDto;
import com.casaSolaire.models.Note;
import com.casaSolaire.repository.NoteRepository;
import com.casaSolaire.services.impl.NoteServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.Optional;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class NoteServiceTest {

    @InjectMocks
    private NoteServiceImpl noteService;

    @Mock
    private NoteRepository noteRepository;

    @Test
    public void CreateFournisseurTest() {
        ArticleDto articleDto = ArticleDto.builder()
                .id(1L)
                .reference("Art")
                .designation("Art1")
                .build();
        UtilisateurDto utilisateurDto = UtilisateurDto.builder()
                .id(1L)
                .name("name")
                .username("username")
                .password("passer123")
                .build();
        NoteDto noteDto = NoteDto.builder()
                .id(1L)
                .reference("note1")
                .nombreEtoile("4etoile")
                .articleDto(articleDto)
                .utilisateurDto(utilisateurDto)
                .build();
        Note note = NoteDto.fromDtoToEntity(noteDto);
        when(noteRepository.save(note)).thenReturn(note);

        NoteDto fournisseurDtoSavedResult = noteService.save(noteDto);

        verify(noteRepository).save(note);
        assertThat(noteDto).isNotNull();
        //       assertThat(fournisseurDtoSavedResult).isEqualTo(fournisseurDto);
        assertThat(fournisseurDtoSavedResult.getId()).isEqualTo(note.getId());
        assertThat(fournisseurDtoSavedResult.getReference()).isEqualTo(note.getReference());
        assertThat(fournisseurDtoSavedResult.getNombreEtoile()).isEqualTo(note.getNombreEtoile());
    }

    @Test
    public void findAllTest() {
        ArticleDto articleDto = ArticleDto.builder()
                .id(1L)
                .reference("Art")
                .designation("Art1")
                .build();
        UtilisateurDto utilisateurDto = UtilisateurDto.builder()
                .id(1L)
                .name("name")
                .username("username")
                .password("passer123")
                .build();
        NoteDto noteDto = NoteDto.builder()
                .id(1L)
                .reference("note1")
                .nombreEtoile("4etoile")
                .articleDto(articleDto)
                .utilisateurDto(utilisateurDto)
                .build();
        Note note = NoteDto.fromDtoToEntity(noteDto);
        when(noteRepository.findAll()).thenReturn(singletonList(note));

        List<NoteDto> fournisseurDtoList = noteService.findAll();

        assertThat(fournisseurDtoList).isNotNull();
        assertThat(fournisseurDtoList.size()).isEqualTo(1);
        verify(noteRepository).findAll();
        assertThat(fournisseurDtoList.get(0)).isEqualTo(NoteDto.fromEntityToDto(note));
    }

    @Test
    public void findByIdTest() {
        ArticleDto articleDto = ArticleDto.builder()
                .id(1L)
                .reference("Art")
                .designation("Art1")
                .build();
        UtilisateurDto utilisateurDto = UtilisateurDto.builder()
                .id(1L)
                .name("name")
                .username("username")
                .password("passer123")
                .build();
        NoteDto noteDto = NoteDto.builder()
                .id(1L)
                .reference("note1")
                .nombreEtoile("4etoile")
                .articleDto(articleDto)
                .utilisateurDto(utilisateurDto)
                .build();
        Optional<Note> note = Optional.ofNullable(NoteDto.fromDtoToEntity(noteDto));
        when(noteRepository.findById(note.get().getId())).thenReturn(note);

        NoteDto fournisseurDtoSavedResult = noteService.findById(noteDto.getId());

        verify(noteRepository).findById(note.get().getId());
        assertThat(noteDto).isNotNull();
        //     assertThat(fournisseurDtoSavedResult).isEqualTo(fournisseurDto);
        assertThat(fournisseurDtoSavedResult.getId()).isEqualTo(note.get().getId());

    }


}
