package com.casaSolaire.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "customer")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Client implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "reference", length = 50)
    private String reference;

    @Column(name = "firstName", length = 150)
    private String firstName;

    @Column(name = "lastName", length = 90)
    private String lastName;

    @Column(name = "email", length = 30)
    private String email;

    @Column(name = "mobile", length = 30)
    private String mobile;

    @Column(name = "address", length = 100)
    private String address;

}
