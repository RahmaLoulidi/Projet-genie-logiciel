package com.gestresmat.springjwt.DTO;

import com.gestresmat.springjwt.models.EtatProposition;
import com.gestresmat.springjwt.models.Proposition;

import java.math.BigDecimal;
import java.util.Date;

public class PropositionDto {

    private Long id;
    private Date dateDebut;
    private Date dateFin;
    private String nomFournisseur;
    private BigDecimal prixTotal;

    private EtatProposition etat;

    public PropositionDto(Proposition proposition) {
        this.id = proposition.getId();
        this.dateDebut = proposition.getAppelOffre().getDateDebut();
        this.dateFin = proposition.getAppelOffre().getDateFin();
        this.nomFournisseur = proposition.getFournisseur().getNomSociete();
        this.prixTotal = proposition.getPrixTotal();
        this.etat = proposition.getEtat();
    }

    public PropositionDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public String getNomFournisseur() {
        return nomFournisseur;
    }

    public void setNomFournisseur(String nomFournisseur) {
        this.nomFournisseur = nomFournisseur;
    }

    public BigDecimal getPrixTotal() {
        return prixTotal;
    }

    public void setPrixTotal(BigDecimal prixTotal) {
        this.prixTotal = prixTotal;
    }

    public EtatProposition getEtat() {
        return etat;
    }

    public void setEtat(EtatProposition etat) {
        this.etat = etat;
    }
}