package com.gestresmat.springjwt.models;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Date;

@Entity
public class Imprimante extends Ressource implements Serializable {
    private String resolution;
    private String vitesse;


    public Imprimante(String codeBarre, String dureeGarantie, Date dateLivraison, String marque, String resolution, String vitesse) {
        super(codeBarre, dureeGarantie, dateLivraison, marque);
        this.resolution = resolution;
        this.vitesse = vitesse;
    }

    public Imprimante() {
        super();
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
}