package com.casaSolaire.controllers;

import com.casaSolaire.controllers.api.EmailApi;
import com.casaSolaire.dto.EmailDto;
import com.casaSolaire.dto.FournisseurDto;
import com.casaSolaire.dto.NewsletterDto;
import com.casaSolaire.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@CrossOrigin
@RestController
public class EmailController implements EmailApi {

    private final EmailService emailService;

    @Autowired
    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }


    @Override
    public ResponseEntity<EmailDto> sendEmail(EmailDto emailDto) {
        try {
            emailDto.setCreateDate(new Date());
            emailService.sendEmailToManager(emailDto);
            return new ResponseEntity<>(emailDto, HttpStatus.OK);
        } catch (MailException e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<FournisseurDto> sendMailToFournisseur(FournisseurDto fournisseurDto) {
        try {
            emailService.sendEmailToFournisseur(fournisseurDto);
            return new ResponseEntity<>(fournisseurDto, HttpStatus.OK);
        } catch (MailException e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<NewsletterDto> sendMailToCustomer(NewsletterDto newsletterDto) {
        try {
            emailService.sendEmailToNewsletter(newsletterDto);
            return new ResponseEntity<>(newsletterDto, HttpStatus.OK);
        } catch (MailException e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<NewsletterDto> sendMailToAllCustomers(NewsletterDto newsletterDto) {
        try {
            emailService.sendMailToAllNewsletters(newsletterDto);
            return new ResponseEntity<>(newsletterDto, HttpStatus.OK);
        } catch (MailException e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<EmailDto> sendEmailToManager(EmailDto emailDto) {
        try {
            emailService.sendEmailToManager(emailDto);
            return new ResponseEntity<EmailDto>(emailDto, HttpStatus.OK);
        } catch (MailException e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<EmailDto> getEmailById(Long id) {
        return ResponseEntity.ok(emailService.findById(id));
    }

    @Override
    public ResponseEntity<List<EmailDto>> getAll() {
        List<EmailDto> emailDtoList = emailService.findAll();
        return new ResponseEntity(emailDtoList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<EmailDto>> getAllNewsletterOrderByIdDesc() {
        List<EmailDto> emailDtoList = emailService.findByOrderByIdDesc();
        return new ResponseEntity(emailDtoList, HttpStatus.OK);
    }

    @Override
    public BigDecimal countNumberOfEmail() {
        return emailService.countNumberOfEmailInMonth();
    }

    @Override
    public void delete(Long id) {
        emailService.delete(id);
    }

}
