package com.casaSolaire.dto;

import com.casaSolaire.models.LigneCommande;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LigneCommandeDto {

    private Long id;

    private Long numero;

    private int quantity;

    private double price;

    private Long productId;

    private String productName;

    // @JsonIgnore
    private CommandeDto commandeDto;

    private ArticleDto articleDto;

    public static LigneCommandeDto fromEntityToDto(LigneCommande ligneCommande) {
        if (ligneCommande == null) {
            return null;
        }

        return LigneCommandeDto.builder()
                .id(ligneCommande.getId())
                .numero(ligneCommande.getNumero())
                .quantity(ligneCommande.getQuantity())
                .price(ligneCommande.getPrice())
                .productId(ligneCommande.getProductId())
                .productName(ligneCommande.getProductName())
                .commandeDto(CommandeDto.fromEntityToDto(ligneCommande.getCommande()))
                .articleDto(ArticleDto.fromEntityToDto(ligneCommande.getArticle()))
                .build();
    }

    public static LigneCommande fromDtoToEntity(LigneCommandeDto ligneCommandeDto) {
        if (ligneCommandeDto == null) {
            return null;
        }

        LigneCommande ligneCommande = new LigneCommande();
        ligneCommande.setId(ligneCommandeDto.getId());
        ligneCommande.setNumero(ligneCommandeDto.getNumero());
        ligneCommande.setQuantity(ligneCommandeDto.getQuantity());
        ligneCommande.setPrice(ligneCommandeDto.getPrice());
        ligneCommande.setProductId(ligneCommandeDto.getProductId());
        ligneCommande.setProductName(ligneCommande.getProductName());
        ligneCommande.setArticle(ArticleDto.fromDtoToEntity(ligneCommandeDto.getArticleDto()));
        ligneCommande.setCommande(CommandeDto.fromDtoToEntity(ligneCommandeDto.getCommandeDto()));

        return ligneCommande;
    }

}
