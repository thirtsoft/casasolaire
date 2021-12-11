package com.casaSolaire.dto;

import com.casaSolaire.models.Article;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ArticleDto {

    private Long id;

    private String reference;

    private String designation;

    private int quantity;

    private int quantite;

    private double price;

    private double currentPrice;

    private boolean promo;

    private boolean selected;

    private String description;

    private String manufactured;

    private String photo;

    private ScategoryDto scategoryDto;

    public ArticleDto(long id, String reference, String designation, int quantity,
                      double price, double currentPrice, boolean promo, boolean selected,
                      String description, String photo, ScategoryDto scategoryDto) {
        this.id = id;
        this.reference = reference;
        this.designation = designation;
        this.quantity = quantity;
        this.price = price;
        this.currentPrice = currentPrice;
        this.promo = promo;
        this.selected = selected;
        this.description = description;
        this.photo = photo;
        this.scategoryDto = scategoryDto;
    }

    public static ArticleDto fromEntityToDto(Article article) {
        if (article == null) {
            return null;
        }

        return ArticleDto.builder()
                .id(article.getId())
                .reference(article.getReference())
                .designation(article.getDesignation())
                .quantity(article.getQuantity())
                .quantite(article.getQuantite())
                .price(article.getPrice())
                .currentPrice(article.getCurrentPrice())
                .promo(article.isPromo())
                .selected(article.isSelected())
                .description(article.getDescription())
                .manufactured(article.getManufactured())
                .photo(article.getPhoto())
                .scategoryDto(ScategoryDto.fromEntityToDto(article.getScategory()))
                .build();
    }

    public static Article fromDtoToEntity(ArticleDto articleDto) {
        if (articleDto == null) {
            return null;
        }

        Article article = new Article();
        article.setId(articleDto.getId());
        article.setReference(articleDto.getReference());
        article.setDesignation(articleDto.getDesignation());
        article.setQuantity(articleDto.getQuantity());
        article.setQuantite(articleDto.getQuantite());
        article.setPrice(articleDto.getPrice());
        article.setCurrentPrice(articleDto.getCurrentPrice());
        article.setPromo(articleDto.isPromo());
        article.setSelected(articleDto.isSelected());
        article.setDescription(articleDto.getDescription());
        article.setManufactured(articleDto.getManufactured());
        article.setPhoto(articleDto.getPhoto());
        article.setScategory(ScategoryDto.fromDtoToEntity(articleDto.getScategoryDto()));

        return article;
    }

}
