package com.casaSolaire.repository;

import com.casaSolaire.dto.ArticleDto;
import com.casaSolaire.dto.NoteDto;
import com.casaSolaire.dto.UtilisateurDto;
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
        ArticleDto articleDto = new ArticleDto();
        articleDto.setReference(referenceArticle);
        articleDto.setDesignation(designation);

        String username = "thir";
        String mobile = "779440310";
        UtilisateurDto utilisateurDto = new UtilisateurDto();
        utilisateurDto.setUsername(username);
        utilisateurDto.setMobile(mobile);

        String reference = "Note1";
        String nombreEtoile = "4etoiles";
        String observation = "bonbon";
        NoteDto noteDto = new NoteDto();
        noteDto.setReference(reference);
        noteDto.setNombreEtoile(nombreEtoile);
        noteDto.setObservation(observation);
        noteDto.setArticleDto(articleDto);
        noteDto.setUtilisateurDto(utilisateurDto);

        NoteDto noteDtoResult = NoteDto.fromEntityToDto(
                noteRepository.save(
                        NoteDto.fromDtoToEntity(noteDto)
                )
        );

        assertNotNull(noteDtoResult);

    }

    @Test
    @Rollback(false)
    public void TestUpdateNote() {
        String referenceArticle = "Art1";
        String designation = "Article1";
        ArticleDto articleDto = new ArticleDto();
        articleDto.setReference(referenceArticle);
        articleDto.setDesignation(designation);

        String username = "thir";
        String mobile = "779440310";
        UtilisateurDto utilisateurDto = new UtilisateurDto();
        utilisateurDto.setUsername(username);
        utilisateurDto.setMobile(mobile);

        String reference = "Note1";
        String nombreEtoile = "4etoiles";
        String observation = "bonbon";
        NoteDto noteDto = new NoteDto();
        noteDto.setReference(reference);
        noteDto.setNombreEtoile(nombreEtoile);
        noteDto.setObservation(observation);
        noteDto.setArticleDto(articleDto);
        noteDto.setUtilisateurDto(utilisateurDto);

        NoteDto noteDtoResult = NoteDto.fromEntityToDto(
                noteRepository.save(
                        NoteDto.fromDtoToEntity(noteDto)
                )
        );

        String referenceNote = "Note1111";
        noteDto.setReference(referenceNote);
        noteDto.setId((long) 1);
        NoteDto.fromEntityToDto(noteRepository.save(NoteDto.fromDtoToEntity(noteDto)));

        assertThat(noteDto.getReference()).isEqualTo(referenceNote);

    }

    @Test
    public void testFindById() {
        String referenceArticle = "Art1";
        String designation = "Article1";
        ArticleDto articleDto = new ArticleDto();
        articleDto.setReference(referenceArticle);
        articleDto.setDesignation(designation);

        String username = "thir";
        String mobile = "779440310";
        UtilisateurDto utilisateurDto = new UtilisateurDto();
        utilisateurDto.setUsername(username);
        utilisateurDto.setMobile(mobile);

        String reference = "Note1";
        String nombreEtoile = "4etoiles";
        String observation = "bonbon";
        NoteDto noteDto = new NoteDto();
        noteDto.setReference(reference);
        noteDto.setNombreEtoile(nombreEtoile);
        noteDto.setObservation(observation);
        noteDto.setArticleDto(articleDto);
        noteDto.setUtilisateurDto(utilisateurDto);

        NoteDto noteDtoResult = NoteDto.fromEntityToDto(
                noteRepository.save(
                        NoteDto.fromDtoToEntity(noteDto)
                )
        );

        boolean isExistNote = noteRepository.findById(noteDtoResult.getId()).isPresent();

        assertTrue(isExistNote);

    }

    @Test
    public void testFindAll() {
        String referenceArticle = "Art1";
        String designation = "Article1";
        ArticleDto articleDto = new ArticleDto();
        articleDto.setReference(referenceArticle);
        articleDto.setDesignation(designation);

        String username = "thir";
        String mobile = "779440310";
        UtilisateurDto utilisateurDto = new UtilisateurDto();
        utilisateurDto.setUsername(username);
        utilisateurDto.setMobile(mobile);

        String reference = "Note1";
        String nombreEtoile = "4etoiles";
        String observation = "bonbon";
        NoteDto noteDto = new NoteDto();
        noteDto.setReference(reference);
        noteDto.setNombreEtoile(nombreEtoile);
        noteDto.setObservation(observation);
        noteDto.setArticleDto(articleDto);
        noteDto.setUtilisateurDto(utilisateurDto);

        NoteDto noteDtoResult = NoteDto.fromEntityToDto(
                noteRepository.save(
                        NoteDto.fromDtoToEntity(noteDto)
                )
        );

        String referenceNote = "Note1";
        String nombreEtoileNote = "4etoiles";
        String observationNote = "bonbon";
        NoteDto noteDto1 = new NoteDto();
        noteDto1.setReference(referenceNote);
        noteDto1.setNombreEtoile(nombreEtoileNote);
        noteDto1.setObservation(observationNote);
        noteDto1.setArticleDto(articleDto);
        noteDto1.setUtilisateurDto(utilisateurDto);

        NoteDto noteDtoResult1 = NoteDto.fromEntityToDto(
                noteRepository.save(
                        NoteDto.fromDtoToEntity(noteDto1)
                )
        );

        List<?> notes = noteRepository.findAll();

        assertThat(notes).size().isGreaterThan(0);

    }

    @Test
    @Rollback(false)
    public void testDelete() {
        String referenceArticle = "Art1";
        String designation = "Article1";
        ArticleDto articleDto = new ArticleDto();
        articleDto.setReference(referenceArticle);
        articleDto.setDesignation(designation);

        String username = "thir";
        String mobile = "779440310";
        UtilisateurDto utilisateurDto = new UtilisateurDto();
        utilisateurDto.setUsername(username);
        utilisateurDto.setMobile(mobile);

        String reference = "Note1";
        String nombreEtoile = "4etoiles";
        String observation = "bonbon";
        NoteDto noteDto = new NoteDto();
        noteDto.setReference(reference);
        noteDto.setNombreEtoile(nombreEtoile);
        noteDto.setObservation(observation);
        noteDto.setArticleDto(articleDto);
        noteDto.setUtilisateurDto(utilisateurDto);

        NoteDto noteDtoResult = NoteDto.fromEntityToDto(
                noteRepository.save(
                        NoteDto.fromDtoToEntity(noteDto)
                )
        );

        boolean isExistBeforeDelete = noteRepository.findById(noteDtoResult.getId()).isPresent();

        noteRepository.deleteById(noteDtoResult.getId());

        boolean notExistAfterDelete = noteRepository.findById(noteDtoResult.getId()).isPresent();

        assertTrue(isExistBeforeDelete);

        assertFalse(notExistAfterDelete);

    }

}
