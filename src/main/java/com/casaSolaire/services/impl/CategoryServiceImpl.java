package com.casaSolaire.services.impl;

import com.casaSolaire.dto.CategoryDto;
import com.casaSolaire.exceptions.ResourceNotFoundException;
import com.casaSolaire.models.Category;
import com.casaSolaire.repository.CategoryRepository;
import com.casaSolaire.services.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryDto save(CategoryDto categoryDto) {
        return CategoryDto.fromEntityToDto(
                categoryRepository.save(
                        CategoryDto.fromDtoToEntity(categoryDto)
                )
        );
    }

    @Override
    public CategoryDto update(Long id, CategoryDto categoryDto) {
        if (!categoryRepository.existsById(id)) {
            throw new ResourceNotFoundException("Category not found");
        }

        Optional<Category> category = categoryRepository.findById(id);

        if (!category.isPresent()) {
            throw new ResourceNotFoundException("Category not found");
        }

        CategoryDto categoryResult = CategoryDto.fromEntityToDto(category.get());

        categoryResult.setCode(categoryDto.getCode());
        categoryResult.setDesignation(categoryDto.getDesignation());

        return CategoryDto.fromEntityToDto(
                categoryRepository.save(
                        CategoryDto.fromDtoToEntity(categoryResult)
                )
        );
    }

    @Override
    public CategoryDto findById(Long id) {
        if (id == null) {
            log.error("Category Id is null");
            return null;
        }

        Optional<Category> category = categoryRepository.findById(id);

        return Optional.of(CategoryDto.fromEntityToDto(category.get())).orElseThrow(() ->
                new ResourceNotFoundException(
                        "Aucnun categorie avec l'Id = " + id + "n'a été trouvé")
        );
    }

    @Override
    public CategoryDto findByDesignation(String designation) {
        if (!StringUtils.hasLength(designation)) {
            log.error("Category Designation is null");
        }

        Optional<Category> category = categoryRepository.findCategoryByDesignation(designation);

        return Optional.of(CategoryDto.fromEntityToDto(category.get())).orElseThrow(() ->
                new ResourceNotFoundException(
                        "No category with l'Id = " + designation + "n'a été found")
        );
    }

    @Override
    public List<CategoryDto> findAll() {
        return categoryRepository.findAll().stream()
                .map(CategoryDto::fromEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        if (id == null) {
            log.error("Category Id is null");
            return;
        }
        categoryRepository.deleteById(id);
    }
}
