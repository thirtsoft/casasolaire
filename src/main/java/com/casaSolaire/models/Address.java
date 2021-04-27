package com.casaSolaire.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "address")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code", length = 30)
    private String code;

    @Column(name = "city", length = 100)
    private String city;

    @Column(name = "rue", length = 100)
    private String rue;

    @Column(name = "town", length = 90)
    private String town;

    @Column(name = "country", length = 90)
    private String country;
}
