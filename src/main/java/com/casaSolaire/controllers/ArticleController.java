package com.casaSolaire.controllers;

import com.casaSolaire.controllers.api.ArticleApi;
import com.casaSolaire.dto.ArticleDto;
import com.casaSolaire.services.ArticleService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@RestController
@CrossOrigin
public class ArticleController implements ArticleApi {

    private final ArticleService articleService;

    private final String articlePhotosDir = "C://Users//Folio9470m//cs2//productphotos//";

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @Override
    public ResponseEntity<ArticleDto> save(ArticleDto articleDto) {
        return ResponseEntity.ok(articleService.save(articleDto));
    }

    @Override
    public ResponseEntity<ArticleDto> saveArticleWithFile(String article, MultipartFile photoArticle) throws IOException {
        ArticleDto articleDto = new ObjectMapper().readValue(article, ArticleDto.class);
        if (photoArticle != null && !photoArticle.isEmpty()) {
            articleDto.setPhoto(photoArticle.getOriginalFilename());
            photoArticle.transferTo(new File(articlePhotosDir + photoArticle.getOriginalFilename()));
        }

        return ResponseEntity.ok(articleService.save(articleDto));

    }


    @Override
    public ResponseEntity<ArticleDto> update(Long id, ArticleDto articleDto) {
        articleDto.setId(id);
        return ResponseEntity.ok(articleService.save(articleDto));
    }

    @Override
    public ResponseEntity<ArticleDto> findById(Long id) {
        return ResponseEntity.ok(articleService.findById(id));
    }

    @Override
    public ResponseEntity<ArticleDto> findByReference(String reference) {
        return ResponseEntity.ok(articleService.findByReference(reference));
    }

    @Override
    public List<ArticleDto> findAll() {
        return articleService.findAll();
    }

    @Override
    public List<ArticleDto> findListArticleByScategories(Long idScategory) {
        return articleService.findListArticleByScategories(idScategory);
    }

    @Override
    public List<ArticleDto> getListArticleByKeyword(String keyword) {
        return articleService.findListArticleByKeyword("%" + keyword + "%");
    }

    @Override
    public List<ArticleDto> getListArticleByPrice(double price) {
        return articleService.findListArticleGroupByPrice(price);
    }

    @Override
    public List<ArticleDto> getListArticleByPriceMinMax(String min, String max) {
        return articleService.findListArticleByPriceMinMax(Double.parseDouble(min), Double.parseDouble(max));
    }

    @Override
    public List<ArticleDto> getListArticleBySelected() {
        return articleService.findListArticleBySelected();
    }

    @Override
    public List<ArticleDto> getTop12ByOrderByCreateDateDesc() {
        return articleService.findTop12ByOrderByCreateDateDesc();
    }

    @Override
    public ResponseEntity<List<ArticleDto>> getAllArticlesOrderByIdDesc() {
        List<ArticleDto> articleDtoList = articleService.findByOrderByIdDesc();
        return new ResponseEntity<>(articleDtoList, HttpStatus.OK);
    }

    @Override
    public BigDecimal countNumberOfProductInSubCategory(Long subCatId) {
        return articleService.countNumberOfProductInSubCategory(subCatId);
    }

    @Override
    public Page<ArticleDto> getListArticleByPageable(int page, int size) {
        final Pageable pageable = PageRequest.of(page, size);
        return articleService.findArticleByPageable(pageable);
    }


    @Override
    public Page<ArticleDto> getListArticleByScategoryByPageable(Long scatId, int page, int size) {
        final Pageable pageable = PageRequest.of(page, size);
        return articleService.findArticleByScategoryPageables(scatId, pageable);
    }

    @Override
    public Page<ArticleDto> getListArticleBySamePriceyByPageable(double price, int page, int size) {
        final Pageable pageable = PageRequest.of(page, size);
        return articleService.findArticleBySamePricePageables(price, pageable);
    }


    @Override
    public void delete(Long id) {
        articleService.delete(id);
    }

    @Override
    public byte[] getPhotoArticle(Long id) throws Exception {

        ArticleDto articleDto = articleService.findById(id);

        System.out.println("Article DTO -- " + articleDto);
        System.out.println("Article DTO Designation -- " + articleDto.getDesignation());
        System.out.println("Article DTO Price -- " + articleDto.getPrice());
        System.out.println("Article DTO Photo -- " + articleDto.getPhoto());

        return Files.readAllBytes(Paths.get(System.getProperty("user.home") + "/cs2/productphotos/" + articleDto.getPhoto()));


    }

    @Override
    public void uploadPhotoArticle(MultipartFile file, Long id) throws IOException {
        //    ArticleDto articleDto = articleService.findById(Long.valueOf(id));
        ArticleDto articleDto = articleService.findById(id);
        articleDto.setPhoto(file.getOriginalFilename());
        Files.write(Paths.get(System.getProperty("user.home") + "/cs2/productphotos/" + articleDto.getPhoto()), file.getBytes());

        articleService.save(articleDto);
    }
}
