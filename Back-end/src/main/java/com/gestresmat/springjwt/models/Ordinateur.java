package com.gestresmat.springjwt.models;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Date;

@Entity
public class Ordinateur extends Ressource implements Serializable {
    private String cpu;
    private String disqueDur;
    private String ecran;
    private String ram;

    public Ordinateur(String codeBarre, String dureeGarantie, Date dateLivraison,String marque, String cpu, String disqueDur, String ecran, String ram) {
        super(codeBarre, dureeGarantie, dateLivraison,marque);
        this.cpu = cpu;
        this.disqueDur = disqueDur;
        this.ecran = ecran;
        this.ram = ram;

    }

    public Ordinateur() {
        super();
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String  cpu) {
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


}