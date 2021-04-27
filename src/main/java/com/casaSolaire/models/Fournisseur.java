package com.casaSolaire.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "fournisseur")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Fournisseur implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "reference", length = 90)
    private String reference;

    @Column(name = "firstName", length = 150)
    private String firstName;

    @Column(name = "lastName", length = 100)
    private String lastName;

    @Column(name = "email", length = 30)
    private String email;

    @Column(name = "telephone", length = 30)
    private String telephone;

    @Column(name = "city", length = 90)
    private String city;

    @Column(name = "town", length = 90)
    private String town;

    @Column(name = "rue", length = 150)
    private String rue;

    @ManyToOne
    @JoinColumn(name = "idArticle")
    private Article article;
}
