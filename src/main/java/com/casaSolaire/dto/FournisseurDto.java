package com.casaSolaire.dto;

import com.casaSolaire.models.Fournisseur;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FournisseurDto {

    private Long id;

    private String reference;

    private String firstName;

    private String lastName;

    private String email;

    private String telephone;

    private String city;

    private String town;

    private String rue;

    private String subject;

    private String message;

    private ArticleDto articleDto;

    public static FournisseurDto fromEntityToDto(Fournisseur fournisseur) {
        if (fournisseur == null) {
            return null;
        }
        return FournisseurDto.builder()
                .id(fournisseur.getId())
                .reference(fournisseur.getReference())
                .firstName(fournisseur.getFirstName())
                .lastName(fournisseur.getLastName())
                .email(fournisseur.getEmail())
                .telephone(fournisseur.getTelephone())
                .city(fournisseur.getCity())
                .town(fournisseur.getTown())
                .rue(fournisseur.getRue())
                .subject(fournisseur.getSubject())
                .message(fournisseur.getMessage())
                .articleDto(ArticleDto.fromEntityToDto(fournisseur.getArticle()))
                .build();
    }

    public static Fournisseur fromDtoToEntity(FournisseurDto fournisseurDto) {
        if (fournisseurDto == null) {
            return null;
        }
        Fournisseur fournisseur = new Fournisseur();
        fournisseur.setId(fournisseurDto.getId());
        fournisseur.setReference(fournisseurDto.getReference());
        fournisseur.setFirstName(fournisseurDto.getFirstName());
        fournisseur.setLastName(fournisseurDto.getLastName());
        fournisseur.setEmail(fournisseurDto.getEmail());
        fournisseur.setTelephone(fournisseurDto.getTelephone());
        fournisseur.setCity(fournisseurDto.getCity());
        fournisseur.setTown(fournisseurDto.getTown());
        fournisseur.setRue(fournisseurDto.getRue());
        fournisseur.setSubject(fournisseurDto.getSubject());
        fournisseur.setMessage(fournisseurDto.getMessage());
        fournisseur.setArticle(ArticleDto.fromDtoToEntity(fournisseurDto.getArticleDto()));

        return fournisseur;
    }
}
