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

    private double price;

    private double currentPrice;

    private int quantity;

    private String description;

    private boolean promo;

    private String photo;

    private ScategoryDto scategoryDto;

    public static ArticleDto fromEntityToDto(Article article) {
        if (article == null) {
            return null;
        }
        return ArticleDto.builder()
                .id(article.getId())
                .reference(article.getReference())
                .designation(article.getDesignation())
                .price(article.getPrice())
                .currentPrice(article.getCurrentPrice())
                .quantity(article.getQuantity())
                .description(article.getDescription())
                .promo(article.isPromo())
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
        article.setPrice(articleDto.getPrice());
        article.setCurrentPrice(articleDto.getCurrentPrice());
        article.setQuantity(articleDto.getQuantity());
        article.setDescription(articleDto.getDescription());
        article.setPromo(articleDto.isPromo());
        article.setPhoto(articleDto.getPhoto());
        article.setScategory(ScategoryDto.fromDtoToEntity(articleDto.getScategoryDto()));

        return article;
    }
}
