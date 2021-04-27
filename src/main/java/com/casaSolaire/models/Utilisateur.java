package com.casaSolaire.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "utilisateur")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Utilisateur implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 150)
    private String name;

    @Column(name = "username", length = 100)
    private String username;

    @Column(name = "mobile", length = 30)
    private String mobile;

    @Column(name = "password", length = 150)
    private String password;

    @Column(name = "address", length = 150)
    private String address;

    @Column(name = "isActive")
    private boolean isActive;

    @ManyToOne
    @JoinColumn(name = "idRole")
    private Role role;
}
