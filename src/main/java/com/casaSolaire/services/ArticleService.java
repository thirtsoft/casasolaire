package com.casaSolaire.services;

import com.casaSolaire.dto.ArticleDto;
import com.casaSolaire.dto.CategoryDto;

import java.util.List;

public interface ArticleService {

    ArticleDto save(ArticleDto articleDto);

    ArticleDto findById(Long id);

    List<ArticleDto> findAll();

    void delete(Long id);
}
