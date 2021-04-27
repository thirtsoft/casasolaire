package com.casaSolaire.services;

import com.casaSolaire.dto.AddressDto;
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
    private AddressServiceImpl addressService;

    @Mock
    private AddressRepository addressRepository;

    @Test
    public void CreateAddressTest() {
        AddressDto addressDto = AddressDto.builder()
                .id(1L)
                .code("Add")
                .city("DK")
                .town("Mariste")
                .build();
        Address address = AddressDto.fromDtoToEntity(addressDto);
        when(addressRepository.save(address)).thenReturn(address);

        AddressDto AddressDtoSavedResult = addressService.save(addressDto);

        verify(addressRepository).save(address);
        assertThat(addressDto).isNotNull();
        assertThat(AddressDtoSavedResult).isEqualTo(addressDto);
        assertThat(AddressDtoSavedResult.getId()).isEqualTo(address.getId());
        assertThat(AddressDtoSavedResult.getCity()).isEqualTo(address.getCity());
        assertThat(AddressDtoSavedResult.getTown()).isEqualTo(address.getTown());
    }

    @Test
    public void findAllTest() {
        AddressDto addressDto = com.casaSolaire.dto.AddressDto.builder()
                .id(1L)
                .code("ADD")
                .city("DK")
                .country("SEN")
                .build();
        Address address = AddressDto.fromDtoToEntity(addressDto);

        when(addressRepository.findAll()).thenReturn(singletonList(address));

        List<AddressDto> addressDtoList = addressService.findAll();

        assertThat(addressDtoList).isNotNull();
        assertThat(addressDtoList.size()).isEqualTo(1);
        verify(addressRepository).findAll();
        assertThat(addressDtoList.get(0)).isEqualTo(AddressDto.fromEntityToDto(address));
    }

    @Test
    public void findByIdTest() {
        AddressDto addressDto = AddressDto.builder()
                .id(1L)
                .country("SEN")
                .town("DK")
                .build();
        Optional<Address> address = Optional.ofNullable(AddressDto.fromDtoToEntity(addressDto));
        when(addressRepository.findById(address.get().getId())).thenReturn(address);

        AddressDto addressDtoSavedResult = addressService.findById(addressDto.getId());

        verify(addressRepository).findById(address.get().getId());
        assertThat(addressDto).isNotNull();
        assertThat(addressDtoSavedResult).isEqualTo(addressDto);
        assertThat(addressDtoSavedResult.getId()).isEqualTo(address.get().getId());

    }


}
