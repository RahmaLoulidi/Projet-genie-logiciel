package com.gestresmat.springjwt.models;


import com.fasterxml.jackson.annotation.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Entity
public class Proposition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false,updatable = false)
    private Long id;
    private BigDecimal prixTotal;

    @JsonIdentityReference(alwaysAsId = true)
    @ManyToOne
    @JoinColumn(name = "appel_offre_id")
    private AppelOffre appelOffre;


    @JsonIdentityReference(alwaysAsId = true)
    @ManyToOne
    @JoinColumn(name = "fournisseur_id")
    private Fournisseur fournisseur;

    ///////////////////////////////////SALMAAYA////////////////////////////////////
    @ColumnDefault("'EN_ATTENTE'")
    @Enumerated(EnumType.STRING)
    private EtatProposition etat;


    @JsonIdentityReference(alwaysAsId = true)
    @OneToMany(mappedBy = "proposition", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BesoinPropose> besoinsProposes = new ArrayList<>();

    ////////////////////////////////////////////////////////////////////////


    public Proposition() {
    }

    public Proposition(BigDecimal prixTotal) {
        this.prixTotal = prixTotal;
    }

    public Proposition(BigDecimal prixTotal, AppelOffre appelOffre, Fournisseur fournisseur) {
        this.prixTotal = prixTotal;
        this.appelOffre = appelOffre;
        this.fournisseur = fournisseur;
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getPrixTotal() {
        return prixTotal;
    }

    public AppelOffre getAppelOffre() {
        return appelOffre;
    }

    public Fournisseur getFournisseur() {
        return fournisseur;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPrixTotal(BigDecimal prixTotal) {
        this.prixTotal = prixTotal;
    }

    public void setAppelOffre(AppelOffre appelOffre) {
        this.appelOffre = appelOffre;
    }

    public void setFournisseur(Fournisseur fournisseur) {
        this.fournisseur = fournisseur;
    }

    public EtatProposition getEtat() {
        return etat;
    }

    public List<BesoinPropose> getBesoinsProposes() {
        return besoinsProposes;
    }

    public void setEtat(EtatProposition etat) {
        this.etat = etat;
    }

    public void setBesoinsProposes(List<BesoinPropose> besoinsProposes) {
        this.besoinsProposes = besoinsProposes;
    }
}


