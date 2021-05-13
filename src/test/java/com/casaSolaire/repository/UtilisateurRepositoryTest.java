package com.casaSolaire.repository;

import com.casaSolaire.models.Utilisateur;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UtilisateurRepositoryTest {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Test
    @Rollback(false)
    public void testCreateUtilisateur() {
        String username = "thir";
        String mobile = "779440310";
        Utilisateur utilisateurDto = new Utilisateur();
        utilisateurDto.setUsername(username);
        utilisateurDto.setMobile(mobile);

        Utilisateur utilisateurDtoResult = utilisateurRepository.save(utilisateurDto);

        assertNotNull(utilisateurDtoResult);

    }

    @Test
    @Rollback(false)
    public void TestUpdateUtilisateur() {
        String username = "thir";
        String mobile = "779440310";
        Utilisateur utilisateurDto = new Utilisateur();
        utilisateurDto.setUsername(username);
        utilisateurDto.setMobile(mobile);

        utilisateurDto.setId((long) 3);

        Utilisateur utilisateurDtoResult = utilisateurRepository.save(utilisateurDto);

        assertThat(utilisateurDtoResult.getUsername()).isEqualTo(username);

    }

    @Test
    public void testFindById() {

        String username = "thir";
        String mobile = "779440310";
        Utilisateur utilisateurDto = new Utilisateur();
        utilisateurDto.setUsername(username);
        utilisateurDto.setMobile(mobile);

        Utilisateur utilisateurDtoResult = utilisateurRepository.save(utilisateurDto);

        boolean isExistUser = utilisateurRepository.findById(utilisateurDtoResult.getId()).isPresent();

        assertTrue(isExistUser);


    }

    @Test
    public void testFindAll() {
        String username = "thir";
        String mobile = "779440310";
        Utilisateur utilisateurDto = new Utilisateur();
        utilisateurDto.setUsername(username);
        utilisateurDto.setMobile(mobile);

        utilisateurRepository.save(utilisateurDto);

        String usernameUser = "thir";
        String mobileUser = "779440310";
        Utilisateur utilisateurDto1 = new Utilisateur();
        utilisateurDto1.setUsername(usernameUser);
        utilisateurDto1.setMobile(mobileUser);

        utilisateurRepository.save(utilisateurDto1);

        List<Utilisateur> users = utilisateurRepository.findAll();

        assertThat(users).size().isGreaterThan(0);

    }

    @Test
    @Rollback(false)
    public void testDelete() {
        String username = "thir";
        String mobile = "779440310";
        Utilisateur utilisateurDto = new Utilisateur();
        utilisateurDto.setUsername(username);
        utilisateurDto.setMobile(mobile);

        Utilisateur utilisateurDtoResult = utilisateurRepository.save(utilisateurDto);

        boolean isExistBeforeDelete = utilisateurRepository.findById(utilisateurDtoResult.getId()).isPresent();

        utilisateurRepository.deleteById(utilisateurDtoResult.getId());

        boolean notExistAfterDelete = utilisateurRepository.findById(utilisateurDtoResult.getId()).isPresent();

        assertTrue(isExistBeforeDelete);

        assertFalse(notExistAfterDelete);

    }
}
