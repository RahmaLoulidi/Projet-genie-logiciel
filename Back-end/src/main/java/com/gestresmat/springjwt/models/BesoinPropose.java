package com.gestresmat.springjwt.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Entity
public class BesoinPropose {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double prix;

    private String marque;

    @JsonIdentityReference(alwaysAsId = true)
    @ManyToOne
    @JoinColumn(name = "besoin_id")
    private Besoin besoin;

    @JsonIdentityReference(alwaysAsId = true)
    @ManyToOne
    @JoinColumn(name = "proposition_id")
    private Proposition proposition;

    public BesoinPropose(Double prix, String marque, Besoin besoin) {
        this.prix = prix;
        this.marque = marque;
        this.besoin = besoin;
    }

    public BesoinPropose(Besoin besoin, Proposition proposition, Double prix, String marque) {
        this.besoin = besoin;
        this.proposition = proposition;
        this.prix = prix;
        this.marque = marque;
    }

    public BesoinPropose(Besoin besoin, Proposition proposition) {
        this.besoin = besoin;
        this.proposition = proposition;
    }

    public BesoinPropose() {
    }

    public Long getId() {
        return id;
    }

    public Besoin getBesoin() {
        return besoin;
    }

    public Proposition getProposition() {
        return proposition;
    }

    public Double getPrix() {
        return prix;
    }

    public String getMarque() {
        return marque;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setBesoin(Besoin besoin) {
        this.besoin = besoin;
    }

    public void setProposition(Proposition proposition) {
        this.proposition = proposition;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }
}
