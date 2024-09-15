package com.casaSolaire.repository;

import com.casaSolaire.models.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    List<Notification> findByOrderByIdDesc();

    @Query("select count(c) from Notification c where month(c.createdDate) = month(current_date)")
    BigDecimal countNumberOfNotification();

    @Query("select count(c) from Notification c where c.article.reference =:prod")
    BigDecimal countNumberOfNotificationByProductId(@Param("prod") String prodRef);

    @Query("select n from Notification n where n.article.reference =:num")
    List<Notification> findTop4ByOrderByCreatedDateDesc(@Param("num") String prodRef);


}
