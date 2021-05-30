package com.casaSolaire;

import com.casaSolaire.models.*;
import com.casaSolaire.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CasaSolaireApplication implements CommandLineRunner {

    private static final Logger LOG = LoggerFactory.getLogger(CasaSolaireApplication.class);

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ScategoryRepository scategoryRepository;
    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private FournisseurRepository fournisseurRepository;
/*
    @Autowired
    public CasaSolaireApplication(CategoryRepository categoryRepository,
                                  ScategoryRepository scategoryRepository,
                                  ArticleRepository articleRepository,
                                  ClientRepository clientRepository,
                                  FournisseurRepository fournisseurRepository) {
        this.categoryRepository = categoryRepository;
        this.scategoryRepository = scategoryRepository;
        this.articleRepository = articleRepository;
        this.clientRepository = clientRepository;
        this.fournisseurRepository = fournisseurRepository;
    }
*/

    public static void main(String[] args) {
        SpringApplication.run(CasaSolaireApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {


        Category c1 = new Category(1L, "cat1", "cat1");
        Category c2 = new Category(2L, "cat2", "cat2");
        Category c3 = new Category(3L, "cat3", "cat3");
        categoryRepository.save(c1);
        categoryRepository.save(c2);
        categoryRepository.save(c3);

        Scategory sc1 = new Scategory(1L, "scat1", "scat1", c1);
        Scategory sc2 = new Scategory(2L, "scat2", "scat2", c2);
        Scategory sc3 = new Scategory(3L, "scat3", "scat3", c3);
        Scategory sc4 = new Scategory(4L, "scat4", "scat4", c1);

        scategoryRepository.save(sc1);
        scategoryRepository.save(sc2);
        scategoryRepository.save(sc3);
        scategoryRepository.save(sc4);

        Article p1 = new Article(1L, "prod1", "prod1", 150, 1700.0, 1800, true, "prod1", "photo1.jpg", sc1);
        Article p2 = new Article(2L, "prod2", "prod2", 150, 1700.0, 1800, true, "prod1", "photo2.jpg", sc1);
        Article p3 = new Article(3L, "prod3", "prod3", 150, 1700.0, 1800, false, "prod1", "photo3.jpg", sc1);
        Article p4 = new Article(4L, "prod4", "prod4", 150, 1700.0, 1800, false, "prod1", "photo4.jpg", sc1);
        Article p5 = new Article(5L, "prod5", "prod5", 150, 1700.0, 1800, true, "prod1", "photo5.jpg", sc1);
        Article p6 = new Article(6L, "prod6", "prod6", 150, 1700.0, 1800, true, "prod1", "photo5.jpg", sc1);
        Article p7 = new Article(7L, "prod7", "prod7", 150, 1700.0, 1800, true, "prod1", "photo7.jpg", sc1);
        Article p8 = new Article(8L, "prod8", "prod8", 150, 1700.0, 1800, false, "prod1", "photo8.jpg", sc1);
        Article p9 = new Article(9L, "prod9", "prod9", 150, 1700.0, 1800, false, "prod1", "photo9.jpg", sc1);
        Article p10 = new Article(10L, "prod10", "prod10", 150, 1700.0, 1800, true, "prod1", "photo10.jpg", sc1);

        articleRepository.save(p1);
        articleRepository.save(p2);
        articleRepository.save(p3);
        articleRepository.save(p4);
        articleRepository.save(p5);
        articleRepository.save(p6);
        articleRepository.save(p7);
        articleRepository.save(p8);
        articleRepository.save(p9);
        articleRepository.save(p10);

        Fournisseur f1 = new Fournisseur(1L, "f1", "f1", "f1", "f1", "f1", "f1", "f1", "f1", p1);
        Fournisseur f2 = new Fournisseur(2L, "f2", "f2", "f2", "f2", "f2", "f2", "f2", "f2", p2);
        Fournisseur f3 = new Fournisseur(3L, "f3", "f3", "f3", "f3", "f3", "f3", "f3", "f3", p2);
        Fournisseur f4 = new Fournisseur(4L, "f4", "f4", "f4", "f4", "f4", "f4", "f4", "f4", p1);
        fournisseurRepository.save(f1);
        fournisseurRepository.save(f2);
        fournisseurRepository.save(f3);
        fournisseurRepository.save(f4);

        Client cl1 = new Client(1L, "cl1", "cl1", "cl1", "cl1", "cl1", "cl1");
        Client cl2 = new Client(2L, "cl2", "cl2", "cl2", "cl2", "cl2", "cl2");
        Client cl3 = new Client(3L, "cl3", "cl3", "cl3", "cl3", "cl3", "cl3");
        Client cl4 = new Client(4L, "cl4", "cl4", "cl4", "cl4", "cl4", "cl4");

        clientRepository.save(cl1);
        clientRepository.save(cl2);
        clientRepository.save(cl3);
        clientRepository.save(cl4);


    }
}
