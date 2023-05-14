package com.gestresmat.springjwt.security.services;

import com.gestresmat.springjwt.models.Besoin;
import com.gestresmat.springjwt.models.Enseignant;
import com.gestresmat.springjwt.models.Fournisseur;
import com.gestresmat.springjwt.models.ListeNoir;
import com.gestresmat.springjwt.repository.ListeNoirRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;

@Service
public class ListeNoirService {
    private final ListeNoirRepository listeNoirRepository;

    public ListeNoirService(ListeNoirRepository listeNoirRepository) {
        this.listeNoirRepository = listeNoirRepository;
    }





}
