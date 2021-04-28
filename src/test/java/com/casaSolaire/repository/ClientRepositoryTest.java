package com.casaSolaire.repository;

import com.casaSolaire.models.Client;
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
public class ClientRepositoryTest {

    @Autowired
    private ClientRepository clientRepository;

    @Test
    @Rollback(false)
    public void testCreateClient() {
        String reference = "CLT1";
        String firstName = "tairou";
        String lastName = "diallo";
        String email = "thirdiallo@gmail.com";
        String mobile = "779440310";
        String address = "USA";
        Client clientDto = new Client();
        clientDto.setReference(reference);
        clientDto.setFirstName(firstName);
        clientDto.setLastName(lastName);
        clientDto.setEmail(email);
        clientDto.setMobile(mobile);
        clientDto.setAddress(address);

        Client clientDtoResult = clientRepository.save(clientDto);

        assertNotNull(clientDtoResult);

    }

    @Test
    @Rollback(false)
    public void TestUpdateClient() {
        String reference = "CLT1";
        String firstName = "tairou";
        String lastName = "diallo";
        String email = "thirdiallo@gmail.com";
        String mobile = "779440310";
        String address = "USA";
        Client clientDto = new Client();
        clientDto.setReference(reference);
        clientDto.setFirstName(firstName);
        clientDto.setLastName(lastName);
        clientDto.setEmail(email);
        clientDto.setMobile(mobile);
        clientDto.setAddress(address);

        Client clientDtoResult = clientRepository.save(clientDto);

        String firstName1 = "TairouDiallo";
        String lastName1 = "diallodiallo";
        clientDto.setFirstName(firstName1);
        clientDto.setLastName(lastName1);
        clientDto.setId((long) 1);

        clientRepository.save(clientDto);

        assertThat(clientDto.getFirstName()).isEqualTo(firstName1);

    }

    @Test
    public void testFindById() {
        String reference = "CLT1";
        String firstName = "tairou";
        String lastName = "diallo";
        String email = "thirdiallo@gmail.com";
        String mobile = "779440310";
        String address = "USA";
        Client clientDto = new Client();
        clientDto.setReference(reference);
        clientDto.setFirstName(firstName);
        clientDto.setLastName(lastName);
        clientDto.setEmail(email);
        clientDto.setMobile(mobile);
        clientDto.setAddress(address);

        Client clientDtoResult = clientRepository.save(clientDto);

        boolean isExitClient = clientRepository.findById(clientDtoResult.getId()).isPresent();

        assertTrue(isExitClient);

    }

    @Test
    public void testFindAll() {
        String reference = "CLT1";
        String firstName = "tairou";
        String lastName = "diallo";
        String email = "thirdiallo@gmail.com";
        String mobile = "779440310";
        String address = "USA";
        Client clientDto = new Client();
        clientDto.setReference(reference);
        clientDto.setFirstName(firstName);
        clientDto.setLastName(lastName);
        clientDto.setEmail(email);
        clientDto.setMobile(mobile);
        clientDto.setAddress(address);

        Client clientDtoResult = clientRepository.save(clientDto);

        String reference1 = "CLT1";
        String firstName1 = "tairou";
        String lastName1 = "diallo";
        Client clientDto1 = new Client();
        clientDto1.setReference(reference1);
        clientDto1.setFirstName(firstName1);
        clientDto1.setLastName(lastName1);

        Client clientDtoResult1 = clientRepository.save(clientDto);

        List<?> clients = clientRepository.findAll();

        assertThat(clients).size().isGreaterThan(1);

    }

    @Test
    @Rollback(false)
    public void testDelete() {
        String reference = "CLT1";
        String firstName = "tairou";
        String lastName = "diallo";
        String email = "thirdiallo@gmail.com";
        String mobile = "779440310";
        String address = "USA";
        Client clientDto = new Client();
        clientDto.setReference(reference);
        clientDto.setFirstName(firstName);
        clientDto.setLastName(lastName);
        clientDto.setEmail(email);
        clientDto.setMobile(mobile);
        clientDto.setAddress(address);

        Client clientDtoResult = clientRepository.save(clientDto);

        boolean isExistBeforeDelete = clientRepository.findById(clientDtoResult.getId()).isPresent();

        clientRepository.deleteById(clientDtoResult.getId());

        boolean notExistAfterDelete = clientRepository.findById(clientDtoResult.getId()).isPresent();

        assertTrue(isExistBeforeDelete);

        assertFalse(notExistAfterDelete);

    }

}
