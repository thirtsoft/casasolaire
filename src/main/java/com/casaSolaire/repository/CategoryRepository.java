package com.casaSolaire.repository;

import com.casaSolaire.models.Address;
import com.casaSolaire.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findCategoryByDesignation(String designation);

    List<Category> findByOrderByIdDesc();
}
