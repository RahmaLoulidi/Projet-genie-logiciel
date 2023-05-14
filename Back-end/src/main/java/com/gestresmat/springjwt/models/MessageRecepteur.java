package com.gestresmat.springjwt.models;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
@Data
@Entity
public class MessageRecepteur implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false,updatable = false)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "message_id")
    private Message message;

    @ManyToOne
    @JoinColumn(name = "recepteur_id")
    private User recepteur;

    public MessageRecepteur(Message message, User recepteur) {
        this.message = message;
        this.recepteur = recepteur;
    }

    public MessageRecepteur() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public User getRecepteur() {
        return recepteur;
    }

    public void setRecepteur(User recepteur) {
        this.recepteur = recepteur;
    }
}
