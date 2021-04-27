package com.casaSolaire.services;

import com.casaSolaire.dto.UtilisateurDto;
import com.casaSolaire.models.Utilisateur;
import com.casaSolaire.repository.UtilisateurRepository;
import com.casaSolaire.services.impl.UtilisateurServiceImpl;
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
public class UtilisateurServiceTest {

    @InjectMocks
    private UtilisateurServiceImpl utilisateurService;

    @Mock
    private UtilisateurRepository utilisateurRepository;

    @Test
    public void CreateUserTest() {
        UtilisateurDto utilisateurDto = UtilisateurDto.builder()
                .id(1L)
                .name("name")
                .username("username")
                .mobile("mobile")
                .password("passer123")
                .build();
        Utilisateur utilisateur = UtilisateurDto.fromDtoToEntity(utilisateurDto);
        when(utilisateurRepository.save(utilisateur)).thenReturn(utilisateur);

        UtilisateurDto utilisateurDtoSavedResult = utilisateurService.save(utilisateurDto);

        verify(utilisateurRepository).save(utilisateur);
        assertThat(utilisateurDto).isNotNull();
        //      assertThat(utilisateurDtoSavedResult).isEqualTo(utilisateurDto);
        assertThat(utilisateurDtoSavedResult.getId()).isEqualTo(utilisateur.getId());
        assertThat(utilisateurDtoSavedResult.getName()).isEqualTo(utilisateur.getName());
        //    assertThat(utilisateurDtoSavedResult.getUsername()).isEqualTo(utilisateur.getUsername());
    }

    @Test
    public void findAllTest() {
        UtilisateurDto utilisateurDto = UtilisateurDto.builder()
                .id(1L)
                .name("name")
                .username("username")
                .mobile("mobile")
                .password("passer123")
                .build();
        Utilisateur utilisateur = UtilisateurDto.fromDtoToEntity(utilisateurDto);

        when(utilisateurRepository.findAll()).thenReturn(singletonList(utilisateur));

        List<UtilisateurDto> utilisateurDtoList = utilisateurService.findAll();

        assertThat(utilisateurDtoList).isNotNull();
        assertThat(utilisateurDtoList.size()).isEqualTo(1);
        verify(utilisateurRepository).findAll();
        assertThat(utilisateurDtoList.get(0)).isEqualTo(UtilisateurDto.fromEntityToDto(utilisateur));
    }

    @Test
    public void findByIdTest() {
        UtilisateurDto utilisateurDto = UtilisateurDto.builder()
                .id(1L)
                .name("name")
                .username("username")
                .mobile("mobile")
                .password("passer123")
                .build();

        Optional<Utilisateur> utilisateur = Optional.ofNullable(UtilisateurDto.fromDtoToEntity(utilisateurDto));
        when(utilisateurRepository.findById(utilisateur.get().getId())).thenReturn(utilisateur);

        UtilisateurDto utilisateurDtoSavedResult = utilisateurService.findById(utilisateurDto.getId());

        verify(utilisateurRepository).findById(utilisateur.get().getId());
        assertThat(utilisateurDto).isNotNull();
        //    assertThat(utilisateurDtoSavedResult).isEqualTo(utilisateurDto);
        assertThat(utilisateurDtoSavedResult.getId()).isEqualTo(utilisateur.get().getId());

    }


}
