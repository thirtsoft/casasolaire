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

        Long number = 123456L;
        double total = 12000;
        String status = "PAYEE";
        Commande commandeDto = new Commande();
        commandeDto.setNumeroCommande(number);
        commandeDto.setTotalCommande(total);
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

        Long number = 12L;
        double total = 12000;
        String status = "PAYEE";
        Commande commandeDto = new Commande();
        commandeDto.setNumeroCommande(number);
        commandeDto.setTotalCommande(total);
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

        Long number = 12L;
        double total = 12000;
        String status = "PAYEE";
        Commande commandeDto = new Commande();
        commandeDto.setNumeroCommande(number);
        commandeDto.setTotalCommande(total);
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

        Long number = 12L;
        double total = 12000;
        String status = "PAYEE";
        Commande commandeDto = new Commande();
        commandeDto.setNumeroCommande(number);
        commandeDto.setTotalCommande(total);
        commandeDto.setStatus(status);
        commandeDto.setClient(client);
        commandeRepository.save(commandeDto);

        Long number1 = 5L;
        double total1 = 12000;
        String status1 = "PAYEE";
        Commande commandeDto1 = new Commande();
        commandeDto1.setNumeroCommande(number1);
        commandeDto1.setTotalCommande(total1);
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

        Long number = 12L;
        double total = 12000;
        String status = "PAYEE";
        Commande commandeDto = new Commande();
        commandeDto.setNumeroCommande(number);
        commandeDto.setTotalCommande(total);
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
