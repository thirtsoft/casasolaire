package com.casaSolaire.dto;

import com.casaSolaire.models.Note;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NoteDto {

    private Long id;

    private String reference;

    private String nombreEtoile;

    private String observation;

    private ArticleDto articleDto;

    private UtilisateurDto utilisateurDto;

    public static NoteDto fromEntityToDto(Note note) {
        if (note == null) {
            return null;
        }
        return NoteDto.builder()
                .id(note.getId())
                .reference(note.getReference())
                .nombreEtoile(note.getNombreEtoile())
                .observation(note.getObservation())
                .articleDto(ArticleDto.fromEntityToDto(note.getArticle()))
                .utilisateurDto(UtilisateurDto.fromEntityToDto(note.getUtilisateur()))
                .build();
    }

    public static Note fromDtoToEntity(NoteDto noteDto) {
        if (noteDto == null) {
            return null;
        }
        Note note = new Note();
        note.setId(noteDto.getId());
        note.setReference(noteDto.getReference());
        note.setNombreEtoile(noteDto.getNombreEtoile());
        note.setObservation(note.getObservation());

        return note;
    }
}
