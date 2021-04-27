package com.casaSolaire.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "notification")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Note implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "reference", length = 50)
    private String reference;

    @Column(name = "designation", length = 50)
    private String nombreEtoile;

    @Column(name = "observation", length = 200)
    private String observation;

    @ManyToOne
    @JoinColumn(name = "idArticle")
    private Article article;

    @ManyToOne
    @JoinColumn(name = "idUtilisateur")
    private Utilisateur utilisateur;
}
