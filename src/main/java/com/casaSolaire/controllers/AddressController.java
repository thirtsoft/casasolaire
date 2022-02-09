package com.casaSolaire.controllers;

import com.casaSolaire.controllers.api.AddressApi;
import com.casaSolaire.dto.AddressDto;
import com.casaSolaire.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class AddressController implements AddressApi {

    private final AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }


    @Override
    public ResponseEntity<AddressDto> save(AddressDto addressDto) {
        return ResponseEntity.ok(addressService.save(addressDto));
    }

    @Override
    public ResponseEntity<AddressDto> findById(Long id) {
        return ResponseEntity.ok(addressService.findById(id));
    }

    @Override
    public List<AddressDto> findAll() {
        return addressService.findAll();
    }

    @Override
    public ResponseEntity<List<AddressDto>> getAllAddressesOrderByIdDesc() {
        List<AddressDto> addressDtoList = addressService.findByOrderByIdDesc();
        return new ResponseEntity<>(addressDtoList, HttpStatus.OK);
    }

    @Override
    public void delete(Long id) {
        addressService.delete(id);
    }
}
