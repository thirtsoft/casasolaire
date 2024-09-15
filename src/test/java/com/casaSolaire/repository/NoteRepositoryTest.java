package com.casaSolaire.repository;

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

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Test
    @Rollback(false)
    public void testCreateNote() {
        Long idArticle = (long) 1;
        Article article = articleRepository.findById(idArticle).orElse(null);

        Long idUser = (long) 1;
        Utilisateur user = utilisateurRepository.findById(idUser).orElse(null);

        String reference = "Note1";
        String nombreEtoile = "4etoiles";
        String observation = "bonbon";
        Note noteDto = new Note();
        noteDto.setReference(reference);
        noteDto.setNombreEtoile(nombreEtoile);
        noteDto.setObservation(observation);
        noteDto.setArticle(article);
        noteDto.setUtilisateur(user);

        Note noteDtoResult = noteRepository.save(noteDto);

        assertNotNull(noteDtoResult);

    }

    @Test
    @Rollback(false)
    public void TestUpdateNote() {
        Long idArticle = (long) 1;
        Article article = articleRepository.findById(idArticle).orElse(null);

        Long idUser = (long) 1;
        Utilisateur user = utilisateurRepository.findById(idUser).orElse(null);

        String reference = "Note1";
        String nombreEtoile = "4etoiles";
        String observation = "bonbon";
        Note noteDto = new Note();
        noteDto.setReference(reference);
        noteDto.setNombreEtoile(nombreEtoile);
        noteDto.setObservation(observation);
        noteDto.setArticle(article);
        noteDto.setUtilisateur(user);

        noteDto.setId((long) 3);

        Note noteDtoResult = noteRepository.save(noteDto);

        assertThat(noteDtoResult.getReference()).isEqualTo(reference);

    }

    @Test
    public void testFindById() {
        Long idArticle = (long) 1;
        Article article = articleRepository.findById(idArticle).orElse(null);

        Long idUser = (long) 1;
        Utilisateur user = utilisateurRepository.findById(idUser).orElse(null);

        String reference = "Note1";
        String nombreEtoile = "4etoiles";
        String observation = "bonbon";
        Note noteDto = new Note();
        noteDto.setReference(reference);
        noteDto.setNombreEtoile(nombreEtoile);
        noteDto.setObservation(observation);
        noteDto.setArticle(article);
        noteDto.setUtilisateur(user);

        Note noteDtoResult = noteRepository.save(noteDto);

        boolean isExistNote = noteRepository.findById(noteDtoResult.getId()).isPresent();

        assertTrue(isExistNote);

    }

    @Test
    public void testFindAll() {
        Long idArticle = (long) 1;
        Article article = articleRepository.findById(idArticle).orElse(null);

        Long idUser = (long) 1;
        Utilisateur user = utilisateurRepository.findById(idUser).orElse(null);

        String reference = "Note1";
        String nombreEtoile = "4etoiles";
        String observation = "bonbon";
        Note noteDto = new Note();
        noteDto.setReference(reference);
        noteDto.setNombreEtoile(nombreEtoile);
        noteDto.setObservation(observation);
        noteDto.setArticle(article);
        noteDto.setUtilisateur(user);

        noteRepository.save(noteDto);

        String referenceNote = "Note1";
        String nombreEtoileNote = "4etoiles";
        String observationNote = "bonbon";
        Note noteDto2 = new Note();
        noteDto2.setReference(referenceNote);
        noteDto2.setNombreEtoile(nombreEtoileNote);
        noteDto2.setObservation(observationNote);
        noteDto2.setArticle(article);
        noteDto2.setUtilisateur(user);

        noteRepository.save(noteDto2);

        List<Note> notes = noteRepository.findAll();

        assertThat(notes).size().isGreaterThan(1);

    }

    @Test
    @Rollback(false)
    public void testDelete() {
        Long idArticle = (long) 1;
        Article article = articleRepository.findById(idArticle).orElse(null);

        Long idUser = (long) 1;
        Utilisateur user = utilisateurRepository.findById(idUser).orElse(null);

        String reference = "Note1";
        String nombreEtoile = "4etoiles";
        String observation = "bonbon";
        Note noteDto = new Note();
        noteDto.setReference(reference);
        noteDto.setNombreEtoile(nombreEtoile);
        noteDto.setObservation(observation);
        noteDto.setArticle(article);
        noteDto.setUtilisateur(user);

        Note noteDtoResult = noteRepository.save(noteDto);

        boolean isExistBeforeDelete = noteRepository.findById(noteDtoResult.getId()).isPresent();

        noteRepository.deleteById(noteDtoResult.getId());

        boolean notExistAfterDelete = noteRepository.findById(noteDtoResult.getId()).isPresent();

        assertTrue(isExistBeforeDelete);

        assertFalse(notExistAfterDelete);

    }

}
