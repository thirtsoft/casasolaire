package com.casaSolaire.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "article", uniqueConstraints = {
        @UniqueConstraint(columnNames = "reference"),
})
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Article implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "reference", length = 100)
    private String reference;

    @Column(name = "designation", length = 100)
    private String designation;

    @Column(name = "quantity", length = 50)
    private int quantity;

    @Transient
    private int quantite = 1;

    @Column(name = "price", length = 50)
    private double price;

    @Column(name = "currentPrice", length = 50)
    private double currentPrice;

    private boolean promo;

    private boolean selected;

    @CreationTimestamp
    private Date createDate;

    @UpdateTimestamp
    private Date lastUpDated;

    @Column(name = "description")
    @Lob
    private String description;

    @Column(name = "manufactured")
    @Lob
    private String manufactured;

    @Column(name = "photo")
    private String photo;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "scatId")
    private Scategory scategory;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "article")
    private List<Notification> notificationList;

    public Article(Long id, String reference, String designation, int quantity,
                   double price, double currentPrice, boolean promo, boolean selected,
                   String description, String photo, Scategory scategory) {
        this.id = id;
        this.reference = reference;
        this.designation = designation;
        this.quantity = quantity;
        this.price = price;
        this.currentPrice = currentPrice;
        this.promo = promo;
        this.selected = selected;
        this.description = description;
        this.photo = photo;
        this.scategory = scategory;
    }

    public Article(String designation, String photo, double price, String description, Scategory scategory) {
        super();
        this.designation = designation;
        this.photo = photo;
        this.price = price;
        this.description = description;
        this.scategory = scategory;
    }

}
