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
import static org.junit.Assert.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class FournisseurRepositoryTest {

    @Autowired
    private FournisseurRepository fournisseurRepository;

    @Autowired
    private ArticleRepository articleRepository;

    @Test
    @Rollback(false)
    public void testCreateFournisseur() {
        Long idArticle = (long) 2;
        Article article = articleRepository.findById(idArticle).orElse(null);

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
        fournisseurDto.setArticle(article);

        Fournisseur fournisseurDtoResult = fournisseurRepository.save(fournisseurDto);

        assertNotNull(fournisseurDtoResult);

    }

    @Test
    @Rollback(false)
    public void TestUpdateFournisseur() {
        Long idArticle = (long) 1;
        Article article = articleRepository.findById(idArticle).orElse(null);

        String referenceFour = "CLT1";
        String firstName = "tairou";
        String lastName = "diallo";
        Fournisseur fournisseurDto = new Fournisseur();
        fournisseurDto.setReference(referenceFour);
        fournisseurDto.setFirstName(firstName);
        fournisseurDto.setLastName(lastName);
        fournisseurDto.setArticle(article);

        fournisseurDto.setId((long) 3);

        Fournisseur fournisseurDtoResult = fournisseurRepository.save(fournisseurDto);

        assertThat(fournisseurDtoResult.getFirstName()).isEqualTo(firstName);

    }

    @Test
    public void testFindById() {
        Long idArticle = (long) 1;
        Article article = articleRepository.findById(idArticle).orElse(null);

        String referenceFour = "CLT1";
        String firstName = "tairou";
        String lastName = "diallo";
        Fournisseur fournisseurDto = new Fournisseur();
        fournisseurDto.setReference(referenceFour);
        fournisseurDto.setFirstName(firstName);
        fournisseurDto.setLastName(lastName);
        fournisseurDto.setArticle(article);

        Fournisseur fournisseurDtoResult = fournisseurRepository.save(fournisseurDto);

        boolean isExitFournisseur = fournisseurRepository.findById(fournisseurDtoResult.getId()).isPresent();

        assertTrue(isExitFournisseur);

    }

    @Test
    public void testFindAll() {
        Long idArticle = (long) 1;
        Article article = articleRepository.findById(idArticle).orElse(null);

        String referenceFour = "CLT1";
        String firstName = "tairou";
        String lastName = "diallo";
        Fournisseur fournisseurDto = new Fournisseur();
        fournisseurDto.setReference(referenceFour);
        fournisseurDto.setFirstName(firstName);
        fournisseurDto.setLastName(lastName);
        fournisseurDto.setArticle(article);

        fournisseurRepository.save(fournisseurDto);

        List<Fournisseur> fournisseurList = fournisseurRepository.findAll();

        assertThat(fournisseurList).size().isGreaterThan(0);

    }

    @Test
    @Rollback(false)
    public void testDelete() {
        Long idArticle = (long) 1;
        Article article = articleRepository.findById(idArticle).orElse(null);

        String referenceFour = "CLT1";
        String firstName = "tairou";
        String lastName = "diallo";
        Fournisseur fournisseurDto = new Fournisseur();
        fournisseurDto.setReference(referenceFour);
        fournisseurDto.setFirstName(firstName);
        fournisseurDto.setLastName(lastName);
        fournisseurDto.setArticle(article);

        Fournisseur fournisseurDtoResult = fournisseurRepository.save(fournisseurDto);

        boolean isExistBeforeDelete = fournisseurRepository.findById(fournisseurDtoResult.getId()).isPresent();

        fournisseurRepository.deleteById(fournisseurDtoResult.getId());

        boolean notExistAfterDelete = fournisseurRepository.findById(fournisseurDtoResult.getId()).isPresent();

        assertTrue(isExistBeforeDelete);

        assertFalse(notExistAfterDelete);


    }

}
