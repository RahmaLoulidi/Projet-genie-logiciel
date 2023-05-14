package com.gestresmat.springjwt.security.services;

import com.gestresmat.springjwt.DTO.AffectationDTOO;
import com.gestresmat.springjwt.DTO.AffectationDto;
import com.gestresmat.springjwt.DTO.BesoinDto;
import com.gestresmat.springjwt.exceptions.ResourceNotFoundException;
import com.gestresmat.springjwt.models.*;
import com.gestresmat.springjwt.repository.*;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AffectationService {
    private final AffectationRepository affectationRepository;
    private final OrdinateurRepository ordinateurRepository;
    private final ImprimanteRepository imprimanteRepository;
    private final BesoinRepository besoinRepository;
    private final RessourceRepo ressourceRepository;

    private  final EnseignantRepository enseignantRepository;

    public AffectationService(AffectationRepository affectationRepository, OrdinateurRepository ordinateurRepository, ImprimanteRepository imprimanteRepository, BesoinRepository besoinRepository, RessourceRepo ressourceRepository, EnseignantRepository enseignantRepository) {
        this.affectationRepository = affectationRepository;
        this.ordinateurRepository = ordinateurRepository;
        this.imprimanteRepository = imprimanteRepository;

        this.besoinRepository = besoinRepository;
        this.ressourceRepository = ressourceRepository;
        this.enseignantRepository = enseignantRepository;
    }

    public List<Ressource> getAllRessources(){
        return ressourceRepository.findAll() ;
    }

    public List<Ordinateur> getAllOrdinateurs(){
        return ordinateurRepository.findAll() ;
    }

    public List<Imprimante> getAllImprimantes(){
        return imprimanteRepository.findAll() ;
    }

    public List<BesoinDto> getAllBesoins(){

        List<BesoinDto> result = new ArrayList<>();
        List<Besoin> besoins = besoinRepository.findAll();

        for (Besoin besoin : besoins) {
            BesoinDto besoinDto = new BesoinDto();
            besoinDto.setId(besoin.getId());
            besoinDto.setEnseignant(besoin.getEnseignant().getEmail());
            besoinDto.setEnseignantId(besoin.getEnseignant().getId());
            if (besoin.getType().equals("Ordinateur")){
                besoinDto.setCpu(besoin.getCpu());
                besoinDto.setDisqueDur(besoin.getDisqueDur());
                besoinDto.setEcran(besoin.getEcran());
                besoinDto.setMarque(besoin.getMarque());
                besoinDto.setRam(besoin.getRam());
                besoinDto.setType(besoin.getType());
            } else if (besoin.getType().equals("Imprimante")) {
                besoinDto.setVitesse(besoin.getVitesse());
                besoinDto.setResolution(besoin.getResolution());
                besoinDto.setMarque(besoin.getMarque());
                besoinDto.setType(besoin.getType());
            }
            result.add(besoinDto);
        }
        return result;
    }

    public Affectation saveAffectation(Date dateAffectation, Long enseignantId, Long ressourceId) {
        Enseignant enseignant = enseignantRepository.findById(enseignantId)
                .orElseThrow(() -> new RuntimeException("Enseignant not found with id " + enseignantId));
        Ressource ressource = ressourceRepository.findById(ressourceId)
                .orElseThrow(() -> new RuntimeException("Ressource not found with id " + ressourceId));
        Affectation affectation = new Affectation();
        affectation.setDateAffectation(dateAffectation);
        affectation.setEnseignant(enseignant);
        affectation.setRessource(ressource);
        return affectationRepository.save(affectation);
    }

    public List<AffectationDTOO> getAllAffectations() {
        List<Affectation> affectations = affectationRepository.findAll();
        List<AffectationDTOO> affectationDTOs = new ArrayList<>();

        for (Affectation affectation : affectations) {
            affectationDTOs.add(new AffectationDTOO(
                    affectation.getId(),
                    affectation.getDateAffectation(),
                    affectation.getEnseignant().getEmail(),
                    affectation.getRessource().getCodeBarre()
            ));
        }

        return affectationDTOs;
    }

    public AffectationDTOO updateAffectation(Long id, AffectationDTOO affectationDTOO) {
        Affectation affectation = affectationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Affectation with id " + id + " not found"));

        affectation.setDateAffectation(affectationDTOO.getDateAffectation());

        Ressource ressource = ressourceRepository.findByCodeBarre(affectationDTOO.getRessourceCodeBarre());


        affectation.setRessource(ressource);

        Affectation updatedAffectation = affectationRepository.save(affectation);
        return new AffectationDTOO(updatedAffectation.getId(),
                updatedAffectation.getDateAffectation(),
                updatedAffectation.getEnseignant().getEmail(),
                updatedAffectation.getRessource().getCodeBarre());
    }

    public void deleteAffectation(Long id){
        affectationRepository.deleteById(id);
    }

}


