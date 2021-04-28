package com.casaSolaire.repository;

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
public class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    @Rollback(false)
    public void testCreateCategory() {
        Category categoryDto = new Category((long) 1, "pann", "Panneaux Solaire");
        Category categoryDtoResult = categoryRepository.save(categoryDto);

        assertNotNull(categoryDtoResult);

    }

    @Test
    @Rollback(false)
    public void TestUpdateCategory() {
        Category categoryDto = new Category((long) 1, "sac", "Lamp");
        Category categoryDtoResult = categoryRepository.save(categoryDto);

        String categoryDesignation = "SCIE";
        Category categoryUpdateDto = new Category((long) 1, "Bureau", categoryDesignation);

        categoryUpdateDto.setId((long) 1);
        categoryRepository.save(categoryUpdateDto);

        assertThat(categoryUpdateDto.getDesignation()).isEqualTo(categoryDesignation);

    }

    @Test
    public void testFindById() {
        Category categoryDto = new Category((long) 1, "sac", "sac a mai");
        Category categoryDtoResult = categoryRepository.save(categoryDto);

        boolean isCategory = categoryRepository.findById(categoryDtoResult.getId()).isPresent();

        assertTrue(isCategory);

    }

    @Test
    public void testFindByDesignation() {
        Category categoryDto = new Category((long) 1, "Robe", "RobeMariage");
        Category categoryDtoResult = categoryRepository.save(categoryDto);
        String catDesignation = "RobeMariage";
        assertThat(categoryDtoResult.getDesignation()).isEqualTo(catDesignation);
    }

    @Test
    public void testFindAll() {
        Category categoryDto = new Category((long) 1, "Robe", "sac a mai");
        Category categoryDtoResult = categoryRepository.save(categoryDto);

        Category categoryDto1 = new Category((long) 2, "Panthalon", "Panthalon homme");
        Category categoryDtoResult1 = categoryRepository.save(categoryDto1);

        Category categoryDto2 = new Category((long) 3, "Chemise", "Chemise Femme");
        Category categoryDtoResult2 = categoryRepository.save(categoryDto2);

        List<?> categories = categoryRepository.findAll();

        assertThat(categories).size().isGreaterThan(2);

    }

    @Test
    @Rollback(false)
    public void testDelete() {
        Category categoryDto = new Category((long) 1, "Chemise", "Chemise Femme");
        Category categoryDtoResult2 = categoryRepository.save(categoryDto);

        boolean isExistBeforeDelete = categoryRepository.findById(categoryDtoResult2.getId()).isPresent();

        categoryRepository.deleteById(categoryDtoResult2.getId());

        boolean notExistAfterDelete = categoryRepository.findById(categoryDtoResult2.getId()).isPresent();

        assertTrue(isExistBeforeDelete);

        assertFalse(notExistAfterDelete);

    }

}
