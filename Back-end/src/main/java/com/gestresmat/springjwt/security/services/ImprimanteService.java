package com.gestresmat.springjwt.security.services;

import com.gestresmat.springjwt.exceptions.RessourceIntrouvableException;
import com.gestresmat.springjwt.models.Enseignant;
import com.gestresmat.springjwt.models.Imprimante;
import com.gestresmat.springjwt.models.Ressource;
import com.gestresmat.springjwt.repository.EnseignantRepository;
import com.gestresmat.springjwt.repository.ImprimanteRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ImprimanteService {
    final ImprimanteRepository imprimanteRepository;
    final EnseignantRepository enseignantRepository;

    public ImprimanteService(ImprimanteRepository imprimanteRepository, EnseignantRepository enseignantRepository) {
        this.imprimanteRepository = imprimanteRepository;
        this.enseignantRepository = enseignantRepository;
    }


    public Imprimante addImprimante(Imprimante imprimante){
        return imprimanteRepository.save(imprimante);
    }

    public List<Imprimante> findAllImprimante(){
        return imprimanteRepository.findAll();

    }

    public Imprimante updateImprimante(Long id, String dureeGarantie, Date dateLivraison) throws RessourceIntrouvableException {
        Imprimante imprimante = imprimanteRepository.findById(id)
                .orElseThrow(() -> new RessourceIntrouvableException("Imprimante introuvable avec l'id: " + id));
        imprimante.setDureeGarantie(dureeGarantie);
        imprimante.setDateLivraison(dateLivraison);
        return imprimanteRepository.save(imprimante);
    }


    public void deleteImprimante(Long id){
        imprimanteRepository.deleteById(id);
    }


    public List<Imprimante> findImprimantesByEns(Long id){
        Enseignant enseignant= enseignantRepository.findEnseignantById(id);
        List<Imprimante> imprimantes=imprimanteRepository.findImprimantesByEns(id);
        return imprimantes;

    }

    public Imprimante getImprimanteById(Long id){
        Imprimante r = imprimanteRepository.findImprimanteById(id);
        return r;
    }

}
