package com.casaSolaire.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "commande")
@Data
public class Commande implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numeroCommande", length = 90)
    private Long numeroCommande;

    @Column(name = "totalCommande", length = 150)
    private double totalCommande;

    @Column(name = "totalQuantity", length = 150)
    private int totalQuantity;

    @Column(name = "status")
    private String status;

    @Column(name = "session_id")
    private String sessionId;

    @Column(name = "orderTrackingNumber")
    private String orderTrackingNumber;

    @Column(name = "dateCommande", length = 90)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "GMT")
    private Date dateCommande;

    @ManyToOne
    @JoinColumn(name = "clientId")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private Utilisateur utilisateur;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "shipping_address_id", referencedColumnName = "id")
    private Address shippingAddress;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "billing_address_id", referencedColumnName = "id")
    private Address billingAddress;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "commande", fetch = FetchType.LAZY)
    private List<LigneCommande> lcomms = new ArrayList<>();

    public Commande() {
    }

    public void add(LigneCommande ligneCommande) {
        if (ligneCommande != null) {
            if (lcomms == null) {
                lcomms = new ArrayList<>();
            }
            lcomms.add(ligneCommande);
            ligneCommande.setCommande(this);
        }
    }

}
