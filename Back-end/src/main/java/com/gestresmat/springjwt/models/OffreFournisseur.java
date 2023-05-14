package com.gestresmat.springjwt.models;
import javax.persistence.*;

import java.io.Serializable;

@Entity
public class OffreFournisseur implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false,updatable = false)
    private Long id;
    private Double prix;

    @ManyToOne
    @JoinColumn(name = "fournisseur_id")
    private Fournisseur fournisseur;

    @ManyToOne
    @JoinColumn(name = "appelOffre_id")
    private AppelOffre appelOffre;

    public OffreFournisseur(double prix) {
        this.prix = prix;
    }

    public OffreFournisseur() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }
}
