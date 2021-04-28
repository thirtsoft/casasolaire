package com.casaSolaire.repository;

import com.casaSolaire.enums.Status;
import com.casaSolaire.models.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CommandeRepositoryTest {

    @Autowired
    private CommandeRepository commandeRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Test
    @Rollback(false)
    public void testCreateCommande() {
        Long idClient = (long) 1;
        Client client = clientRepository.findById(idClient).orElse(null);

        String number = "Com1";
        double total = 12000;
        Status status = Status.PAYEE;
        Commande commandeDto = new Commande();
        commandeDto.setNumber(number);
        commandeDto.setTotal(total);
        commandeDto.setStatus(status);
        commandeDto.setClient(client);

        Commande commandeDtoResult = commandeRepository.save(commandeDto);

        assertNotNull(commandeDtoResult);

    }

    @Test
    @Rollback(false)
    public void TestUpdateCommande() {
        Long idClient = (long) 1;
        Client client = clientRepository.findById(idClient).orElse(null);

        String number = "Com1";
        double total = 12000;
        Status status = Status.PAYEE;
        Commande commandeDto = new Commande();
        commandeDto.setNumber(number);
        commandeDto.setTotal(total);
        commandeDto.setStatus(status);
        commandeDto.setClient(client);

       // Commande commandeDtoResult = commandeRepository.save(commandeDto);

        commandeDto.setId((long) 3);

        Commande utilisateurDtoResult = commandeRepository.save(commandeDto);

        assertThat(utilisateurDtoResult.getStatus()).isEqualTo(status);

    }

    @Test
    public void testFindById() {
        Long idClient = (long) 1;
        Client client = clientRepository.findById(idClient).orElse(null);

        String number = "Com1";
        double total = 12000;
        Status status = Status.PAYEE;
        Commande commandeDto = new Commande();
        commandeDto.setNumber(number);
        commandeDto.setTotal(total);
        commandeDto.setStatus(status);
        commandeDto.setClient(client);

        Commande commandeDtoResult = commandeRepository.save(commandeDto);

        boolean isExistCommande = commandeRepository.findById(commandeDtoResult.getId()).isPresent();

        assertTrue(isExistCommande);

    }

    @Test
    public void testFindAll() {
        Long idClient = (long) 1;
        Client client = clientRepository.findById(idClient).orElse(null);

        String number = "Com1";
        double total = 12000;
        Status status = Status.PAYEE;
        Commande commandeDto = new Commande();
        commandeDto.setNumber(number);
        commandeDto.setTotal(total);
        commandeDto.setStatus(status);
        commandeDto.setClient(client);
        commandeRepository.save(commandeDto);

        String number1 = "Com1";
        double total1 = 12000;
        Status status1 = Status.PAYEE;
        Commande commandeDto1 = new Commande();
        commandeDto1.setNumber(number1);
        commandeDto1.setTotal(total1);
        commandeDto1.setStatus(status1);
        commandeDto1.setClient(client);
        commandeRepository.save(commandeDto1);

        List<Commande> commandeList = commandeRepository.findAll();

        assertThat(commandeList).size().isGreaterThan(0);

    }

    @Test
    @Rollback(false)
    public void testDelete() {
        Long idClient = (long) 1;
        Client client = clientRepository.findById(idClient).orElse(null);

        String number = "Com1";
        double total = 12000;
        Status status = Status.PAYEE;
        Commande commandeDto = new Commande();
        commandeDto.setNumber(number);
        commandeDto.setTotal(total);
        commandeDto.setStatus(status);
        commandeDto.setClient(client);

        Commande commandeDtoResult = commandeRepository.save(commandeDto);

        boolean isExistBeforeDelete = commandeRepository.findById(commandeDtoResult.getId()).isPresent();

        commandeRepository.deleteById(commandeDtoResult.getId());

        boolean notExistAfterDelete = commandeRepository.findById(commandeDtoResult.getId()).isPresent();

        assertTrue(isExistBeforeDelete);

        assertFalse(notExistAfterDelete);


    }

}
