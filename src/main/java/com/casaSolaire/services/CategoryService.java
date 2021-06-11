package com.casaSolaire.services;

import com.casaSolaire.dto.CategoryDto;

import java.util.List;

public interface CategoryService {

    CategoryDto save(CategoryDto categoryDto);

    CategoryDto update(Long id, CategoryDto categoryDto);

    CategoryDto findById(Long id);

    CategoryDto findByDesignation(String designation);

    List<CategoryDto> findAll();

    void delete(Long id);
}
