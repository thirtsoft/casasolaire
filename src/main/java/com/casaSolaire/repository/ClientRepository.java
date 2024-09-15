package com.casaSolaire.repository;

import com.casaSolaire.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    List<Client> findByOrderByIdDesc();

    @Query("select count(p) from Client p ")
    BigDecimal countNumberOfClient();
}
