package com.casaSolaire.repository;

import com.casaSolaire.models.Article;
import com.casaSolaire.models.Category;
import com.casaSolaire.models.Client;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ArticleRepositoryTest {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    @Rollback(false)
    public void testCreateArticle() {
        Long catId = (long) 2;
        Category category = categoryRepository.findById(catId).orElse(null);

        String reference = "Art1";
        String designation = "Article1";
        double price = 12000;
        String description = "blablablalba";
        Article articleDto = new Article(null, reference, designation, price, 12000, 5, true, description, "photo", category);

        Article articleDtoResult = articleRepository.save(articleDto);

        assertNotNull(articleDtoResult);

    }

    @Test
    @Rollback(false)
    public void TestUpdateArticle() {
        String reference = "Art1";
        String designation = "Article1";
        double price = 12000;
        String description = "blablablalba";
        Article articleDto = new Article();
        articleDto.setReference(reference);
        articleDto.setDesignation(designation);
        articleDto.setPrice(price);
        articleDto.setDescription(description);

        articleDto.setId((long) 3);

        Article articleDtoResult = articleRepository.save(articleDto);

        assertThat(articleDtoResult.getDesignation()).isEqualTo(designation);

    }

    @Test
    public void testFindById() {
        Long catId = (long) 1;
        Category category = categoryRepository.findById(catId).orElse(null);

        String reference = "Art1";
        String designation = "Article1";
        double price = 12000;
        String description = "blablablalba";
        Article articleDto = new Article(null, reference, designation, price, 12000, 5, true, description, "photo", category);

        Article articleDtoResult = articleRepository.save(articleDto);

        boolean isExitArticle = articleRepository.findById(articleDtoResult.getId()).isPresent();

        assertTrue(isExitArticle);

    }

    @Test
    public void testFindAll() {

        Long catId = (long) 1;
        Category category = categoryRepository.findById(catId).orElse(null);

        String reference = "Art1";
        String designation = "Article1";
        double price = 12000;
        String description = "blablablalba";
        Article articleDto = new Article(null, reference, designation, price, 12000, 5, true, description, "photo", category);

        articleRepository.save(articleDto);

        String referenceArticle = "Art1";
        String designationArticle = "Article1";
        double priceArticle = 12000;
        String descriptionArticle = "blablablalba";
        Article articleDto2 = new Article(null, referenceArticle, designationArticle, priceArticle, 12000, 5, true, descriptionArticle, "photo", category);

        articleRepository.save(articleDto2);

        List<Article> articleList = articleRepository.findAll();

        assertThat(articleList).size().isGreaterThan(0);

    }

    @Test
    @Rollback(false)
    public void testDelete() {
        Long catId = (long) 1;
        Category category = categoryRepository.findById(catId).orElse(null);

        String reference = "Art1";
        String designation = "Article1";
        double price = 12000;
        String description = "blablablalba";
        Article articleDto = new Article(null, reference, designation, price, 12000, 5, true, description, "photo", category);

        Article articleDelete = articleRepository.save(articleDto);

        boolean isExistBeforeDelete = articleRepository.findById(articleDelete.getId()).isPresent();

        articleRepository.deleteById(articleDelete.getId());

        boolean notExistAfterDelete = articleRepository.findById(articleDelete.getId()).isPresent();

        assertTrue(isExistBeforeDelete);

        assertFalse(notExistAfterDelete);

    }

}
