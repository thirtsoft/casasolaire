package com.casaSolaire.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "ligneCommande")
public class LigneCommande implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numero", length = 90)
    private Long numero;

    @Column(name = "quantity", length = 70)
    private int quantity;

    @Column(name = "price", length = 70)
    private double price;

    private Long productId;

    private String productName;

    @Column(name = "created_date")
    private Date createdDate;

    @ManyToOne
    @JoinColumn(name = "comId", referencedColumnName = "id")
    @JsonIgnore
    private Commande commande;

    @ManyToOne
    @JoinColumn(name = "prodId", referencedColumnName = "id")
    private Article article;

    public LigneCommande() {
    }

    public LigneCommande(Commande commande, Article article, int quantity, double price) {
        this.article = article;
        this.quantity = quantity;
        this.price = price;
        this.commande = commande;
        this.createdDate = new Date();

    }

    public LigneCommande(Long id, int quantity, double price, Commande commande, Article article) {
        this.id = id;
        this.numero = commande.getNumeroCommande();
        this.quantity = quantity;
        this.price = price;
        this.createdDate = new Date();
        this.commande = commande;
        this.article = article;
    }

    public LigneCommande(Long id, Long numero, int quantity, double price, Commande commande, Article article) {
        this.id = id;
        this.numero = numero;
        this.quantity = quantity;
        this.price = price;
        this.createdDate = new Date();
        this.commande = commande;
        this.article = article;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Commande getCommande() {
        return commande;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }
}
