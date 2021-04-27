package com.casaSolaire.repository;

import com.casaSolaire.models.Article;
import com.casaSolaire.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
}
