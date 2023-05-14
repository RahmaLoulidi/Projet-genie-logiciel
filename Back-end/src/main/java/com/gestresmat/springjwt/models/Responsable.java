package com.gestresmat.springjwt.models;


import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Responsable extends User implements Serializable {

    @OneToMany(mappedBy="responsable")
    private List<Notification> notifications = new ArrayList<>();
    public Responsable(String username, String email, String motDePasse) {
        super(username, email, motDePasse);
    }

    public Responsable() {
    }
}
