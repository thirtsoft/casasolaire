package com.casaSolaire.services;


import com.casaSolaire.dto.ClientDto;
import com.casaSolaire.models.Client;
import com.casaSolaire.repository.ClientRepository;
import com.casaSolaire.services.impl.ClientServiceImpl;
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
public class ClientServiceTest {

    @InjectMocks
    private ClientServiceImpl clientService;

    @Mock
    private ClientRepository clientRepository;

    @Test
    public void CreateClientTest() {
        ClientDto clientDto = ClientDto.builder()
                .id(1L)
                .firstName("CLT")
                .lastName("CLT")
                .email("CLT")
                .build();
        Client client = ClientDto.fromDtoToEntity(clientDto);
        when(clientRepository.save(client)).thenReturn(client);

        ClientDto clientDtoSavedResult = clientService.save(clientDto);

        verify(clientRepository).save(client);
        assertThat(clientDto).isNotNull();
        assertThat(clientDtoSavedResult).isEqualTo(clientDto);
        assertThat(clientDtoSavedResult.getId()).isEqualTo(client.getId());
        assertThat(clientDtoSavedResult.getFirstName()).isEqualTo(client.getFirstName());
    }

    @Test
    public void findAllTest() {
        ClientDto clientDto = ClientDto.builder()
                .id(1L)
                .firstName("CLT")
                .lastName("CLT")
                .email("CLT")
                .build();
        Client client = ClientDto.fromDtoToEntity(clientDto);

        when(clientRepository.findAll()).thenReturn(singletonList(client));

        List<ClientDto> clientDtoList = clientService.findAll();

        assertThat(clientDtoList).isNotNull();
        assertThat(clientDtoList.size()).isEqualTo(1);
        verify(clientRepository).findAll();
        assertThat(clientDtoList.get(0)).isEqualTo(ClientDto.fromEntityToDto(client));
    }

    @Test
    public void findByIdTest() {
        ClientDto clientDto = ClientDto.builder()
                .id(1L)
                .firstName("CLT")
                .lastName("CLT")
                .email("CLT")
                .build();
        Optional<Client> client = Optional.ofNullable(ClientDto.fromDtoToEntity(clientDto));
        when(clientRepository.findById(client.get().getId())).thenReturn(client);

        ClientDto clientDtoSavedResult = clientService.findById(clientDto.getId());

        verify(clientRepository).findById(client.get().getId());
        assertThat(clientDto).isNotNull();
        assertThat(clientDtoSavedResult).isEqualTo(clientDto);
        assertThat(clientDtoSavedResult.getId()).isEqualTo(client.get().getId());

    }


}
