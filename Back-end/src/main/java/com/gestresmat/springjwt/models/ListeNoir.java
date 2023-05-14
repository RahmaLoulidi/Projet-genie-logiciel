package com.gestresmat.springjwt.models;
import javax.persistence.*;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
public class ListeNoir implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false,updatable = false)
    private Long id;

    @OneToMany(mappedBy = "listeNoir")
    private List<Fournisseur> fournisseurs;
    public ListeNoir() {
    }
}

