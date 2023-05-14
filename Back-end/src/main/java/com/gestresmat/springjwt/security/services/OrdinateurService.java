package com.gestresmat.springjwt.security.services;

import com.gestresmat.springjwt.exceptions.RessourceIntrouvableException;
import com.gestresmat.springjwt.models.Enseignant;
import com.gestresmat.springjwt.models.Imprimante;
import com.gestresmat.springjwt.models.Ordinateur;
import com.gestresmat.springjwt.models.Ressource;
import com.gestresmat.springjwt.repository.EnseignantRepository;
import com.gestresmat.springjwt.repository.OrdinateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrdinateurService {
    final OrdinateurRepository ordinateurRepository;

    final EnseignantRepository enseignantRepository;


    @Autowired
    public OrdinateurService(OrdinateurRepository ordinateurRepository, EnseignantRepository enseignantRepository) {
        this.ordinateurRepository = ordinateurRepository;
        this.enseignantRepository = enseignantRepository;
    }

    public Ordinateur addOrdinateur(Ordinateur ordinateur){
        return ordinateurRepository.save(ordinateur);
    }

    public List<Ordinateur> findAllOrdinateur(){
        return ordinateurRepository.findAll();

    }

//    public Ordinateur updateOrdinateur(Ordinateur ordinateur){
//        return ordinateurRepository.save(ordinateur);
//    }

    public Ordinateur updateOrdinateur(Long id, String dureeGarantie, Date dateLivraison) throws RessourceIntrouvableException {
        Ordinateur ordinateur = ordinateurRepository.findById(id)
                .orElseThrow(() -> new RessourceIntrouvableException("Ordinateur introuvable avec l'id: " + id));
        ordinateur.setDureeGarantie(dureeGarantie);
        ordinateur.setDateLivraison(dateLivraison);
        return ordinateurRepository.save(ordinateur);
    }

    public void deleteOrdinateur(Long id){
       ordinateurRepository.deleteById(id);
    }


    public List<Ordinateur> findOrdinateursByEns(Long id){
        Enseignant enseignant= enseignantRepository.findEnseignantById(id);
        List<Ordinateur> ordinateurs=ordinateurRepository.findOrdinateurByEns(id);
        return ordinateurs;

    }


    public Ordinateur getOrdinateurById(Long id){
        Ordinateur r = ordinateurRepository.findOrdinateurById(id);
        return r;
    }


}
