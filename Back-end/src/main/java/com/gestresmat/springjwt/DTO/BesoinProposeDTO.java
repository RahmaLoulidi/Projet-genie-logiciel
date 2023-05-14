package com.gestresmat.springjwt.DTO;

import java.math.BigDecimal;

public class BesoinProposeDTO {
    private Long id;
    private BigDecimal prix;
    private String marque;
    private Long besoinId;

    public BesoinProposeDTO() {
    }

    public BesoinProposeDTO(Long besoinId, String marque, BigDecimal prix) {
        this.besoinId = besoinId;
        this.marque = marque;
        this.prix = prix;
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getPrix() {
        return prix;
    }

    public String getMarque() {
        return marque;
    }

    public Long getBesoinId() {
        return besoinId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPrix(Double prix) {
        this.prix = BigDecimal.valueOf(prix);
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public void setBesoinId(Long besoinId) {
        this.besoinId = besoinId;
    }
}
