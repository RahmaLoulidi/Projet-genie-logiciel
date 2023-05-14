package com.gestresmat.springjwt.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import java.io.Serializable;
import java.util.List;

@Data
@Entity
public class TechnicienMaintenance extends User implements Serializable {

    @JsonManagedReference
    @OneToMany(mappedBy = "technicienMaintenance")
    private List<Constat> constats;
    public TechnicienMaintenance(String username, String email, String motDePasse) {
        super(username, email, motDePasse);
    }

    public TechnicienMaintenance() {
    }
}
