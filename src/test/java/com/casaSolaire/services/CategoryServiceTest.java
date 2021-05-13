package com.casaSolaire.services;


import com.casaSolaire.dto.CategoryDto;
import com.casaSolaire.models.Category;
import com.casaSolaire.repository.CategoryRepository;
import com.casaSolaire.services.impl.CategoryServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.Optional;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CategoryServiceTest {

    @InjectMocks
    private CategoryServiceImpl categorieService;

    @Mock
    private CategoryRepository categoryRepository;

    @Test
    public void CreateCategoryTest() {
        CategoryDto categoryDto;
        categoryDto = CategoryDto.builder()
                //        .id(null)
                .code("123")
                .designation("designation")
                .build();
        Category category = CategoryDto.fromDtoToEntity(categoryDto);
        when(categoryRepository.save(category)).thenReturn(category);

        CategoryDto categoryDtoSavedResult = categorieService.save(categoryDto);

        verify(categoryRepository).save(category);
        assertThat(categoryDto).isNotNull();
        assertThat(categoryDtoSavedResult).isEqualTo(categoryDto);
        assertThat(categoryDtoSavedResult.getId()).isEqualTo(category.getId());
        assertThat(categoryDtoSavedResult.getCode()).isEqualTo(category.getCode());
        assertThat(categoryDtoSavedResult.getDesignation()).isEqualTo(category.getDesignation());
    }

    @Test
    public void findAllTest() {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(1L);
        categoryDto.setCode("Mobile");
        categoryDto.setDesignation("Samsung A10s");

        Category category = CategoryDto.fromDtoToEntity(categoryDto);
        when(categoryRepository.findAll()).thenReturn(singletonList(category));

        List<CategoryDto> categories = categorieService.findAll();

        assertThat(categories).isNotNull();
        assertThat(categories.size()).isEqualTo(1);
        verify(categoryRepository).findAll();
        assertThat(categories.get(0)).isEqualTo(CategoryDto.fromEntityToDto(category));
    }

    @Test
    public void findByIdTest() {
        CategoryDto categoryDto = CategoryDto.builder()
                .id(1L)
                .code("123")
                .designation("designation")
                .build();
        Optional<Category> categorie = Optional.ofNullable(CategoryDto.fromDtoToEntity(categoryDto));
        when(categoryRepository.findById(categorie.get().getId())).thenReturn(categorie);

        CategoryDto categoryDtoSavedResult = categorieService.findById(categoryDto.getId());

        verify(categoryRepository).findById(categorie.get().getId());
        assertThat(categoryDto).isNotNull();
        assertThat(categoryDtoSavedResult).isEqualTo(categoryDto);
        assertThat(categoryDtoSavedResult.getId()).isEqualTo(categorie.get().getId());

    }

    @Test
    public void findByDesignationTest() {
        CategoryDto categoryDto = CategoryDto.builder()
                .id(1L)
                .code("123")
                .designation("designation")
                .build();
        Optional<Category> categorie = Optional.ofNullable(CategoryDto.fromDtoToEntity(categoryDto));
        when(categoryRepository.findCategoryByDesignation(categorie.get().getDesignation())).thenReturn(categorie);

        CategoryDto categoryDtoSavedResult = categorieService.findByDesignation(categoryDto.getDesignation());

        Assert.assertNotNull(categoryDto);
        verify(categoryRepository).findCategoryByDesignation(categorie.get().getDesignation());
        assertThat(categoryDto).isNotNull();
        assertThat(categoryDtoSavedResult).isEqualTo(categoryDto);
        assertThat(categoryDtoSavedResult.getDesignation()).isEqualTo(categorie.get().getDesignation());

    }


}
