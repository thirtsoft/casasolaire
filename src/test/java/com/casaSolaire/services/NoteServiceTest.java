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
    private NoteServiceImpl notificationService;

    @Mock
    private NoteRepository notificationRepository;

    @Test
    public void CreateNotificationTest() {
        UtilisateurDto utilisateurDto = new UtilisateurDto();
        utilisateurDto.setId(1L);
        utilisateurDto.setUsername("username");
        utilisateurDto.setPassword("passer123");

        ArticleDto articleDto = new ArticleDto();
        articleDto.setId(1L);
        articleDto.setReference("Art1");
        articleDto.setDesignation("Art1");

        NoteDto noteDto = NoteDto.builder()
                .id(1L)
                .reference("ref")
                .nombreEtoile("4etoile")
                .observation("bon")
                .articleDto(articleDto)
                .utilisateurDto(utilisateurDto)
                .build();
        Note notification = NoteDto.fromDtoToEntity(noteDto);
        when(notificationRepository.save(notification)).thenReturn(notification);

        NoteDto noteDtoSavedResult = notificationService.save(noteDto);

        verify(notificationRepository).save(notification);
        assertThat(noteDto).isNotNull();
//        assertThat(noteDtoSavedResult).isEqualTo(noteDto);
        assertThat(noteDtoSavedResult.getId()).isEqualTo(notification.getId());
        assertThat(noteDtoSavedResult.getReference()).isEqualTo(notification.getReference());
        assertThat(noteDtoSavedResult.getNombreEtoile()).isEqualTo(notification.getNombreEtoile());
    }

    @Test
    public void findAllTest() {
        UtilisateurDto utilisateurDto = UtilisateurDto.builder()
                .id(1L)
                .username("username")
                .password("passer123")
                .build();
        ArticleDto articleDto = ArticleDto.builder()
                .id(1L)
                .reference("Art1")
                .designation("Art1")
                .build();
        NoteDto noteDto = NoteDto.builder()
                .id(1L)
                .reference("ref")
                .nombreEtoile("4etoile")
                .observation("bon")
                .articleDto(articleDto)
                .utilisateurDto(utilisateurDto)
                .build();
        Note notification = NoteDto.fromDtoToEntity(noteDto);
        when(notificationRepository.findAll()).thenReturn(singletonList(notification));

        List<NoteDto> noteDtoList = notificationService.findAll();

        assertThat(noteDtoList).isNotNull();
        assertThat(noteDtoList.size()).isEqualTo(1);
        verify(notificationRepository).findAll();
        assertThat(noteDtoList.get(0)).isEqualTo(NoteDto.fromEntityToDto(notification));
    }

    @Test
    public void findByIdTest() {
        UtilisateurDto utilisateurDto = UtilisateurDto.builder()
                .id(1L)
                .username("username")
                .password("passer123")
                .build();
        ArticleDto articleDto = ArticleDto.builder()
                .id(1L)
                .reference("Art1")
                .designation("Art1")
                .build();
        NoteDto noteDto = NoteDto.builder()
                .id(1L)
                .reference("ref")
                .nombreEtoile("4etoile")
                .observation("bon")
                .articleDto(articleDto)
                .utilisateurDto(utilisateurDto)
                .build();
        Optional<Note> notification = Optional.ofNullable(NoteDto.fromDtoToEntity(noteDto));
        when(notificationRepository.findById(notification.get().getId())).thenReturn(notification);

        NoteDto noteDtoSavedResult = notificationService.findById(noteDto.getId());

        verify(notificationRepository).findById(notification.get().getId());
        assertThat(noteDto).isNotNull();
//        assertThat(noteDtoSavedResult).isEqualTo(noteDto);
        assertThat(noteDtoSavedResult.getId()).isEqualTo(notification.get().getId());

    }


}
