package com.gestresmat.springjwt.models;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


import javax.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Entity
public class Enseignant extends User implements Serializable {
    private boolean chefDepartement;
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "departement_id")
    private Departement departement;

    @ManyToMany(mappedBy="enseignants")
    private List<Ressource> ressources = new ArrayList<>();
    @JsonIgnore
    @OneToMany(mappedBy = "enseignant")
    private List<Panne> pannes;

    @OneToMany(mappedBy = "enseignant")
    private List<Besoin> besoins;

    public Departement getDepartement() {
        return departement;
    }

    public void setDepartement(Departement departement) {
        this.departement = departement;
    }

    public boolean isChefDepartement() {
        return chefDepartement;
    }

    public void setChefDepartement(boolean chefDepartement) {
        this.chefDepartement = chefDepartement;
    }


    public Enseignant(String username, String email, String motDePasse) {
        super(username, email, motDePasse);
    }
    public Enseignant() {
        super();
    }

}



