package com.gestresmat.springjwt.security.services;


import com.gestresmat.springjwt.exceptions.PanneNotFoundException;
import com.gestresmat.springjwt.models.Enseignant;
import com.gestresmat.springjwt.models.Panne;
import com.gestresmat.springjwt.models.Ressource;
import com.gestresmat.springjwt.repository.EnseignantRepository;
import com.gestresmat.springjwt.repository.PanneRepo;
import com.gestresmat.springjwt.repository.RessourceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class PanneService {
    private final PanneRepo panneRepo;
    private final EnseignantRepository enseignantRepository;

    private final RessourceRepo ressourceRepo;

    @Autowired
    public PanneService(PanneRepo panneRepo, EnseignantRepository enseignantRepository, RessourceRepo ressourceRepo) {
        this.panneRepo = panneRepo;
        this.enseignantRepository = enseignantRepository;
        this.ressourceRepo = ressourceRepo;
    }

    public Panne addPanne(Panne panne){
        return panneRepo.save(panne);
    }

    public void savePanne(Panne panne, Long idEns){
        Enseignant enseignant= enseignantRepository.findEnseignantById(idEns);
        panne.setEnseignant(enseignant);
        panneRepo.save(panne);
    }

    public void savePanneRes(Panne panne, Long idEns,Long idRes){
        Enseignant enseignant= enseignantRepository.findEnseignantById(idEns);
        Ressource  ressource= ressourceRepo.findRessourceById(idRes);
        panne.setEnseignant(enseignant);
        panne.setRessource(ressource);
        panneRepo.save(panne);
    }

    public List<Panne> findAllPanne(){
        return panneRepo.findAllTech();
    }

    public List<Panne> findAllPanneEnseignant( Long idEns){
        Enseignant enseignant= enseignantRepository.findEnseignantById(idEns);
        List<Panne> pannes= panneRepo.findPannesByEnseignant(enseignant);

        return pannes;
    }
    public Panne findPanneById(Long id){
        return panneRepo.findPanneById(id).orElseThrow(()->new PanneNotFoundException("Panne not found"));
    }

    public Panne updatePanne(Panne panne){


        return panneRepo.save(panne);
    }

    @Transactional
    public void deletePanne(Long id) {
        panneRepo.deletePanneById(id);
    }
}
