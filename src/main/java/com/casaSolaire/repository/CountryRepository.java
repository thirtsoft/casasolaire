package com.casaSolaire.repository;

import com.casaSolaire.models.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {

    List<Country> findByOrderByIdDesc();

    @Query("select count(p) from Country p ")
    BigDecimal countNumberOfCountry();
}
