package com.casaSolaire.services;

import com.casaSolaire.dto.NewsletterDto;

import java.math.BigDecimal;
import java.util.List;

public interface NewsletterService {

    NewsletterDto save(NewsletterDto newsletterDto);

    NewsletterDto update(Long id, NewsletterDto newsletterDto);


    BigDecimal countNumberOfNewsletterDto();

    NewsletterDto findById(Long id);

    List<NewsletterDto> findAll();

    List<NewsletterDto> findByOrderByIdDesc();

    BigDecimal countNumberOfNewsletters();

    void delete(Long id);

}
