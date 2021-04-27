package com.casaSolaire.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CollectionId;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "reservation")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reservation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;

    private double total;

    private LocalDate localDate;

    @ManyToOne
    @JoinColumn(name = "idClient")
    private Client client;
}
