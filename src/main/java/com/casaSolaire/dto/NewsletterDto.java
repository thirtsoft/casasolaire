package com.casaSolaire.dto;

import com.casaSolaire.models.Newsletter;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class NewsletterDto {

    private Long id;

    private String code;

    private String customerEmail;

    private String subject;

    private String message;

    private Date dateInscription;

    public static NewsletterDto fromEntityToDto(Newsletter newsletter) {
        if (newsletter == null) {
            return null;
        }
        return NewsletterDto.builder()
                .id(newsletter.getId())
                .code(newsletter.getCode())
                .customerEmail(newsletter.getCustomerEmail())
                .subject(newsletter.getSubject())
                .message(newsletter.getMessage())
                .dateInscription(newsletter.getDateInscription())
                .build();
    }

    public static Newsletter fromDtoToEntity(NewsletterDto newsletterDto) {
        if (newsletterDto == null) {
            return null;
        }

        Newsletter newsletter = new Newsletter();
        newsletter.setId(newsletterDto.getId());
        newsletter.setCode(newsletterDto.getCode());
        newsletter.setCustomerEmail(newsletterDto.getCustomerEmail());
        newsletter.setSubject(newsletterDto.getSubject());
        newsletter.setMessage(newsletterDto.getMessage());
        newsletter.setDateInscription(newsletterDto.getDateInscription());

        return newsletter;
    }
}
