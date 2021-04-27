package com.casaSolaire.services;

import com.casaSolaire.dto.ArticleDto;
import com.casaSolaire.dto.CategoryDto;
import com.casaSolaire.models.Article;
import com.casaSolaire.repository.ArticleRepository;
import com.casaSolaire.services.impl.ArticleServiceImpl;
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
public class ArticleServiceTest {

    @InjectMocks
    private ArticleServiceImpl articleService;

    @Mock
    private ArticleRepository articleRepository;

    @Test
    public void CreateArticleTest() {
        CategoryDto categoryDto = CategoryDto.builder()
                .id(1L)
                .code("123")
                .designation("designation")
                .build();
        ArticleDto articleDto = ArticleDto.builder()
                .id(1L)
                .reference("Art1")
                .designation("Art1")
                .price(12000)
                .quantity(5)
                .photo("photo")
                .categoryDto(categoryDto)
                .build();
        Article article = ArticleDto.fromDtoToEntity(articleDto);
        when(articleRepository.save(article)).thenReturn(article);

        ArticleDto articleDtoSavedResult = articleService.save(articleDto);

        verify(articleRepository).save(article);
        assertThat(articleDto).isNotNull();
//        assertThat(articleDtoSavedResult).isEqualTo(articleDto);
        assertThat(articleDtoSavedResult.getId()).isEqualTo(article.getId());
        assertThat(articleDtoSavedResult.getReference()).isEqualTo(article.getReference());
        assertThat(articleDtoSavedResult.getDesignation()).isEqualTo(article.getDesignation());
    }

    @Test
    public void findAllTest() {
        CategoryDto categoryDto = CategoryDto.builder()
                .id(1L)
                .code("123")
                .designation("designation")
                .build();
        ArticleDto articleDto = ArticleDto.builder()
                .id(1L)
                .reference("Art1")
                .designation("Art1")
                .price(12000)
                .quantity(5)
                .photo("photo")
                .categoryDto(categoryDto)
                .build();
        Article article = ArticleDto.fromDtoToEntity(articleDto);
        when(articleRepository.findAll()).thenReturn(singletonList(article));

        List<ArticleDto> articleDtoList = articleService.findAll();

        assertThat(articleDtoList).isNotNull();
        assertThat(articleDtoList.size()).isEqualTo(1);
        verify(articleRepository).findAll();
        assertThat(articleDtoList.get(0)).isEqualTo(ArticleDto.fromEntityToDto(article));
    }

    @Test
    public void findByIdTest() {
        CategoryDto categoryDto = CategoryDto.builder()
                .id(1L)
                .code("123")
                .designation("designation")
                .build();
        ArticleDto articleDto = ArticleDto.builder()
                .id(1L)
                .reference("Art1")
                .designation("Art1")
                .price(12000)
                .quantity(5)
                .photo("photo")
                .categoryDto(categoryDto)
                .build();
        Optional<Article> article = Optional.ofNullable(ArticleDto.fromDtoToEntity(articleDto));
        when(articleRepository.findById(article.get().getId())).thenReturn(article);

        ArticleDto articleDtoSavedResult = articleService.findById(articleDto.getId());

        verify(articleRepository).findById(article.get().getId());
        assertThat(articleDto).isNotNull();
        //      assertThat(articleDtoSavedResult).isEqualTo(articleDto);
        assertThat(articleDtoSavedResult.getId()).isEqualTo(article.get().getId());

    }


}
