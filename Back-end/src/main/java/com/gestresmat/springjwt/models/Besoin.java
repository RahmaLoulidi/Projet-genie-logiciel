package com.gestresmat.springjwt.models;



import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

import java.io.Serializable;
import java.util.List;

@Data
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Besoin implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false,updatable = false)
    private Long id;
    private String marque;
    private String cpu;
    private String disqueDur;
    private String ecran;
    private String ram;
    private String resolution;
    private String vitesse;
    private String type;
    private boolean livrable;

    private Integer envoie;

    //    @OneToMany(mappedBy = "besoin")
//    private List<AppelOffre> appelOffres;


    @ManyToOne
    @JoinColumn(name = "appel_offre_id")
    private AppelOffre appelOffre;

    @ManyToOne
    @JoinColumn(name = "enseignant_id")
    private Enseignant enseignant;


    public Besoin() {
    }

    public Besoin(String marque, String cpu, String disqueDur, String ecran, String ram, String resolution, String vitesse, String type, boolean livrable, AppelOffre appelOffre, Enseignant enseignant) {
        this.marque = marque;
        this.cpu = cpu;
        this.disqueDur = disqueDur;
        this.ecran = ecran;
        this.ram = ram;
        this.resolution = resolution;
        this.vitesse = vitesse;
        this.type = type;
        this.livrable = livrable;
        this.appelOffre = appelOffre;
        this.enseignant = enseignant;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getDisqueDur() {
        return disqueDur;
    }

    public void setDisqueDur(String disqueDur) {
        this.disqueDur = disqueDur;
    }

    public String getEcran() {
        return ecran;
    }

    public void setEcran(String ecran) {
        this.ecran = ecran;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public String getVitesse() {
        return vitesse;
    }

    public void setVitesse(String vitesse) {
        this.vitesse = vitesse;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isLivrable() {
        return livrable;
    }

    public void setLivrable(boolean livrable) {
        this.livrable = livrable;
    }

    public AppelOffre getAppelOffre() {
        return appelOffre;
    }

    public void setAppelOffre(AppelOffre appelOffre) {
        this.appelOffre = appelOffre;
    }

    public Enseignant getEnseignant() {
        return enseignant;
    }

    public void setEnseignant(Enseignant enseignant) {
        this.enseignant = enseignant;
    }



    public Integer getEnvoie() {
        return envoie;
    }

    public void setEnvoie(Integer envoie) {
        this.envoie = envoie;
    }
}