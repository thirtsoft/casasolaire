package com.casaSolaire.repository;

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
public class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    @Rollback(false)
    public void testCreateCategory() {
        CategoryDto categoryDto = new CategoryDto((long) 1, "pann", "Panneaux Solaire");
        CategoryDto categoryDtoResult = CategoryDto.fromEntityToDto(
                categoryRepository.save(
                        CategoryDto.fromDtoToEntity(categoryDto)
                )
        );

        assertNotNull(categoryDtoResult);

    }

    @Test
    @Rollback(false)
    public void TestUpdateCategory() {
        CategoryDto categoryDto = new CategoryDto((long) 1, "sac", "Lamp");
        CategoryDto categoryDtoResult = CategoryDto.fromEntityToDto(
                categoryRepository.save(
                        CategoryDto.fromDtoToEntity(categoryDto)
                )
        );

        String categoryDesignation = "SCIE";
        CategoryDto categoryUpdateDto = new CategoryDto((long) 1, "Bureau", categoryDesignation);

        categoryUpdateDto.setId((long) 1);
        CategoryDto.fromEntityToDto(categoryRepository.save(CategoryDto.fromDtoToEntity(categoryUpdateDto)));

        assertThat(categoryUpdateDto.getDesignation()).isEqualTo(categoryDesignation);

    }

    @Test
    public void testFindById() {
        CategoryDto categoryDto = new CategoryDto((long) 1, "sac", "sac a mai");
        CategoryDto categoryDtoResult = CategoryDto.fromEntityToDto(
                categoryRepository.save(
                        CategoryDto.fromDtoToEntity(categoryDto)
                )
        );

        boolean isCategory = categoryRepository.findById(categoryDtoResult.getId()).isPresent();

        assertTrue(isCategory);

    }

    @Test
    public void testFindByDesignation() {
        CategoryDto categoryDto = new CategoryDto((long) 1, "Robe", "RobeMariage");
        CategoryDto categoryDtoResult = CategoryDto.fromEntityToDto(
                categoryRepository.save(
                        CategoryDto.fromDtoToEntity(categoryDto)
                )
        );
        String catDesignation = "RobeMariage";
        assertThat(categoryDtoResult.getDesignation()).isEqualTo(catDesignation);
    }

    @Test
    public void testFindAll() {
        CategoryDto categoryDto = new CategoryDto((long) 1, "Robe", "sac a mai");
        CategoryDto categoryDtoResult = CategoryDto.fromEntityToDto(
                categoryRepository.save(
                        CategoryDto.fromDtoToEntity(categoryDto)
                )
        );
        CategoryDto categoryDto1 = new CategoryDto((long) 2, "Panthalon", "Panthalon homme");
        CategoryDto categoryDtoResult1 = CategoryDto.fromEntityToDto(
                categoryRepository.save(
                        CategoryDto.fromDtoToEntity(categoryDto1)
                )
        );

        CategoryDto categoryDto2 = new CategoryDto((long) 3, "Chemise", "Chemise Femme");
        CategoryDto categoryDtoResult2 = CategoryDto.fromEntityToDto(
                categoryRepository.save(
                        CategoryDto.fromDtoToEntity(categoryDto2)
                )
        );

        List<?> categories = categoryRepository.findAll();

        assertThat(categories).size().isGreaterThan(2);

    }

    @Test
    @Rollback(false)
    public void testDelete() {
        CategoryDto categoryDto = new CategoryDto((long) 1, "Chemise", "Chemise Femme");
        CategoryDto categoryDtoResult2 = CategoryDto.fromEntityToDto(
                categoryRepository.save(
                        CategoryDto.fromDtoToEntity(categoryDto)
                )
        );

        boolean isExistBeforeDelete = categoryRepository.findById(categoryDtoResult2.getId()).isPresent();

        categoryRepository.deleteById(categoryDtoResult2.getId());

        boolean notExistAfterDelete = categoryRepository.findById(categoryDtoResult2.getId()).isPresent();

        assertTrue(isExistBeforeDelete);

        assertFalse(notExistAfterDelete);

    }

}
