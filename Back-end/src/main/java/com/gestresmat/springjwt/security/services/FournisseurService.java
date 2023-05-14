package com.gestresmat.springjwt.security.services;

import com.gestresmat.springjwt.models.Fournisseur;
import com.gestresmat.springjwt.repository.FournisseurRepo;
import org.springframework.stereotype.Service;

@Service
public class FournisseurService {
    private final FournisseurRepo fournisseurRepo;

    public FournisseurService(FournisseurRepo fournisseurRepo) {
        this.fournisseurRepo = fournisseurRepo;
    }

    public Fournisseur findFournisseurById(Long id) {
        return fournisseurRepo.findFournisseurById(id);
    }
}
