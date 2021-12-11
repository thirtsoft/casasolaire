package com.casaSolaire.controllers;

import com.casaSolaire.controllers.api.NewsletterApi;
import com.casaSolaire.dto.NewsletterDto;
import com.casaSolaire.services.NewsletterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class NewsletterController implements NewsletterApi {

    private final NewsletterService newsletterService;

    public NewsletterController(NewsletterService newsletterService) {
        this.newsletterService = newsletterService;
    }

    @Override
    public ResponseEntity<NewsletterDto> save(NewsletterDto newsletterDto) {
        newsletterDto.setCode("ISNCRIPT" + Math.random() * 100000);
        newsletterDto.setDateInscription(new Date());
        return ResponseEntity.ok(newsletterService.save(newsletterDto));
    }

    @Override
    public ResponseEntity<NewsletterDto> update(Long id, NewsletterDto newsletterDto) {
        newsletterDto.setId(id);
        return ResponseEntity.ok(newsletterService.save(newsletterDto));
    }

    @Override
    public ResponseEntity<NewsletterDto> findById(Long id) {
        return ResponseEntity.ok(newsletterService.findById(id));
    }

    @Override
    public ResponseEntity<List<NewsletterDto>> findAll() {
        List<NewsletterDto> newsletterDtoList = newsletterService.findAll();
        return new ResponseEntity(newsletterDtoList, HttpStatus.OK);
    }


    @Override
    public ResponseEntity<List<NewsletterDto>> getAllNewslettersOrderByIdDesc() {
        List<NewsletterDto> newsletterDtoList = newsletterService.findByOrderByIdDesc();
        return new ResponseEntity(newsletterDtoList, HttpStatus.OK);
    }

    @Override
    public BigDecimal countNumberOfNewsletters() {
        return newsletterService.countNumberOfNewsletters();
    }

    @Override
    public void delete(Long id) {
        newsletterService.delete(id);
    }

}
