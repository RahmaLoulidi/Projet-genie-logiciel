package com.gestresmat.springjwt.models;

import javax.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
public class Departement implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false,updatable = false)
    private Long id;
    private String nomDepartement;
    private int nbrEnseignant;
    @OneToMany(mappedBy = "departement" , cascade = {CascadeType.ALL})
    private List<Enseignant> enseignants;

    public Departement(String nomDepartement, int nbrEnseignant) {
        this.nomDepartement = nomDepartement;
        this.nbrEnseignant = nbrEnseignant;
    }

    public Departement() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomDepartement() {
        return nomDepartement;
    }

    public void setNomDepartement(String nomDepartement) {
        this.nomDepartement = nomDepartement;
    }

    public int getNbrEnseignant() {
        return nbrEnseignant;
    }

    public void setNbrEnseignant(int nbrEnseignant) {
        this.nbrEnseignant = nbrEnseignant;
    }
}
