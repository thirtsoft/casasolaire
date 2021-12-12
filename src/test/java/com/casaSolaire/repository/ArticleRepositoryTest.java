package com.casaSolaire.repository;

import com.casaSolaire.models.Article;
import com.casaSolaire.models.Scategory;
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

    @Autowired
    private ScategoryRepository scategoryRepository;

    @Test
    @Rollback(false)
    public void testCreateArticle() {
        Long catId = (long) 2;
        Scategory scategory = scategoryRepository.findById(catId).orElse(null);

        String reference = "Art1";
        String designation = "Article1";
        double price = 12000;
        String description = "blablablalba";
        Article article = new Article(1L, reference, designation, price, 12000, 5, true, description, "photo", scategory);

        Article articleDtoResult = articleRepository.save(article);

        assertNotNull(articleDtoResult);

    }

    @Test
    @Rollback(false)
    public void TestUpdateArticle() {
        Long catId = (long) 2;
        Scategory scategory = scategoryRepository.findById(catId).orElse(null);
        String reference = "Art1";
        String designation = "Article1";
        double price = 12000;
        String description = "blablablalba";

        Article articleDto = new Article(null, reference, designation, price, 12000, 5, true, description, "photo", scategory);

        String refArticle = "Article";
        String designationArt = "Art";
        articleDto.setId(3L);
        articleDto.setReference(refArticle);
        articleDto.setDesignation(designationArt);

        Article articleDtoResult = articleRepository.save(articleDto);

        assertThat(articleDtoResult.getReference()).isEqualTo(refArticle);
        assertThat(articleDtoResult.getDesignation()).isEqualTo(designationArt);
        assertThat(articleDtoResult.getCurrentPrice()).isEqualTo(articleDto.getCurrentPrice());
        assertThat(articleDtoResult.getDescription()).isEqualTo(articleDto.getDescription());

    }

    @Test
    public void testFindById() {
        Long catId = (long) 2;
        Scategory scategory = scategoryRepository.findById(catId).orElse(null);

        String reference = "Art1";
        String designation = "Article1";
        double price = 12000;
        String description = "blablablalba";
        Article articleDto = new Article(null, reference, designation, price, 12000, 5, true, description, "photo", scategory);

        Article articleDtoResult = articleRepository.save(articleDto);

        boolean isExitArticle = articleRepository.findById(articleDtoResult.getId()).isPresent();

        assertTrue(isExitArticle);

    }

    @Test
    public void testFindAll() {

        Long catId = (long) 2;
        Scategory scategory = scategoryRepository.findById(catId).orElse(null);

        String reference = "Art1";
        String designation = "Article1";
        double price = 12000;
        String description = "blablablalba";
        Article articleDto = new Article(1L, reference, designation, price, 12000, 5, true, description, "photo", scategory);
        articleRepository.save(articleDto);

        String refCom = "Art1";
        String desCom = "Article1";
        double priceArt = 12000;
        String descriptionArt = "blablablalba";
        Article articleDto1 = new Article(2L, refCom, desCom, priceArt, 12000, 5, true, descriptionArt, "photo", scategory);
        articleRepository.save(articleDto1);

        List<Article> articleList = articleRepository.findAll();

        assertThat(articleList).size().isGreaterThan(0);

    }

    @Test
    @Rollback(false)
    public void testDelete() {
        Long catId = (long) 2;
        Scategory scategory = scategoryRepository.findById(catId).orElse(null);

        String reference = "Art1";
        String designation = "Article1";
        double price = 12000;
        String description = "blablablalba";
        Article articleDto = new Article(1L, reference, designation, price, 12000, 5, true, description, "photo", scategory);

        Article articleDelete = articleRepository.save(articleDto);

        boolean isExistBeforeDelete = articleRepository.findById(articleDelete.getId()).isPresent();

        articleRepository.deleteById(articleDelete.getId());

        boolean notExistAfterDelete = articleRepository.findById(articleDelete.getId()).isPresent();

        assertTrue(isExistBeforeDelete);

        assertFalse(notExistAfterDelete);

    }

}
