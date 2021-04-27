package com.casaSolaire.models;

import com.casaSolaire.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;


@Entity
@Table(name = "commande")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Commande implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "number", length = 90)
    private String number;

    @Column(name = "total", length = 150)
    private double total;

    @Column(name = "dateCommande", length = 90)
    private LocalDate dateCommande;

    @Enumerated
    private Status status;

    @ManyToOne
    @JoinColumn(name = "idClient")
    private Client client;
}
