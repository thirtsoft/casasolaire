package com.casaSolaire.services.impl;

import com.casaSolaire.dto.AddressDto;
import com.casaSolaire.exceptions.ResourceNotFoundException;
import com.casaSolaire.models.Address;
import com.casaSolaire.repository.AddressRepository;
import com.casaSolaire.services.AddressService;
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
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }


    @Override
    public AddressDto save(AddressDto addressDto) {
        return AddressDto.fromEntityToDto(
                addressRepository.save(
                        AddressDto.fromDtoToEntity(addressDto)
                )
        );

    }

    @Override
    public AddressDto findById(Long id) {
        if (id == null) {
            log.error("Address Id is null");
            return null;
        }

        Optional<Address> address = addressRepository.findById(id);

        return Optional.of(AddressDto.fromEntityToDto(address.get())).orElseThrow(() ->
                new ResourceNotFoundException(
                        "Not address with l'Id = " + id + "n'a été found")
        );
    }

    @Override
    public List<AddressDto> findAll() {
        return addressRepository.findAll().stream()
                .map(AddressDto::fromEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        if (id == null) {
            log.error("Address Id is null");
            return;
        }
        addressRepository.deleteById(id);

    }
}
