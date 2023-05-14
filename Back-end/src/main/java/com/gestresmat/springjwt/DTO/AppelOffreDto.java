package com.gestresmat.springjwt.DTO;

import java.util.Date;
import java.util.List;

public class AppelOffreDto {
    private Long id;
    private Date dateDebut;
    private Date dateFin;

    private List<PropositionDto> propositions;

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

    public List<PropositionDto> getPropositions() {
        return propositions;
    }

    public void setPropositions(List<PropositionDto> propositions) {
        this.propositions = propositions;
    }
}
