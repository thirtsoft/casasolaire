package com.casaSolaire.repository;

import com.casaSolaire.dto.ClientDto;
import com.casaSolaire.dto.CommandeDto;
import com.casaSolaire.enums.Status;
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
public class CommandeRepositoryTest {

    @Autowired
    private CommandeRepository commandeRepository;

    @Test
    @Rollback(false)
    public void testCreateCommande() {
        String reference = "CLT1";
        String firstName = "tairou";
        String lastName = "diallo";
        String email = "thirdiallo@gmail.com";
        String mobile = "779440310";
        String address = "USA";
        ClientDto clientDto = new ClientDto();
        clientDto.setReference(reference);
        clientDto.setFirstName(firstName);
        clientDto.setLastName(lastName);
        clientDto.setEmail(email);
        clientDto.setMobile(mobile);
        clientDto.setAddress(address);

        String number = "Com1";
        double total = 12000;
        Status status = Status.PAYEE;
        CommandeDto commandeDto = new CommandeDto();
        commandeDto.setNumber(number);
        commandeDto.setTotal(total);
        commandeDto.setStatus(status);
        commandeDto.setClientDto(clientDto);

        CommandeDto commandeDtoResult = CommandeDto.fromEntityToDto(
                commandeRepository.save(
                        CommandeDto.fromDtoToEntity(commandeDto)
                )
        );

        assertNotNull(commandeDtoResult);

    }

    @Test
    @Rollback(false)
    public void TestUpdateCommande() {
        String reference = "CLT1";
        String firstName = "tairou";
        String lastName = "diallo";
        String email = "thirdiallo@gmail.com";
        String mobile = "779440310";
        String address = "USA";
        ClientDto clientDto = new ClientDto();
        clientDto.setReference(reference);
        clientDto.setFirstName(firstName);
        clientDto.setLastName(lastName);
        clientDto.setEmail(email);
        clientDto.setMobile(mobile);
        clientDto.setAddress(address);

        String number = "Com1";
        double total = 12000;
        Status status = Status.PAYEE;
        CommandeDto commandeDto = new CommandeDto();
        commandeDto.setNumber(number);
        commandeDto.setTotal(total);
        commandeDto.setStatus(status);
        commandeDto.setClientDto(clientDto);

        CommandeDto commandeDtoResult = CommandeDto.fromEntityToDto(
                commandeRepository.save(
                        CommandeDto.fromDtoToEntity(commandeDto)
                )
        );

        Status status1 = Status.ENCOURS;
        commandeDto.setStatus(status1);
        commandeDto.setId((long) 1);
        CommandeDto.fromEntityToDto(commandeRepository.save(CommandeDto.fromDtoToEntity(commandeDto)));

        assertThat(commandeDto.getStatus()).isEqualTo(status1);

    }

    @Test
    public void testFindById() {
        String reference = "CLT1";
        String firstName = "tairou";
        String lastName = "diallo";
        String email = "thirdiallo@gmail.com";
        String mobile = "779440310";
        String address = "USA";
        ClientDto clientDto = new ClientDto();
        clientDto.setReference(reference);
        clientDto.setFirstName(firstName);
        clientDto.setLastName(lastName);
        clientDto.setEmail(email);
        clientDto.setMobile(mobile);
        clientDto.setAddress(address);

        String number = "Com1";
        double total = 12000;
        Status status = Status.PAYEE;
        CommandeDto commandeDto = new CommandeDto();
        commandeDto.setNumber(number);
        commandeDto.setTotal(total);
        commandeDto.setStatus(status);
        commandeDto.setClientDto(clientDto);

        CommandeDto commandeDtoResult = CommandeDto.fromEntityToDto(
                commandeRepository.save(
                        CommandeDto.fromDtoToEntity(commandeDto)
                )
        );

        boolean isExistCommande = commandeRepository.findById(commandeDtoResult.getId()).isPresent();

        assertTrue(isExistCommande);

    }

    @Test
    public void testFindAll() {
        String reference = "CLT1";
        String firstName = "tairou";
        String lastName = "diallo";
        String email = "thirdiallo@gmail.com";
        String mobile = "779440310";
        String address = "USA";
        ClientDto clientDto = new ClientDto();
        clientDto.setReference(reference);
        clientDto.setFirstName(firstName);
        clientDto.setLastName(lastName);
        clientDto.setEmail(email);
        clientDto.setMobile(mobile);
        clientDto.setAddress(address);

        String number = "Com1";
        double total = 12000;
        Status status = Status.PAYEE;
        CommandeDto commandeDto = new CommandeDto();
        commandeDto.setNumber(number);
        commandeDto.setTotal(total);
        commandeDto.setStatus(status);
        commandeDto.setClientDto(clientDto);

        CommandeDto commandeDtoResult = CommandeDto.fromEntityToDto(
                commandeRepository.save(
                        CommandeDto.fromDtoToEntity(commandeDto)
                )
        );

        String number1 = "Com2";
        double total2 = 1222000;
        Status status2 = Status.VALIDEE;
        CommandeDto commandeDto1 = new CommandeDto();
        commandeDto1.setNumber(number1);
        commandeDto1.setTotal(total2);
        commandeDto1.setStatus(status2);
        commandeDto1.setClientDto(clientDto);

        CommandeDto commandeDtoResult1 = CommandeDto.fromEntityToDto(
                commandeRepository.save(
                        CommandeDto.fromDtoToEntity(commandeDto1)
                )
        );

        List<?> commandes = commandeRepository.findAll();

        assertThat(commandes).size().isGreaterThan(0);

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
        ClientDto clientDto = new ClientDto();
        clientDto.setReference(reference);
        clientDto.setFirstName(firstName);
        clientDto.setLastName(lastName);
        clientDto.setEmail(email);
        clientDto.setMobile(mobile);
        clientDto.setAddress(address);

        String number = "Com1";
        double total = 12000;
        Status status = Status.PAYEE;
        CommandeDto commandeDto = new CommandeDto();
        commandeDto.setNumber(number);
        commandeDto.setTotal(total);
        commandeDto.setStatus(status);
        commandeDto.setClientDto(clientDto);

        CommandeDto commandeDtoResult = CommandeDto.fromEntityToDto(
                commandeRepository.save(
                        CommandeDto.fromDtoToEntity(commandeDto)
                )
        );

        boolean isExistBeforeDelete = commandeRepository.findById(commandeDtoResult.getId()).isPresent();

        commandeRepository.deleteById(commandeDtoResult.getId());

        boolean notExistAfterDelete = commandeRepository.findById(commandeDtoResult.getId()).isPresent();

        assertTrue(isExistBeforeDelete);

        assertFalse(notExistAfterDelete);

    }

}
