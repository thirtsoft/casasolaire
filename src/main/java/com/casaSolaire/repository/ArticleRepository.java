package com.casaSolaire.repository;

import com.casaSolaire.models.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

    Optional<Article> findArticleByReference(String reference);

    @Query("select p from Article p where p.scategory.id =:scat")
    List<Article> findArticleByScategory(@Param("scat") Long scatId);

    List<Article> findTop12ByOrderByCreateDateDesc();

    List<Article> findByOrderByIdDesc();

    @Query("select art from Article art where art.price like :price GROUP BY (art.price, art.id) ")
    List<Article> findArticleGroupByPrice(@Param("price") double price);

    @Query("select p from Article p where p.price between :min and :max")
    List<Article> findListArticleByPriceMinMax(@Param("min") double min, @Param("max") double max);

    @Query("select art from Article art where art.selected = true")
    List<Article> findArticleBySelected();

    @Query("select art from Article art where art.designation like :x")
    List<Article> findArticleByKeyword(@Param("x") String mc);

    @Query("select p from Article p")
    Page<Article> findArticle(Pageable pageable);

    @Query("select p from Article p where p.scategory.id =:scat")
    Page<Article> findArticleByScategoryPageables(@Param("scat") Long scatId, Pageable pageable);

    @Query("select art from Article art where art.price like :price GROUP BY (art.price, art.id) ")
    Page<Article> findArticlePageableGroupByPrice(@Param("price") double price, Pageable pageable);

}
