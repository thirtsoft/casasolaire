package com.casaSolaire.repository;

import com.casaSolaire.models.Address;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AddressRepositoryTest {

    @Autowired
    private AddressRepository addressRepository;

    @Test
    @Rollback(false)
    public void testCreateAddress() {
        String code = "Add1";
        String city = "SEN";
        String rue = "Rue1";
        String town = "DK";
        String country = "Africa";
        Address addressDto = new Address();
        addressDto.setCode(code);
        addressDto.setCity(city);
        addressDto.setRue(rue);
        addressDto.setTown(town);
        addressDto.setCountry(country);

        Address addressDtoResult = addressRepository.save(addressDto);

        assertNotNull(addressDtoResult);

    }

    @Test
    @Rollback(false)
    public void TestUpdateAddress() {
        String code = "Add1";
        String city = "SEN";
        String rue = "Rue1";
        String town = "DK";
        String country = "Africa";
        Address addressDto = new Address();
        addressDto.setCode(code);
        addressDto.setCity(city);
        addressDto.setRue(rue);
        addressDto.setTown(town);
        addressDto.setCountry(country);

        Address addressDtoResult = addressRepository.save(addressDto);

        String town1 = "ZG";
        String country1 = "Africa";
        addressDto.setTown(town1);
        addressDto.setCountry(country1);
        addressDto.setId((long) 4);

        addressRepository.save(addressDto);

        assertThat(addressDto.getTown()).isEqualTo(town1);

    }

    @Test
    public void testFindById() {
        String code = "Add1";
        String city = "SEN";
        String rue = "Rue1";
        String town = "DK";
        String country = "Africa";
        Address addressDto = new Address();
        addressDto.setCode(code);
        addressDto.setCity(city);
        addressDto.setRue(rue);
        addressDto.setTown(town);
        addressDto.setCountry(country);

        Address addressDtoResult = addressRepository.save(addressDto);

        boolean isExitAddress = addressRepository.findById(addressDtoResult.getId()).isPresent();

        assertTrue(isExitAddress);

    }

    @Test
    public void testFindAll() {
        String code = "Add1";
        String city = "SEN";
        String rue = "Rue1";
        String town = "DK";
        String country = "Africa";
        Address addressDto = new Address();
        addressDto.setCode(code);
        addressDto.setCity(city);
        addressDto.setRue(rue);
        addressDto.setTown(town);
        addressDto.setCountry(country);

        Address addressDtoResult = addressRepository.save(addressDto);

        String code1 = "Add1";
        String city1 = "SEN";
        String rue1 = "Rue1";
        String town1 = "DK";
        String country1 = "Africa";
        Address addressDto1 = new Address();
        addressDto1.setCode(code1);
        addressDto1.setCity(city1);
        addressDto1.setRue(rue1);
        addressDto1.setTown(town1);
        addressDto1.setCountry(country1);

        Address addressDtoResult1 = addressRepository.save(addressDto1);

        List<Address> addresses = addressRepository.findAll();

        assertThat(addresses).size().isGreaterThan(1);

    }

    @Test
    @Rollback(false)
    public void testDelete() {
        String code = "Add1";
        String city = "SEN";
        String rue = "Rue1";
        String town = "DK";
        String country = "Africa";
        Address addressDto = new Address();
        addressDto.setCode(code);
        addressDto.setCity(city);
        addressDto.setRue(rue);
        addressDto.setTown(town);
        addressDto.setCountry(country);

        Address addressDtoResult = addressRepository.save(addressDto);

        boolean isExistBeforeDelete = addressRepository.findById(addressDtoResult.getId()).isPresent();

        addressRepository.deleteById(addressDtoResult.getId());

        boolean notExistAfterDelete = addressRepository.findById(addressDtoResult.getId()).isPresent();

        assertTrue(isExistBeforeDelete);

        assertFalse(notExistAfterDelete);

    }

}
