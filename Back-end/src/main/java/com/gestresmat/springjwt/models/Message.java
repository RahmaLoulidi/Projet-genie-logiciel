package com.gestresmat.springjwt.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


import com.fasterxml.jackson.annotation.*;

import lombok.Data;


@Entity
public class Message implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String subject;
    private String content;
    private Integer seen;


    @ManyToMany
    @JoinTable(
            name = "MessageRecepteur",
            joinColumns = @JoinColumn(name = "message_id"),
            inverseJoinColumns = @JoinColumn(name = "recepteur_id")
    )
    @JsonIgnoreProperties("messages_recepteur")
    private List<User> recepteurs;


    @ManyToOne
    @JoinColumn(name = "emetteur_id")
    private User emetteur;

    public Message() {
    }

    public Message(String subject, String content, List<User> recepteurs, User emetteur ) {
        this.subject = subject;
        this.content = content;
        this.recepteurs = recepteurs;
        this.emetteur = emetteur;
    }

    public Message(String subject, String content ) {
        this.subject = subject;
        this.content = content;

    }

    public Message(String subject, String content, Integer seen) {
        this.subject = subject;
        this.content = content;
        this.seen = seen;
    }

    public String getEmetteurUsername(){
        return this.emetteur.getUsername();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getSeen() {
        return seen;
    }

    public void setSeen(Integer seen) {
        this.seen = seen;
    }

    public List<User> getRecepteurs() {
        return recepteurs;
    }

    public void setRecepteurs(List<User> recepteurs) {
        this.recepteurs = recepteurs;
    }

    public User getEmetteur() {
        return emetteur;
    }

    public void setEmetteur(User emetteur) {
        this.emetteur = emetteur;
    }
}
