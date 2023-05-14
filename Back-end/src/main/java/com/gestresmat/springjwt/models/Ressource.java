package com.gestresmat.springjwt.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Entity
@Inheritance
public class Ressource implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false,updatable = false)
    private Long id;
    private String codeBarre;
    private String dureeGarantie;
    private Date dateLivraison;
    private String marque;

    @ManyToMany
    @JoinTable(
            name = "Affectation",
            joinColumns = @JoinColumn(name = "ressource_id"),
            inverseJoinColumns = @JoinColumn(name = "enseignant_id")
    )
    private List<Enseignant> enseignants;

    @OneToOne(mappedBy = "ressource")
    private Panne panne;

    @OneToOne(mappedBy = "ressource")
    private Constat constat;

    public Ressource(String codeBarre, String dureeGarantie, Date dateLivraison, String marque) {
        this.codeBarre = codeBarre;
        this.dureeGarantie = dureeGarantie;
        this.dateLivraison = dateLivraison;
        this.marque = marque;
    }

    public Ressource() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodeBarre() {
        return codeBarre;
    }

    public void setCodeBarre(String codeBarre) {
        this.codeBarre = codeBarre;
    }

    public String getDureeGarantie() {
        return dureeGarantie;
    }

    public void setDureeGarantie(String dureeGarantie) {
        this.dureeGarantie = dureeGarantie;
    }

    public Date getDateLivraison() {
        return dateLivraison;
    }

    public void setDateLivraison(Date dateLivraison) {
        this.dateLivraison = dateLivraison;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }
}