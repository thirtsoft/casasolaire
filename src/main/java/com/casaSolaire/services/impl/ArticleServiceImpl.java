package com.casaSolaire.services.impl;

import com.casaSolaire.dto.ArticleDto;
import com.casaSolaire.exceptions.ResourceNotFoundException;
import com.casaSolaire.models.Article;
import com.casaSolaire.repository.ArticleRepository;
import com.casaSolaire.services.ArticleService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private final ArticleRepository articleRepository;

    public ArticleServiceImpl(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Override
    public ArticleDto save(ArticleDto articleDto) {

        return ArticleDto.fromEntityToDto(
                articleRepository.save(
                        ArticleDto.fromDtoToEntity(articleDto)
                )
        );
    }

    @Override
    public ArticleDto saveArticleWithFile(String article, MultipartFile photoArticle) throws IOException {
        ArticleDto articleDto = new ObjectMapper().readValue(article, ArticleDto.class);
        System.out.println(articleDto);

        articleDto.setPhoto(photoArticle.getOriginalFilename());

        return ArticleDto.fromEntityToDto(
                articleRepository.save(
                        ArticleDto.fromDtoToEntity(articleDto)
                )
        );

    }


    @Override
    public ArticleDto update(Long id, ArticleDto articleDto) {

        if (!articleRepository.existsById(id)) {
            throw new ResourceNotFoundException("Article not found");
        }

        Optional<Article> article = articleRepository.findById(id);

        if (!article.isPresent()) {
            throw new ResourceNotFoundException("Article not found");
        }

        ArticleDto articleDtoResult = ArticleDto.fromEntityToDto(article.get());
        articleDtoResult.setReference(articleDto.getReference());
        articleDtoResult.setDesignation(articleDto.getDesignation());
        articleDtoResult.setPrice(articleDto.getPrice());
        articleDtoResult.setCurrentPrice(articleDto.getCurrentPrice());
        articleDtoResult.setQuantity(articleDto.getQuantity());
        articleDtoResult.setPhoto(articleDto.getPhoto());
        articleDtoResult.setSelected(articleDto.isSelected());
        articleDtoResult.setPromo(articleDto.isPromo());
        articleDtoResult.setDescription(articleDto.getDescription());
        articleDtoResult.setManufactured(articleDto.getManufactured());
        articleDtoResult.setScategoryDto(articleDto.getScategoryDto());

        return ArticleDto.fromEntityToDto(
                articleRepository.save(
                        ArticleDto.fromDtoToEntity(articleDtoResult)
                )
        );
    }

    @Override
    public ArticleDto findById(Long id) {
        if (id == null) {
            log.error("Article Id is null");
            return null;
        }

        Optional<Article> article = articleRepository.findById(id);

        return Optional.of(ArticleDto.fromEntityToDto(article.get())).orElseThrow(() ->
                new ResourceNotFoundException(
                        "Aucnun article avec l'Id = " + id + "n'a été trouvé")
        );
    }

    @Override
    public ArticleDto findByReference(String reference) {
        if (!StringUtils.hasLength(reference)) {
            log.error("Article REFERENCE is null");
        }

        Optional<Article> article = articleRepository.findArticleByReference(reference);

        return Optional.of(ArticleDto.fromEntityToDto(article.get())).orElseThrow(() ->
                new ResourceNotFoundException(
                        "Aucnun article avec l'Id = " + reference + "n'a été trouvé")
        );


    }

    @Override
    public List<ArticleDto> findAll() {
        return articleRepository.findAll().stream()
                .map(ArticleDto::fromEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ArticleDto> findListArticleByScategories(Long scatId) {
        if (scatId == null) {
            log.error("Article Scategory is null");
        }
        return articleRepository.findArticleByScategory(scatId).stream()
                .map(ArticleDto::fromEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ArticleDto> findListArticleByKeyword(String keyword) {
        if (keyword == null) {
            log.error("Article not found");
        }
        return articleRepository.findArticleByKeyword(keyword).stream()
                .map(ArticleDto::fromEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ArticleDto> findListArticleGroupByPrice(double price) {
        return articleRepository.findArticleGroupByPrice(price).stream()
                .map(ArticleDto::fromEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ArticleDto> findListArticleByPriceMinMax(double min, double max) {
        return articleRepository.findListArticleByPriceMinMax(min, max).stream()
                .map(ArticleDto::fromEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ArticleDto> findListArticleBySelected() {
        return articleRepository.findArticleBySelected().stream()
                .map(ArticleDto::fromEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ArticleDto> findTop12ByOrderByCreateDateDesc() {
        return articleRepository.findTop12ByOrderByCreateDateDesc().stream()
                .map(ArticleDto::fromEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ArticleDto> findByOrderByIdDesc() {
        return articleRepository.findByOrderByIdDesc().stream()
                .map(ArticleDto::fromEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Page<ArticleDto> findArticleByPageable(Pageable pageable) {
        return articleRepository.findArticle(pageable)
                .map(ArticleDto::fromEntityToDto);
    }

    @Override
    public Page<ArticleDto> findArticleByScategoryPageables(Long scatId, Pageable pageable) {
        return articleRepository.findArticleByScategoryPageables(scatId, pageable)
                .map(ArticleDto::fromEntityToDto);
    }

    @Override
    public Page<ArticleDto> findArticleBySamePricePageables(double price, Pageable pageable) {
        return articleRepository.findArticlePageableGroupByPrice(price, pageable)
                .map(ArticleDto::fromEntityToDto);
    }

    @Override
    public BigDecimal countNumberOfProductInSubCategory(Long scatId) {
        return articleRepository.countNumberOfProductInSubCategory(scatId);
    }

    @Override
    public void delete(Long id) {
        if (id == null) {
            log.error("Article Id is null");
            return;
        }
        articleRepository.deleteById(id);

    }
}
