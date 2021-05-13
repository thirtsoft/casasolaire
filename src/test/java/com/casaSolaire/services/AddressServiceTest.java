package com.casaSolaire.services;


import com.casaSolaire.dto.AddressDto;
import com.casaSolaire.dto.ClientDto;
import com.casaSolaire.models.Address;
import com.casaSolaire.repository.AddressRepository;
import com.casaSolaire.services.impl.AddressServiceImpl;
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
public class AddressServiceTest {

    @InjectMocks
    private AddressServiceImpl addresseClientService;

    @Mock
    private AddressRepository addresseClientRepository;

    @Test
    public void CreateAddressClientTest() {
        ClientDto clientDto = ClientDto.builder()
                .id(1L)
                .reference("CLT")
                .firstName("CLT")
                .lastName("CLT")
                .build();
        AddressDto addressClientDto = AddressDto.builder()
                .id(1L)
                .city("city")
                .country("country")
                .build();
        Address addressClient = AddressDto.fromDtoToEntity(addressClientDto);
        when(addresseClientRepository.save(addressClient)).thenReturn(addressClient);

        AddressDto addressClientDtoSavedResult = addresseClientService.save(addressClientDto);

        verify(addresseClientRepository).save(addressClient);
        assertThat(addressClientDto).isNotNull();
        //    assertThat(addressClientDtoSavedResult).isEqualTo(addressClientDto);
        assertThat(addressClientDtoSavedResult.getId()).isEqualTo(addressClientDto.getId());
        assertThat(addressClientDtoSavedResult.getCity()).isEqualTo(addressClientDto.getCity());

    }

    @Test
    public void findAllTest() {
        ClientDto clientDto = ClientDto.builder()
                .id(1L)
                .reference("CLT")
                .firstName("CLT")
                .lastName("CLT")
                .build();
        AddressDto addressClientDto = AddressDto.builder()
                .id(1L)
                .city("city")
                .country("country")
                .build();
        Address addressClient = AddressDto.fromDtoToEntity(addressClientDto);
        when(addresseClientRepository.findAll()).thenReturn(singletonList(addressClient));

        List<AddressDto> addressClientDtoList = addresseClientService.findAll();

        assertThat(addressClientDtoList).isNotNull();
        assertThat(addressClientDtoList.size()).isEqualTo(1);
        verify(addresseClientRepository).findAll();
        assertThat(addressClientDtoList.get(0)).isEqualTo(AddressDto.fromEntityToDto(addressClient));
    }

    @Test
    public void findByIdTest() {
        ClientDto clientDto = ClientDto.builder()
                .id(1L)
                .reference("CLT")
                .firstName("CLT")
                .lastName("CLT")
                .build();
        AddressDto addressClientDto = AddressDto.builder()
                .id(1L)
                .city("city")
                .country("country")
                .build();
        Optional<Address> addressClient = Optional.ofNullable(AddressDto.fromDtoToEntity(addressClientDto));
        when(addresseClientRepository.findById(addressClient.get().getId())).thenReturn(addressClient);

        AddressDto addressClientDtoSavedResult = addresseClientService.findById(addressClientDto.getId());

        verify(addresseClientRepository).findById(addressClient.get().getId());
        assertThat(addressClientDto).isNotNull();
        //    assertThat(addressClientDtoSavedResult).isEqualTo(addressClientDto);
        assertThat(addressClientDtoSavedResult.getId()).isEqualTo(addressClient.get().getId());

    }


}
