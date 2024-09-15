package com.casaSolaire.controllers;

import com.casaSolaire.controllers.api.CategoryApi;
import com.casaSolaire.dto.AddressDto;
import com.casaSolaire.dto.CategoryDto;
import com.casaSolaire.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class CategoryController implements CategoryApi {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @Override
    public ResponseEntity<CategoryDto> save(CategoryDto categoryDto) {
        return ResponseEntity.ok(categoryService.save(categoryDto));
    }

    @Override
    public ResponseEntity<CategoryDto> update(Long id, CategoryDto categoryDto) {
        categoryDto.setId(id);
        return ResponseEntity.ok(categoryService.save(categoryDto));
    }

    @Override
    public ResponseEntity<CategoryDto> findById(Long id) {
        return ResponseEntity.ok(categoryService.findById(id));
    }

    @Override
    public ResponseEntity<CategoryDto> findByDesignation(String designation) {
        return ResponseEntity.ok(categoryService.findByDesignation(designation));
    }

    @Override
    public List<CategoryDto> findAll() {
        return categoryService.findAll();
    }

    @Override
    public ResponseEntity<List<CategoryDto>> getAllCategoriesOrderByIdDesc() {
        List<CategoryDto> categoryDtoList = categoryService.findByOrderByIdDesc();
        return new ResponseEntity<>(categoryDtoList, HttpStatus.OK);
    }

    @Override
    public void delete(Long id) {
        categoryService.delete(id);
    }
}
