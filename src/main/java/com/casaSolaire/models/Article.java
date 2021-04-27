package com.casaSolaire.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "article")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Article implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "reference", length = 90)
    private String reference;

    @Column(name = "designation", length = 150)
    private String designation;

    @Column(name = "price", length = 90)
    private double price;

    @Column(name = "currentPrice", length = 90)
    private double currentPrice;

    @Column(name = "quantity", length = 90)
    private int quantity;

    @Column(name = "promo")
    private boolean promo;

    @Column(name = "description")
    @Lob
    private String description;

    @Column(name = "photo", length = 100)
    private String photo;

    @ManyToOne
    @JoinColumn(name = "idCategory")
    private Category category;
}
