package com.gestresmat.springjwt.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import javax.transaction.Transactional;

import java.io.Serializable;
import java.util.Date;
import java.util.Optional;

@Data
@Transactional
@Entity
public class Constat implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false,updatable = false)
    private Long id;
    private Date dateApparition;
    private String explication;
    private String frequence;
    private String ordre;

    private Integer send;
    private Integer reparation;

    private Integer renvoie;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "panne_id")
    private Panne panne;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ressource_id")
    private Ressource ressource;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "technicienMaintenance_id")
    private TechnicienMaintenance technicienMaintenance;

    public Constat(Date dateApparition, String explication, String frequence, String ordre) {
        this.dateApparition = dateApparition;
        this.explication = explication;
        this.frequence = frequence;
        this.ordre = ordre;
    }

    public Constat() {
    }


    public Constat(Date dateApparition, String explication, String frequence, String ordre, Panne panne) {
        this.dateApparition = dateApparition;
        this.explication = explication;
        this.frequence = frequence;
        this.ordre = ordre;
        this.panne = panne;
    }

    public Constat(Date dateApparition, String explication, String frequence, String ordre, TechnicienMaintenance technicienMaintenance) {
        this.dateApparition = dateApparition;
        this.explication = explication;
        this.frequence = frequence;
        this.ordre = ordre;
        this.technicienMaintenance = technicienMaintenance;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateApparition() {
        return dateApparition;
    }

    public void setDateApparition(Date dateApparition) {
        this.dateApparition = dateApparition;
    }

    public String getExplication() {
        return explication;
    }

    public void setExplication(String explication) {
        this.explication = explication;
    }

    public String getFrequence() {
        return frequence;
    }

    public void setFrequence(String frequence) {
        this.frequence = frequence;
    }




    public void setPanne(Panne panne) {
        this.panne = panne;
    }


    public Panne getPanne() {
        return panne;
    }

    public void setTechnicienMaintenance(TechnicienMaintenance technicienMaintenance) {
        this.technicienMaintenance=technicienMaintenance;
    }

    public String getOrdre() {
        return ordre;
    }

    public void setOrdre(String ordre) {
        this.ordre = ordre;
    }

    public Ressource getRessource() {
        return ressource;
    }

    public void setRessource(Ressource ressource) {
        this.ressource = ressource;
    }

    public TechnicienMaintenance getTechnicienMaintenance() {
        return technicienMaintenance;
    }

    public Integer getSend() {
        return send;
    }

    public void setSend(Integer send) {
        this.send = send;
    }

    public Integer getReparation() {
        return reparation;
    }

    public void setReparation(Integer reparation) {
        this.reparation = reparation;
    }

    public Integer getRenvoie() {
        return renvoie;
    }

    public void setRenvoie(Integer renvoie) {
        this.renvoie = renvoie;
    }
}
