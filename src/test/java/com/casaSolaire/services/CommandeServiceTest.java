package com.casaSolaire.services;

import com.casaSolaire.dto.ClientDto;
import com.casaSolaire.dto.CommandeDto;
import com.casaSolaire.enums.Status;
import com.casaSolaire.models.Commande;
import com.casaSolaire.repository.CommandeRepository;
import com.casaSolaire.services.impl.CommandeServiceImpl;
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
public class CommandeServiceTest {

    @InjectMocks
    private CommandeServiceImpl commandeService;

    @Mock
    private CommandeRepository commandeRepository;

    @Test
    public void CreateCommanderTest() {
        ClientDto clientDto = ClientDto.builder()
                .id(1L)
                .reference("Art")
                .firstName("CLT")
                .lastName("CLT")
                .build();
        CommandeDto commandeDto = CommandeDto.builder()
                .id(1L)
                .number("COM12")
                .total(230000)
                .status(Status.PAYEE)
                .clientDto(clientDto)
                .build();
        Commande commander = CommandeDto.fromDtoToEntity(commandeDto);
        when(commandeRepository.save(commander)).thenReturn(commander);

        CommandeDto commandeDtoSavedResult = commandeService.save(commandeDto);

        verify(commandeRepository).save(commander);
        assertThat(commandeDto).isNotNull();
        //    assertThat(commandeDtoSavedResult).isEqualTo(commandeDto);
        assertThat(commandeDtoSavedResult.getId()).isEqualTo(commander.getId());
        assertThat(commandeDtoSavedResult.getNumber()).isEqualTo(commander.getNumber());
        assertThat(commandeDtoSavedResult.getClientDto()).isEqualTo(commander.getClient());
    }

    @Test
    public void findAllTest() {
        ClientDto clientDto = ClientDto.builder()
                .id(1L)
                .reference("CLT")
                .firstName("CLT")
                .lastName("CLT")
                .build();
        CommandeDto commandeDto = CommandeDto.builder()
                .id(1L)
                .number("COM23")
                .total(350000)
                .status(Status.VALIDEE)
                .clientDto(clientDto)
                .build();
        Commande commander = CommandeDto.fromDtoToEntity(commandeDto);
        when(commandeRepository.findAll()).thenReturn(singletonList(commander));

        List<CommandeDto> commandeDtoList = commandeService.findAll();

        assertThat(commandeDtoList).isNotNull();
        assertThat(commandeDtoList.size()).isEqualTo(1);
        verify(commandeRepository).findAll();
        assertThat(commandeDtoList.get(0)).isEqualTo(CommandeDto.fromEntityToDto(commander));
    }

    @Test
    public void findByIdTest() {
        ClientDto clientDto = ClientDto.builder()
                .id(1L)
                .reference("CLT")
                .firstName("CLT")
                .lastName("CLT")
                .build();
        CommandeDto commandeDto = CommandeDto.builder()
                .id(1L)
                .number("Com120")
                .total(120000)
                .status(Status.ENCOURS)
                .clientDto(clientDto)
                .build();

        Optional<Commande> commander = Optional.ofNullable(CommandeDto.fromDtoToEntity(commandeDto));
        when(commandeRepository.findById(commander.get().getId())).thenReturn(commander);

        CommandeDto commanderDtoSavedResult = commandeService.findById(commandeDto.getId());

        verify(commandeRepository).findById(commander.get().getId());
        assertThat(commandeDto).isNotNull();
        //    assertThat(commanderDtoSavedResult).isEqualTo(commandeDto);
        assertThat(commanderDtoSavedResult.getId()).isEqualTo(commander.get().getId());

    }


}
