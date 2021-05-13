package com.casaSolaire.repository;

import com.casaSolaire.models.Category;
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
public class ScategoryRepositoryTest {

    @Autowired
    private ScategoryRepository scategoryRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    @Rollback(false)
    public void testCreateSCategory() {
        Long catId = (long) 2;
        Category category = categoryRepository.findById(catId).orElse(null);

        Scategory scategory = new Scategory(1L, "scat1", "scat1", category);

        Scategory ScategoryResult = scategoryRepository.save(scategory);

        assertNotNull(ScategoryResult);

    }

    @Test
    @Rollback(false)
    public void TestUpdateArticle() {
        Long catId = (long) 2;
        Category category = categoryRepository.findById(catId).orElse(null);

        Scategory scategory = new Scategory(1L, "scat1", "scat1", category);
        scategoryRepository.save(scategory);

        String codeScat = "scat22";
        scategory.setId(3L);
        scategory.setCode(codeScat);

        Scategory scategoryResult = scategoryRepository.save(scategory);

        assertThat(scategoryResult.getCode()).isEqualTo(codeScat);
        assertThat(scategoryResult.getLibelle()).isEqualTo(scategory.getLibelle());

    }

    @Test
    public void testFindById() {
        Long catId = (long) 2;
        Category category = categoryRepository.findById(catId).orElse(null);

        Scategory scategory = new Scategory(1L, "scat1", "scat1", category);
        Scategory scategoryResult = scategoryRepository.save(scategory);

        boolean isExitSCategory = scategoryRepository.findById(scategoryResult.getId()).isPresent();

        assertTrue(isExitSCategory);

    }

    @Test
    public void testFindAll() {

        Long catId = (long) 2;
        Category category = categoryRepository.findById(catId).orElse(null);

        Scategory scategory = new Scategory(1L, "scat1", "scat1", category);
        scategoryRepository.save(scategory);

        Scategory scategory1 = new Scategory(2L, "scat2", "scat2", category);
        scategoryRepository.save(scategory1);

        List<Scategory> scategoryList = scategoryRepository.findAll();

        assertThat(scategoryList).size().isGreaterThan(0);

    }

    @Test
    @Rollback(false)
    public void testDelete() {
        Long catId = (long) 2;
        Category category = categoryRepository.findById(catId).orElse(null);

        Scategory scategory = new Scategory(1L, "scat1", "scat1", category);
        Scategory scategoryDelete = scategoryRepository.save(scategory);

        boolean isExistBeforeDelete = scategoryRepository.findById(scategoryDelete.getId()).isPresent();

        scategoryRepository.deleteById(scategoryDelete.getId());

        boolean notExistAfterDelete = scategoryRepository.findById(scategoryDelete.getId()).isPresent();

        assertTrue(isExistBeforeDelete);

        assertFalse(notExistAfterDelete);

    }

}
