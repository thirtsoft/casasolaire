package com.casaSolaire.services;

import com.casaSolaire.dto.EmailDto;
import com.casaSolaire.dto.FournisseurDto;
import com.casaSolaire.dto.NewsletterDto;
import org.springframework.mail.MailException;

import java.math.BigDecimal;
import java.util.List;

public interface EmailService {

    void sendEmailToManager(EmailDto emailDto) throws MailException;

    void sendEmailToFournisseur(FournisseurDto fournisseurDto) throws MailException;

    void sendEmailToNewsletter(NewsletterDto newsletterDto) throws MailException;

    void sendMailToAllNewsletters(NewsletterDto newsletterDto);

    EmailDto findById(Long id);

    List<EmailDto> findAll();

    List<EmailDto> findByOrderByIdDesc();

    BigDecimal countNumberOfEmailInMonth();

    void delete(Long id);


}
