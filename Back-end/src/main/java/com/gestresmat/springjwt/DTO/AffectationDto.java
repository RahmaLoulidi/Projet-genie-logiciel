package com.gestresmat.springjwt.DTO;

import java.util.Date;

public class AffectationDto {
    private Long id;
    private Date dateAffectation;
    private Long enseignantId;
    private Long ressourceId;

    public AffectationDto(Date dateAffectation, Long enseignantId, Long ressourceId) {
        this.dateAffectation = dateAffectation;
        this.enseignantId = enseignantId;
        this.ressourceId = ressourceId;
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

    public Long getEnseignantId() {
        return enseignantId;
    }

    public void setEnseignantId(Long enseignantId) {
        this.enseignantId = enseignantId;
    }

    public Long getRessourceId() {
        return ressourceId;
    }

    public void setRessourceId(Long ressourceId) {
        this.ressourceId = ressourceId;
    }



}
