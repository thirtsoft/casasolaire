package com.casaSolaire.services;

import com.casaSolaire.dto.NotificationDto;

import java.math.BigDecimal;
import java.util.List;

public interface NotificationService {

    NotificationDto save(NotificationDto notificationDto);

    NotificationDto saveNotificationToArticle(Long id, NotificationDto notificationDto);

    NotificationDto update(Long idNote, NotificationDto notificationDto);

    BigDecimal countNumberOfNotificationDto();

    NotificationDto findById(Long id);

    List<NotificationDto> findAll();

    List<NotificationDto> findByOrderByIdDesc();

    List<NotificationDto> findTop4ByOrderByCreatedDateDescByProductId(String prodRef);

    BigDecimal countNumberOfNotification();

    BigDecimal countNumberOfNotificationByProductId(String prodRef);

    void delete(Long id);

}
