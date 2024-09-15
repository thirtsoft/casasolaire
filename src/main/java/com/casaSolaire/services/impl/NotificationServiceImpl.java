package com.casaSolaire.services.impl;

import com.casaSolaire.dto.ArticleDto;
import com.casaSolaire.dto.NotificationDto;
import com.casaSolaire.exceptions.ResourceNotFoundException;
import com.casaSolaire.models.Notification;
import com.casaSolaire.repository.NotificationRepository;
import com.casaSolaire.services.ArticleService;
import com.casaSolaire.services.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private final NotificationRepository notificationRepository;

    @Autowired
    private final ArticleService articleService;

    public NotificationServiceImpl(NotificationRepository notificationRepository,
                                   ArticleService articleService) {
        this.notificationRepository = notificationRepository;
        this.articleService = articleService;
    }

    @Override
    public NotificationDto save(NotificationDto notificationDto) {

        return NotificationDto.fromEntityToDto(
                notificationRepository.save(
                        NotificationDto.fromDtoToEntity(notificationDto)
                )
        );
    }

    @Override
    public NotificationDto saveNotificationToArticle(Long id, NotificationDto notificationDto) {
        ArticleDto articleDTOOptional = articleService.findById(id);
        notificationDto.setArticleDto(articleDTOOptional);

        return NotificationDto.fromEntityToDto(
                notificationRepository.save(
                        NotificationDto.fromDtoToEntity(notificationDto)
                )
        );

    }

    @Override
    public NotificationDto update(Long idNote, NotificationDto notificationDto) {
        if (!notificationRepository.existsById(idNote)) {
            throw new ResourceNotFoundException("Notification not found");
        }

        Optional<Notification> notificationOptional = notificationRepository.findById(idNote);

        if (!notificationOptional.isPresent()) {
            throw new ResourceNotFoundException("Notification not found");
        }

        NotificationDto notificationDTOResult = NotificationDto.fromEntityToDto(notificationOptional.get());
        notificationDTOResult.setNbreEtoile(notificationDto.getNbreEtoile());
        notificationDTOResult.setObservation(notificationDto.getObservation());
        //    notificationDTOResult.setUtilisateurDto(notificationDto.getUtilisateurDto());
        notificationDTOResult.setArticleDto(notificationDto.getArticleDto());

        return NotificationDto.fromEntityToDto(
                notificationRepository.save(
                        NotificationDto.fromDtoToEntity(notificationDTOResult)
                )
        );
    }

    @Override
    public BigDecimal countNumberOfNotificationDto() {
        return notificationRepository.countNumberOfNotification();
    }

    @Override
    public NotificationDto findById(Long id) {
        if (id == null) {
            log.error("Notification Id is null");
            return null;
        }

        Optional<Notification> notification = notificationRepository.findById(id);

        return Optional.of(NotificationDto.fromEntityToDto(notification.get())).orElseThrow(() ->
                new ResourceNotFoundException(
                        "Aucnun Notification avec l'Id = " + id + "n'a été trouvé")
        );
    }

    @Override
    public List<NotificationDto> findAll() {
        return notificationRepository.findAll().stream()
                .map(NotificationDto::fromEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<NotificationDto> findByOrderByIdDesc() {
        return notificationRepository.findByOrderByIdDesc().stream()
                .map(NotificationDto::fromEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public BigDecimal countNumberOfNotification() {
        return notificationRepository.countNumberOfNotification();
    }

    @Override
    public BigDecimal countNumberOfNotificationByProductId(String prodRef) {
        return notificationRepository.countNumberOfNotificationByProductId(prodRef);
    }

    @Override
    public List<NotificationDto> findTop4ByOrderByCreatedDateDescByProductId(String prodRef) {
        return notificationRepository.findTop4ByOrderByCreatedDateDesc(prodRef).stream()
                .map(NotificationDto::fromEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        if (id == null) {
            log.error("Notification Id is null");
            return;
        }

        notificationRepository.deleteById(id);

    }

}
