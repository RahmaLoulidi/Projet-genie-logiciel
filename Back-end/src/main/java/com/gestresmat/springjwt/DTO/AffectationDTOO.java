package com.gestresmat.springjwt.DTO;

import java.util.Date;

public class AffectationDTOO {
    private Long id;
    private Date dateAffectation;
    private String enseignantEmail;
    private String ressourceCodeBarre;

    public AffectationDTOO() {
    }

    public AffectationDTOO(Long id, Date dateAffectation, String enseignantEmail, String ressourceCodeBarre) {
        this.id = id;
        this.dateAffectation = dateAffectation;
        this.enseignantEmail = enseignantEmail;
        this.ressourceCodeBarre = ressourceCodeBarre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateAffectation() {
        return dateAffectation;
    }

    public void setDateAffectation(Date dateAffectation) {
        this.dateAffectation = dateAffectation;
    }

    public String getEnseignantEmail() {
        return enseignantEmail;
    }

    public void setEnseignantEmail(String enseignantEmail) {
        this.enseignantEmail = enseignantEmail;
    }

    public String getRessourceCodeBarre() {
        return ressourceCodeBarre;
    }

    public void setRessourceCodeBarre(String ressourceCodeBarre) {
        this.ressourceCodeBarre = ressourceCodeBarre;
    }
}

