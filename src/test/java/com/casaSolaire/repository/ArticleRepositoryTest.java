package com.casaSolaire.repository;

import com.casaSolaire.dto.ArticleDto;
import com.casaSolaire.dto.CategoryDto;
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
public class ArticleRepositoryTest {

    @Autowired
    private ArticleRepository articleRepository;

    @Test
    @Rollback(false)
    public void testCreateCategory() {
        CategoryDto categoryDto = new CategoryDto((long) 1, "pann", "Panneaux Solaire");
        String reference = "Art1";
        String designation = "Article1";
        double price = 12000;
        String description = "blablablalba";
        ArticleDto articleDto = new ArticleDto();
        articleDto.setReference(reference);
        articleDto.setDesignation(designation);
        articleDto.setPrice(price);
        articleDto.setDescription(description);
        articleDto.setCategoryDto(categoryDto);

        ArticleDto articleDtoResult = ArticleDto.fromEntityToDto(
                articleRepository.save(
                        ArticleDto.fromDtoToEntity(articleDto)
                )
        );

        assertNotNull(articleDtoResult);

    }

    @Test
    @Rollback(false)
    public void TestUpdateArticle() {
        String designationCategory = "Solaire";
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setDesignation(designationCategory);
        String reference = "Art1";
        String designation = "Article1";
        double price = 12000;
        String description = "blablablalba";
        ArticleDto articleDto = new ArticleDto();
        articleDto.setReference(reference);
        articleDto.setDesignation(designation);
        articleDto.setPrice(price);
        articleDto.setDescription(description);
        articleDto.setCategoryDto(categoryDto);

        ArticleDto articleDtoResult = ArticleDto.fromEntityToDto(
                articleRepository.save(
                        ArticleDto.fromDtoToEntity(articleDto)
                )
        );

        String desingationArticle = "Article2";
        articleDto.setDesignation(desingationArticle);
        articleDto.setId((long) 1);
        ArticleDto.fromEntityToDto(articleRepository.save(ArticleDto.fromDtoToEntity(articleDto)));

        assertThat(articleDto.getDesignation()).isEqualTo(desingationArticle);

    }

    @Test
    public void testFindById() {
        String designationCategory = "Solaire";
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setDesignation(designationCategory);
        String reference = "Art1";
        String designation = "Article1";
        double price = 12000;
        String description = "blablablalba";
        ArticleDto articleDto = new ArticleDto();
        articleDto.setReference(reference);
        articleDto.setDesignation(designation);
        articleDto.setPrice(price);
        articleDto.setDescription(description);
        articleDto.setCategoryDto(categoryDto);

        ArticleDto articleDtoResult = ArticleDto.fromEntityToDto(
                articleRepository.save(
                        ArticleDto.fromDtoToEntity(articleDto)
                )
        );

        boolean isCategory = articleRepository.findById(articleDtoResult.getId()).isPresent();

        assertTrue(isCategory);

    }

    @Test
    public void testFindAll() {
        String designationCategory = "Solaire";
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setDesignation(designationCategory);
        String reference = "Art1";
        String designation = "Article1";
        double price = 12000;
        String description = "blablablalba";
        ArticleDto articleDto = new ArticleDto();
        articleDto.setReference(reference);
        articleDto.setDesignation(designation);
        articleDto.setPrice(price);
        articleDto.setDescription(description);
        articleDto.setCategoryDto(categoryDto);

        ArticleDto articleDtoResult = ArticleDto.fromEntityToDto(
                articleRepository.save(
                        ArticleDto.fromDtoToEntity(articleDto)
                )
        );

        String reference1 = "Art2";
        String designation1 = "Article1";
        double price1 = 12000;
        String description1 = "blablablalba";
        ArticleDto articleDto1 = new ArticleDto();
        articleDto1.setReference(reference1);
        articleDto1.setDesignation(designation1);
        articleDto.setPrice(price1);
        articleDto1.setDescription(description1);
        articleDto1.setCategoryDto(categoryDto);

        ArticleDto articleDtoResult1 = ArticleDto.fromEntityToDto(
                articleRepository.save(
                        ArticleDto.fromDtoToEntity(articleDto1)
                )
        );

        List<?> articles = articleRepository.findAll();

        assertThat(articles).size().isGreaterThan(0);

    }

    @Test
    @Rollback(false)
    public void testDelete() {
        String designationCategory = "Solaire";
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setDesignation(designationCategory);
        String reference = "Art1";
        String designation = "Article1";
        double price = 12000;
        String description = "blablablalba";
        ArticleDto articleDto = new ArticleDto();
        articleDto.setReference(reference);
        articleDto.setDesignation(designation);
        articleDto.setPrice(price);
        articleDto.setDescription(description);
        articleDto.setCategoryDto(categoryDto);

        ArticleDto articleDtoResult = ArticleDto.fromEntityToDto(
                articleRepository.save(
                        ArticleDto.fromDtoToEntity(articleDto)
                )
        );

        boolean isExistBeforeDelete = articleRepository.findById(articleDtoResult.getId()).isPresent();

        articleRepository.deleteById(articleDtoResult.getId());

        boolean notExistAfterDelete = articleRepository.findById(articleDtoResult.getId()).isPresent();

        assertTrue(isExistBeforeDelete);

        assertFalse(notExistAfterDelete);

    }

}
