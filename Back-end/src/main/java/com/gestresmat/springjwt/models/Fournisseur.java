package com.gestresmat.springjwt.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Entity
public class Fournisseur extends User implements Serializable {
    private String nomSociete;
    private String gerant;
    private String lieu;

    @OneToMany(mappedBy="fournisseur")
    private List<Notification> notifications = new ArrayList<>();

    @JsonIdentityReference(alwaysAsId = true)
    @ManyToOne
    @JoinColumn(name = "listeNoir_id")
    private ListeNoir listeNoir;

    /////////////////////////// AHLAM ////////////////////
    @OneToMany(mappedBy = "fournisseur")
    private List<Proposition> propositions;




    public Fournisseur(String username, String motDePasse, String nomSociete) {
        super(nomSociete, motDePasse);
        this.nomSociete = nomSociete;
    }



    public Fournisseur(String username, String email, String motDePasse, String nomSociete, String gerant, String lieu) {
        super(username, motDePasse);
        this.nomSociete = nomSociete;
        this.gerant = gerant;
        this.lieu = lieu;
    }

    public Fournisseur() {
    }

    public String getNomSociete() {
        return nomSociete;
    }

    public void setNomSociete(String nomSociete) {
        this.nomSociete = nomSociete;
    }

    public String getGerant() {
        return gerant;
    }

    public void setGerant(String gerant) {
        this.gerant = gerant;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public List<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
    }

    public ListeNoir getListeNoir() {
        return listeNoir;
    }

    public void setListeNoir(ListeNoir listeNoir) {
        this.listeNoir = listeNoir;
    }

    public List<Proposition> getPropositions() {
        return propositions;
    }

    public void setPropositions(List<Proposition> propositions) {
        this.propositions = propositions;
    }
}