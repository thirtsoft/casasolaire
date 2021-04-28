package com.casaSolaire.repository;

import com.casaSolaire.models.Article;
import com.casaSolaire.models.Category;
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
        Category categoryDto = new Category((long) 1, "pann", "Panneaux Solaire");
        String reference = "Art1";
        String designation = "Article1";
        double price = 12000;
        String description = "blablablalba";
        Article articleDto = new Article();
        articleDto.setReference(reference);
        articleDto.setDesignation(designation);
        articleDto.setPrice(price);
        articleDto.setDescription(description);
        articleDto.setCategory(categoryDto);

        Article articleDtoResult = articleRepository.save(articleDto);

        assertNotNull(articleDtoResult);

    }

    @Test
    @Rollback(false)
    public void TestUpdateArticle() {
        Category categoryDto = new Category((long) 1, "pann", "Panneaux Solaire");
        String reference = "Art1";
        String designation = "Article1";
        double price = 12000;
        String description = "blablablalba";
        Article articleDto = new Article();
        articleDto.setReference(reference);
        articleDto.setDesignation(designation);
        articleDto.setPrice(price);
        articleDto.setDescription(description);
        articleDto.setCategory(categoryDto);

        Article articleDtoResult = articleRepository.save(articleDto);

        String desingationArticle = "Article2";
        articleDto.setDesignation(desingationArticle);
        articleDto.setId((long) 1);
        articleRepository.save(articleDto);

        assertThat(articleDto.getDesignation()).isEqualTo(desingationArticle);

    }

    @Test
    public void testFindById() {
        Category categoryDto = new Category((long) 1, "pann", "Panneaux Solaire");
        String reference = "Art1";
        String designation = "Article1";
        double price = 12000;
        String description = "blablablalba";
        Article articleDto = new Article();
        articleDto.setReference(reference);
        articleDto.setDesignation(designation);
        articleDto.setPrice(price);
        articleDto.setDescription(description);
        articleDto.setCategory(categoryDto);

        Article articleDtoResult = articleRepository.save(articleDto);

        boolean isCategory = articleRepository.findById(articleDtoResult.getId()).isPresent();

        assertTrue(isCategory);

    }

    @Test
    public void testFindAll() {
        Category categoryDto = new Category((long) 1, "pann", "Panneaux Solaire");
        String reference = "Art1";
        String designation = "Article1";
        double price = 12000;
        String description = "blablablalba";
        Article articleDto = new Article();
        articleDto.setReference(reference);
        articleDto.setDesignation(designation);
        articleDto.setPrice(price);
        articleDto.setDescription(description);
        articleDto.setCategory(categoryDto);

        Article articleDtoResult = articleRepository.save(articleDto);

        String reference1 = "Art2";
        String designation1 = "Article1";
        double price1 = 12000;
        String description1 = "blablablalba";
        Article articleDto1 = new Article();
        articleDto1.setReference(reference1);
        articleDto1.setDesignation(designation1);
        articleDto.setPrice(price1);
        articleDto1.setDescription(description1);
        articleDto1.setCategory(categoryDto);

        Article articleDtoResult1 = articleRepository.save(articleDto1);

        List<?> articles = articleRepository.findAll();

        assertThat(articles).size().isGreaterThan(0);

    }

    @Test
    @Rollback(false)
    public void testDelete() {
        Category categoryDto = new Category((long) 1, "pann", "Panneaux Solaire");
        String reference = "Art1";
        String designation = "Article1";
        double price = 12000;
        String description = "blablablalba";
        Article articleDto = new Article();
        articleDto.setReference(reference);
        articleDto.setDesignation(designation);
        articleDto.setPrice(price);
        articleDto.setDescription(description);
        articleDto.setCategory(categoryDto);

        Article articleDtoResult = articleRepository.save(articleDto);

        boolean isExistBeforeDelete = articleRepository.findById(articleDtoResult.getId()).isPresent();

        articleRepository.deleteById(articleDtoResult.getId());

        boolean notExistAfterDelete = articleRepository.findById(articleDtoResult.getId()).isPresent();

        assertTrue(isExistBeforeDelete);

        assertFalse(notExistAfterDelete);

    }

}
