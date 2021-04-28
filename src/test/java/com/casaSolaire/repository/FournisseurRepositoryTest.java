package com.casaSolaire.repository;

import com.casaSolaire.models.Article;
import com.casaSolaire.models.Fournisseur;
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
public class FournisseurRepositoryTest {

    @Autowired
    private FournisseurRepository fournisseurRepository;

    @Test
    @Rollback(false)
    public void testCreateFournisseur() {
        String reference = "Art1";
        String designation = "Article1";
        Article articleDto = new Article();
        articleDto.setReference(reference);
        articleDto.setDesignation(designation);

        String referenceFour = "CLT1";
        String firstName = "tairou";
        String lastName = "diallo";
        String email = "thirdiallo@gmail.com";
        String telephone = "779440310";
        String city = "USA";
        String town = "City";
        String rue = "rue";

        Fournisseur fournisseurDto = new Fournisseur();
        fournisseurDto.setReference(referenceFour);
        fournisseurDto.setFirstName(firstName);
        fournisseurDto.setLastName(lastName);
        fournisseurDto.setEmail(email);
        fournisseurDto.setTelephone(telephone);
        fournisseurDto.setCity(city);
        fournisseurDto.setTown(town);
        fournisseurDto.setRue(rue);
        fournisseurDto.setArticle(articleDto);

        Fournisseur fournisseurDtoResult = fournisseurRepository.save(fournisseurDto);

        assertNotNull(fournisseurDtoResult);

    }

    @Test
    @Rollback(false)
    public void TestUpdateFournisseur() {
        String reference = "Art1";
        String designation = "Article1";
        Article articleDto = new Article();
        articleDto.setReference(reference);
        articleDto.setDesignation(designation);

        String referenceFour = "CLT1";
        String firstName = "tairou";
        String lastName = "diallo";
        String email = "thirdiallo@gmail.com";

        Fournisseur fournisseurDto = new Fournisseur();
        fournisseurDto.setReference(referenceFour);
        fournisseurDto.setFirstName(firstName);
        fournisseurDto.setLastName(lastName);
        fournisseurDto.setEmail(email);
        fournisseurDto.setArticle(articleDto);

        Fournisseur fournisseurDtoResult = fournisseurRepository.save(fournisseurDto);

        String firstName1 = "TairouDiallo";
        String lastName1 = "diallodiallo";
        fournisseurDto.setFirstName(firstName1);
        fournisseurDto.setLastName(lastName1);
        fournisseurDto.setId((long) 1);

        fournisseurRepository.save(fournisseurDto);

        assertThat(fournisseurDto.getFirstName()).isEqualTo(firstName1);

    }

    @Test
    public void testFindById() {
        String reference = "Art1";
        String designation = "Article1";
        Article articleDto = new Article();
        articleDto.setReference(reference);
        articleDto.setDesignation(designation);

        String referenceFour = "CLT1";
        String firstName = "tairou";
        String lastName = "diallo";
        String email = "thirdiallo@gmail.com";

        Fournisseur fournisseurDto = new Fournisseur();
        fournisseurDto.setReference(referenceFour);
        fournisseurDto.setFirstName(firstName);
        fournisseurDto.setLastName(lastName);
        fournisseurDto.setEmail(email);
        fournisseurDto.setArticle(articleDto);

        Fournisseur fournisseurDtoResult = fournisseurRepository.save(fournisseurDto);


        boolean isExitFournisseur = fournisseurRepository.findById(fournisseurDtoResult.getId()).isPresent();

        assertTrue(isExitFournisseur);

    }

    @Test
    public void testFindAll() {
        String reference = "Art1";
        String designation = "Article1";
        Article articleDto = new Article();
        articleDto.setReference(reference);
        articleDto.setDesignation(designation);

        String referenceFour = "CLT1";
        String firstName = "tairou";
        String lastName = "diallo";
        String email = "thirdiallo@gmail.com";

        Fournisseur fournisseurDto = new Fournisseur();
        fournisseurDto.setReference(referenceFour);
        fournisseurDto.setFirstName(firstName);
        fournisseurDto.setLastName(lastName);
        fournisseurDto.setEmail(email);
        fournisseurDto.setArticle(articleDto);

        Fournisseur fournisseurDtoResult = fournisseurRepository.save(fournisseurDto);

        String reference1 = "CLT1";
        String firstName1 = "tairou";
        String lastName1 = "diallo";
        Fournisseur fournisseurDto1 = new Fournisseur();
        fournisseurDto1.setReference(reference);
        fournisseurDto1.setFirstName(firstName);
        fournisseurDto1.setLastName(lastName);

        fournisseurRepository.save(fournisseurDto1);

        List<?> fournisseurs = fournisseurRepository.findAll();

        assertThat(fournisseurs).size().isGreaterThan(0);

    }

    @Test
    @Rollback(false)
    public void testDelete() {
        String reference = "Art1";
        String designation = "Article1";
        Article articleDto = new Article();
        articleDto.setReference(reference);
        articleDto.setDesignation(designation);

        String referenceFour = "CLT1";
        String firstName = "tairou";
        String lastName = "diallo";
        String email = "thirdiallo@gmail.com";

        Fournisseur fournisseurDto = new Fournisseur();
        fournisseurDto.setReference(referenceFour);
        fournisseurDto.setFirstName(firstName);
        fournisseurDto.setLastName(lastName);
        fournisseurDto.setEmail(email);
        fournisseurDto.setArticle(articleDto);

        Fournisseur fournisseurDtoResult = fournisseurRepository.save(fournisseurDto);

        boolean isExistBeforeDelete = fournisseurRepository.findById(fournisseurDtoResult.getId()).isPresent();

        fournisseurRepository.deleteById(fournisseurDtoResult.getId());

        boolean notExistAfterDelete = fournisseurRepository.findById(fournisseurDtoResult.getId()).isPresent();

        assertTrue(isExistBeforeDelete);

        assertFalse(notExistAfterDelete);

    }

}
