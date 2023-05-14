package com.gestresmat.springjwt.models;
import javax.persistence.*;

import java.io.Serializable;

@Entity
public class Notification implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false,updatable = false)
    private Long id;

    @ManyToOne
    private Fournisseur fournisseur;

    @ManyToOne
    private Responsable responsable;
    private String motif;
    private String type;


    public Notification(String motif, String type) {
        this.motif = motif;
        this.type = type;
    }

    public Notification() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
