package com.gestresmat.springjwt.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Entity
public class AppelOffre implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false,updatable = false)
    private Long id;
    private Date dateDebut;
    private Date dateFin;

    @OneToMany(mappedBy = "appelOffre")
    private List<Besoin> besoins;


    ////////////////////////// AHLAM //////////////////////////
    @OneToMany(mappedBy = "appelOffre")
    private List<Proposition> propositions;
    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fournisseur_id")
    private Fournisseur fournisseur;



    public AppelOffre(Date dateDebut, Date dateFin) {
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }

    public void mettreAJourDates(Date dateDebut, Date dateFin) {
        this.setDateDebut(dateDebut);
        this.setDateFin(dateFin);
    }


    public AppelOffre() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public List<Long> getBesoins() {
        List<Long> idsBesoins = new ArrayList<>();
        for(Besoin besoin : besoins) {
            idsBesoins.add(besoin.getId());
        }
        return idsBesoins;
    }
    public void setBesoins(List<Besoin> besoins) {
        this.besoins = besoins;
    }

    public AppelOffre(Date dateDebut, Date dateFin, List<Besoin> besoins) {
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.besoins = besoins;
    }


    public List<Proposition> getPropositions() {
        return propositions;
    }

    public void setPropositions(List<Proposition> propositions) {
        this.propositions = propositions;
    }

    public Fournisseur getFournisseur() {
        return fournisseur;
    }

    public void setFournisseur(Fournisseur fournisseur) {
        this.fournisseur = fournisseur;
    }
}