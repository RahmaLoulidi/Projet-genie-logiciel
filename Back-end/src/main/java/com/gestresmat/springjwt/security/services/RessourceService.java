package com.gestresmat.springjwt.security.services;

import com.gestresmat.springjwt.models.Ressource;
import com.gestresmat.springjwt.repository.ImprimanteRepository;
import com.gestresmat.springjwt.repository.OrdinateurRepository;
import com.gestresmat.springjwt.repository.RessourceRepo;
import org.springframework.stereotype.Service;

@Service
public class RessourceService {

    final RessourceRepo ressourceRepo;
    final OrdinateurRepository ordinateurRepository;
    final ImprimanteRepository imprimanteRepository;

    public RessourceService(RessourceRepo ressourceRepo, OrdinateurRepository ordinateurRepository, ImprimanteRepository imprimanteRepository) {
        this.ressourceRepo = ressourceRepo;
        this.ordinateurRepository = ordinateurRepository;
        this.imprimanteRepository = imprimanteRepository;
    }

    public boolean isPrinter(Long id){
        if(imprimanteRepository.findImprimanteById(id)==null){
            return false;
        }
        return true;
    }

    public Ressource getRessourceById(Long id){
        Ressource r = ressourceRepo.findRessourceById(id);
        return r;
    }
}
