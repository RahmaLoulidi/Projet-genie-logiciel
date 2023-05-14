package com.gestresmat.springjwt.security.services;

import com.gestresmat.springjwt.models.*;
import com.gestresmat.springjwt.repository.BesoinRepository;
import com.gestresmat.springjwt.repository.EnseignantRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class BesoinService {
    private final BesoinRepository besoinRepository;
    private final EnseignantRepository enseignantRepository;

    private final MessageService messageService;

    public BesoinService(BesoinRepository besoinRepository, EnseignantRepository enseignantRepository, MessageService messageService) {
        this.besoinRepository = besoinRepository;
        this.enseignantRepository = enseignantRepository;

        this.messageService = messageService;
    }

    public Besoin addBesoin(Besoin besoin, Long id){
        Enseignant e=enseignantRepository.findEnseignantById(id);
        besoin.setEnseignant(e);
        besoin.setLivrable(true);
        return besoinRepository.save(besoin);
    }
    public List<Besoin> findAllBesoin(){
        return besoinRepository.findAll();
    }
    public Optional<Besoin> findByIdBesoin(Long id){
        return besoinRepository.findById(id);
    }

    public List<Besoin> findByTypeAndLivrableIsTrue(String type){
        return besoinRepository.findByTypeAndLivrableIsTrue(type);

    }

    ///////////////////////////////////// AHLAM /////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////

    public List<Besoin> findByTypeAndAppelOffreIsNull(String type) {
        return  besoinRepository.findByTypeAndAppelOffreIsNull(type);
    }

    public List<Besoin> getBesoinsByAppelOffreIdAndType(Long appelOffreId, String type) {
        return  besoinRepository.findByAppelOffreIdAndType(appelOffreId, type);
    }
    ////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////Rahma///////////////////////////////////////////////////////


    public List<Besoin> findByType(String type){
        return besoinRepository.findAllByTypeOrderByIdDesc(type);
    }

    public Besoin updateOrdi(Besoin besoin){
        return besoinRepository.save(besoin);
    }

    public Besoin updateImp(Besoin besoin){
        return besoinRepository.save(besoin);
    }

    public Besoin envoieBesoin(Besoin besoin){

        besoin.setEnvoie(1);

        return besoinRepository.save(besoin);
    }

    public List<Besoin> findAllBesoinEnvoie(String type){
        return besoinRepository.findBesoinEnvoie(type);
    }

    public List<Besoin> findAllBesoinDep(Long idEns,String type){
        Enseignant enseignant= enseignantRepository.findEnseignantById(idEns);
        Departement dep = enseignant.getDepartement();
        List<Besoin> besoins= besoinRepository.findBesoinByDepAndType(dep,type);

        return besoins;
    }



}
