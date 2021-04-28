package com.casaSolaire.repository;

import com.casaSolaire.dto.ArticleDto;
import com.casaSolaire.dto.NoteDto;
import com.casaSolaire.dto.UtilisateurDto;
import com.casaSolaire.models.Article;
import com.casaSolaire.models.Note;
import com.casaSolaire.models.Utilisateur;
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
public class NoteRepositoryTest {

    @Autowired
    private NoteRepository noteRepository;

    @Test
    @Rollback(false)
    public void testCreateNote() {
        String referenceArticle = "Art1";
        String designation = "Article1";
        Article articleDto = new Article();
        articleDto.setReference(referenceArticle);
        articleDto.setDesignation(designation);

        String username = "thir";
        String mobile = "779440310";
        Utilisateur utilisateurDto = new Utilisateur();
        utilisateurDto.setUsername(username);
        utilisateurDto.setMobile(mobile);

        String reference = "Note1";
        String nombreEtoile = "4etoiles";
        String observation = "bonbon";
        Note noteDto = new Note();
        noteDto.setReference(reference);
        noteDto.setNombreEtoile(nombreEtoile);
        noteDto.setObservation(observation);
        noteDto.setArticle(articleDto);
        noteDto.setUtilisateur(utilisateurDto);

        Note noteDtoResult = noteRepository.save(noteDto);

        assertNotNull(noteDtoResult);

    }

    @Test
    @Rollback(false)
    public void TestUpdateNote() {
        String referenceArticle = "Art1";
        String designation = "Article1";
        Article articleDto = new Article();
        articleDto.setReference(referenceArticle);
        articleDto.setDesignation(designation);

        String username = "thir";
        String mobile = "779440310";
        Utilisateur utilisateurDto = new Utilisateur();
        utilisateurDto.setUsername(username);
        utilisateurDto.setMobile(mobile);

        String reference = "Note1";
        String nombreEtoile = "4etoiles";
        String observation = "bonbon";
        Note noteDto = new Note();
        noteDto.setReference(reference);
        noteDto.setNombreEtoile(nombreEtoile);
        noteDto.setObservation(observation);
        noteDto.setArticle(articleDto);
        noteDto.setUtilisateur(utilisateurDto);
        noteRepository.save(noteDto);

        String referenceNote = "Note1111";
        noteDto.setReference(referenceNote);
        noteDto.setId((long) 1);
        noteRepository.save(noteDto);

        assertThat(noteDto.getReference()).isEqualTo(referenceNote);

    }

    @Test
    public void testFindById() {
        String referenceArticle = "Art1";
        String designation = "Article1";
        Article articleDto = new Article();
        articleDto.setReference(referenceArticle);
        articleDto.setDesignation(designation);

        String username = "thir";
        String mobile = "779440310";
        Utilisateur utilisateurDto = new Utilisateur();
        utilisateurDto.setUsername(username);
        utilisateurDto.setMobile(mobile);

        String reference = "Note1";
        String nombreEtoile = "4etoiles";
        String observation = "bonbon";
        Note noteDto = new Note();
        noteDto.setReference(reference);
        noteDto.setNombreEtoile(nombreEtoile);
        noteDto.setObservation(observation);
        noteDto.setArticle(articleDto);
        noteDto.setUtilisateur(utilisateurDto);

        Note noteDtoResult = noteRepository.save(noteDto);

        boolean isExistNote = noteRepository.findById(noteDtoResult.getId()).isPresent();

        assertTrue(isExistNote);

    }

    @Test
    public void testFindAll() {
        String referenceArticle = "Art1";
        String designation = "Article1";
        Article articleDto = new Article();
        articleDto.setReference(referenceArticle);
        articleDto.setDesignation(designation);

        String username = "thir";
        String mobile = "779440310";
        Utilisateur utilisateurDto = new Utilisateur();
        utilisateurDto.setUsername(username);
        utilisateurDto.setMobile(mobile);

        String reference = "Note1";
        String nombreEtoile = "4etoiles";
        String observation = "bonbon";
        Note noteDto = new Note();
        noteDto.setReference(reference);
        noteDto.setNombreEtoile(nombreEtoile);
        noteDto.setObservation(observation);
        noteDto.setArticle(articleDto);
        noteDto.setUtilisateur(utilisateurDto);

        noteRepository.save(noteDto);

        String referenceNote = "Note1";
        String nombreEtoileNote = "4etoiles";
        String observationNote = "bonbon";
        Note noteDto1 = new Note();
        noteDto1.setReference(referenceNote);
        noteDto1.setNombreEtoile(nombreEtoileNote);
        noteDto1.setObservation(observationNote);
        noteDto1.setArticle(articleDto);
        noteDto1.setUtilisateur(utilisateurDto);

        noteRepository.save(noteDto1);

        List<?> notes = noteRepository.findAll();

        assertThat(notes).size().isGreaterThan(0);

    }

    @Test
    @Rollback(false)
    public void testDelete() {
        String referenceArticle = "Art1";
        String designation = "Article1";
        Article articleDto = new Article();
        articleDto.setReference(referenceArticle);
        articleDto.setDesignation(designation);

        String username = "thir";
        String mobile = "779440310";
        Utilisateur utilisateurDto = new Utilisateur();
        utilisateurDto.setUsername(username);
        utilisateurDto.setMobile(mobile);

        String reference = "Note1";
        String nombreEtoile = "4etoiles";
        String observation = "bonbon";
        Note noteDto = new Note();
        noteDto.setReference(reference);
        noteDto.setNombreEtoile(nombreEtoile);
        noteDto.setObservation(observation);
        noteDto.setArticle(articleDto);
        noteDto.setUtilisateur(utilisateurDto);

        Note noteDtoResult = noteRepository.save(noteDto);

        boolean isExistBeforeDelete = noteRepository.findById(noteDtoResult.getId()).isPresent();

        noteRepository.deleteById(noteDtoResult.getId());

        boolean notExistAfterDelete = noteRepository.findById(noteDtoResult.getId()).isPresent();

        assertTrue(isExistBeforeDelete);

        assertFalse(notExistAfterDelete);

    }

}
