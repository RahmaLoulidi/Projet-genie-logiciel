package com.gestresmat.springjwt.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Date;
@Data
@Transactional
@Entity
public class Panne implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false,updatable = false)
    private Long id;
    private String explication;
    private Date datePanne;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "enseignant_id")
    private Enseignant enseignant;
    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ressource_id")
    private Ressource ressource;

    @JsonIgnore
    @OneToOne(mappedBy = "panne",cascade = CascadeType.REMOVE)
    private Constat constat;

    public Panne(String explication, Date datePanne) {
        this.explication = explication;
        this.datePanne = datePanne;
    }

    public Panne() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getExplication() {
        return explication;
    }

    public void setExplication(String explication) {
        this.explication = explication;
    }

    public Date getDatePanne() {
        return datePanne;
    }

    public void setDatePanne(Date datePanne) {
        this.datePanne = datePanne;
    }




    public void setConstat(Constat constat) {
        this.constat = constat;
    }
}

