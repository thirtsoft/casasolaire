package com.casaSolaire.services;

import com.casaSolaire.dto.CategoryDto;
import com.casaSolaire.models.Category;
import com.casaSolaire.repository.CategoryRepository;
import com.casaSolaire.services.impl.CategoryServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.Optional;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ScategoryServiceTest {

    @InjectMocks
    private CategoryServiceImpl categoryService;

    @Mock
    private CategoryRepository categoryRepository;

    @Test
    public void CreateCategoryTest() {
        CategoryDto categoryDto = CategoryDto.builder()
                .id(1L)
                .code("123")
                .designation("designation")
                .build();
        Category category = CategoryDto.fromDtoToEntity(categoryDto);
        when(categoryRepository.save(category)).thenReturn(category);

        CategoryDto categoryDtoSavedResult = categoryService.save(categoryDto);

        verify(categoryRepository).save(category);
        assertThat(categoryDto).isNotNull();
        assertThat(categoryDtoSavedResult).isEqualTo(categoryDto);
        assertThat(categoryDtoSavedResult.getId()).isEqualTo(category.getId());
        assertThat(categoryDtoSavedResult.getCode()).isEqualTo(category.getCode());
        assertThat(categoryDtoSavedResult.getDesignation()).isEqualTo(category.getDesignation());
    }

    @Test
    public void findAllTest() {
        CategoryDto categorieDto = new CategoryDto();
        categorieDto.setId(1L);
        categorieDto.setCode("Mobile");
        categorieDto.setDesignation("Samsung A10s");

        Category categorie = CategoryDto.fromDtoToEntity(categorieDto);
        when(categoryRepository.findAll()).thenReturn(singletonList(categorie));

        List<CategoryDto> categories = categoryService.findAll();

        assertThat(categories).isNotNull();
        assertThat(categories.size()).isEqualTo(1);
        verify(categoryRepository).findAll();
        assertThat(categories.get(0)).isEqualTo(CategoryDto.fromEntityToDto(categorie));
    }

    @Test
    public void findByIdTest() {
        CategoryDto categorieDto = CategoryDto.builder()
                .id(1L)
                .code("123")
                .designation("designation")
                .build();
        Optional<Category> categorie = Optional.ofNullable(CategoryDto.fromDtoToEntity(categorieDto));
        when(categoryRepository.findById(categorie.get().getId())).thenReturn(categorie);

        CategoryDto categoryDtoSavedResult = categoryService.findById(categorieDto.getId());

        verify(categoryRepository).findById(categorie.get().getId());
        assertThat(categorieDto).isNotNull();
        assertThat(categoryDtoSavedResult).isEqualTo(categorieDto);
        assertThat(categoryDtoSavedResult.getId()).isEqualTo(categorie.get().getId());

    }

    @Test
    public void findByDesignationTest() {
        CategoryDto categorieDto = CategoryDto.builder()
                .id(1L)
                .code("123")
                .designation("designation")
                .build();
        Optional<Category> categorie = Optional.ofNullable(CategoryDto.fromDtoToEntity(categorieDto));
        when(categoryRepository.findCategoryByDesignation(categorie.get().getDesignation())).thenReturn(categorie);

        CategoryDto categoryDtoSavedResult = categoryService.findByDesignation(categorieDto.getDesignation());

        assertNotNull(categorieDto);
        verify(categoryRepository).findCategoryByDesignation(categorie.get().getDesignation());
        assertThat(categorieDto).isNotNull();
        assertThat(categoryDtoSavedResult).isEqualTo(categorieDto);
        assertThat(categoryDtoSavedResult.getDesignation()).isEqualTo(categorie.get().getDesignation());

    }


}
