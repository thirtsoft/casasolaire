package com.casaSolaire.services.impl;

import com.casaSolaire.dto.NewsletterDto;
import com.casaSolaire.exceptions.ResourceNotFoundException;
import com.casaSolaire.models.Newsletter;
import com.casaSolaire.repository.NewsletterRepository;
import com.casaSolaire.services.NewsletterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class NewsletterServiceImpl implements NewsletterService {

    private final NewsletterRepository newsletterRepository;

    public NewsletterServiceImpl(NewsletterRepository newsletterRepository) {
        this.newsletterRepository = newsletterRepository;
    }

    @Override
    public NewsletterDto save(NewsletterDto newsletterDto) {

        return NewsletterDto.fromEntityToDto(
                newsletterRepository.save(
                        NewsletterDto.fromDtoToEntity(newsletterDto)
                )
        );
    }

    @Override
    public NewsletterDto update(Long id, NewsletterDto newsletterDto) {
        if (!newsletterRepository.existsById(id)) {
            throw new ResourceNotFoundException("Newsletter not found");
        }

        Optional<Newsletter> optionalNewsletter = newsletterRepository.findById(id);

        if (!optionalNewsletter.isPresent()) {
            throw new ResourceNotFoundException("Newsletter not found");
        }

        NewsletterDto newsletterDTOResult = NewsletterDto.fromEntityToDto(optionalNewsletter.get());
        newsletterDTOResult.setCode(newsletterDto.getCode());
        newsletterDTOResult.setCustomerEmail(newsletterDto.getCustomerEmail());
        newsletterDTOResult.setDateInscription(new Date());

        return NewsletterDto.fromEntityToDto(
                newsletterRepository.save(
                        NewsletterDto.fromDtoToEntity(newsletterDTOResult)
                )
        );
    }

    @Override
    public NewsletterDto findById(Long id) {
        if (id == null) {
            log.error("Newsletter Id is null");
            return null;
        }

        Optional<Newsletter> optionalNewsletter = newsletterRepository.findById(id);

        return Optional.of(NewsletterDto.fromEntityToDto(optionalNewsletter.get())).orElseThrow(() ->
                new ResourceNotFoundException(
                        "Aucnun Newsletter avec l'Id = " + id + "n'a été trouvé")
        );
    }

    @Override
    public List<NewsletterDto> findAll() {
        return newsletterRepository.findAll().stream()
                .map(NewsletterDto::fromEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<NewsletterDto> findByOrderByIdDesc() {
        return newsletterRepository.findByOrderByIdDesc().stream()
                .map(NewsletterDto::fromEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public BigDecimal countNumberOfNewsletters() {
        return newsletterRepository.countNumberOfNewsletters();
    }

    @Override
    public void delete(Long id) {
        if (id == null) {
            log.error("Newsletter Id is null");
            return;
        }

        newsletterRepository.deleteById(id);
    }

}
