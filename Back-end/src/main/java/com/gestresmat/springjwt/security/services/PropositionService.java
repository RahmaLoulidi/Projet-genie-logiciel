package com.gestresmat.springjwt.security.services;

import com.gestresmat.springjwt.DTO.PropositionDto;
import com.gestresmat.springjwt.models.*;
import com.gestresmat.springjwt.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class PropositionService {

    private final AppelOffreRepo appelOffreRepository;
    private final PropositionRepository propositionRepository;
    private final BesoinRepository besoinRepository;
    private final BesoinProposeRepository besoinProposeRepository;
    private final FournisseurRepo fournisseurRepo;
    private final ListeNoirRepository listeNoirRepository;
    private final AppelOffreService appelOffreService;
    private final MessageService messageService;
    public PropositionService(AppelOffreRepo appelOffreRepository, PropositionRepository propositionRepository, BesoinRepository besoinRepository, BesoinProposeRepository besoinProposeRepository, FournisseurRepo fournisseurRepo, ListeNoirRepository listeNoirRepository, AppelOffreService appelOffreService, MessageService messageService) {
        this.appelOffreRepository = appelOffreRepository;
        this.propositionRepository = propositionRepository;
        this.besoinRepository = besoinRepository;
        this.besoinProposeRepository = besoinProposeRepository;
        this.fournisseurRepo = fournisseurRepo;
        this.listeNoirRepository = listeNoirRepository;
        this.appelOffreService = appelOffreService;
        this.messageService = messageService;
    }

    ///////////////////////// AJOUTER PROPOSITION FOURNISSEUR /////////////////////////////
    @Transactional
    public Proposition ajouterProposition(Long appelOffreId, List<BesoinPropose> besoinsPropose,Long idFour) {
        Fournisseur fournisseur=fournisseurRepo.findFournisseurById(idFour);

        AppelOffre appelOffre = appelOffreRepository.findById(appelOffreId)
                .orElseThrow(() -> new RuntimeException("AppelOffre non trouvé"));


        double prixTotal = 0.0;
        Proposition proposition = new Proposition();
        proposition.setAppelOffre(appelOffre);
        proposition.setFournisseur(fournisseur);


        for (BesoinPropose besoinPropose : besoinsPropose) {
            prixTotal += besoinPropose.getPrix().doubleValue();
            besoinPropose.setProposition(proposition);
            besoinProposeRepository.save(besoinPropose);
        }

        proposition.setPrixTotal(BigDecimal.valueOf(prixTotal));
        proposition.setEtat(EtatProposition.EN_ATTENTE);
        proposition = propositionRepository.save(proposition);
        return proposition;
    }


    ///////////////////////// MODIFIER PROPOSITION FOURNISSEUR /////////////////////////////
    @Transactional
    public Proposition modifierProposition(Long propositionId, List<BesoinPropose> besoinsPropose, Long idFour) {
        Proposition proposition = propositionRepository.findById(propositionId)
                .orElseThrow(() -> new RuntimeException("Proposition non trouvée"));

        // Vérifie si la proposition n'est pas encore validée ou refusée
        if (!proposition.getEtat().equals(EtatProposition.EN_ATTENTE)) {
            throw new RuntimeException("La proposition ne peut pas être modifiée");
        }

        Fournisseur fournisseur = fournisseurRepo.findFournisseurById(idFour);
        proposition.setFournisseur(fournisseur);

        double prixTotal = 0.0;

        // Met à jour les besoins proposés existants
        for (BesoinPropose besoinPropose : besoinsPropose) {
            BesoinPropose existingBesoinPropose = besoinProposeRepository.findById(besoinPropose.getId())
                    .orElseThrow(() -> new RuntimeException("BesoinPropose non trouvé"));

            // Vérifie si le besoinPropose appartient à la proposition à modifier
            if (!existingBesoinPropose.getProposition().getId().equals(proposition.getId())) {
                throw new RuntimeException("Le besoinPropose ne peut pas être modifié");
            }

            existingBesoinPropose.setMarque(besoinPropose.getMarque());
            existingBesoinPropose.setPrix(besoinPropose.getPrix().doubleValue());
            besoinProposeRepository.save(existingBesoinPropose);
            prixTotal += existingBesoinPropose.getPrix().doubleValue();
        }

        // Ajoute de nouveaux besoins proposés
        for (BesoinPropose besoinPropose : besoinsPropose) {
            if (besoinPropose.getId() == null) {
                Besoin besoin = besoinRepository.findById(besoinPropose.getBesoin().getId())
                        .orElseThrow(() -> new RuntimeException("Besoin non trouvé"));
                besoinPropose.setBesoin(besoin);
                besoinPropose.setProposition(proposition);
                besoinProposeRepository.save(besoinPropose);
                prixTotal += besoinPropose.getPrix().doubleValue();
            }
        }

        proposition.setPrixTotal(BigDecimal.valueOf(prixTotal));
        propositionRepository.save(proposition);
        return proposition;
    }

    ////////////////////////// SUPPRIMER PROPOSITION ////////////////////////
    @Transactional
    public void supprimerProposition(Long propositionId) {
        Proposition proposition = propositionRepository.findById(propositionId)
                .orElseThrow(() -> new RuntimeException("Proposition non trouvée"));

        // Vérifie si la proposition n'est pas encore validée ou refusée
        if (!proposition.getEtat().equals(EtatProposition.EN_ATTENTE)) {
            throw new RuntimeException("La proposition ne peut pas être supprimée");
        }

        // Supprime les besoins proposés de la proposition
        besoinProposeRepository.deleteByPropositionId(propositionId);

        // Supprime la proposition
        propositionRepository.delete(proposition);
    }


    public List<BesoinPropose> getBesoinsProposes(Long propositionId) {
        return besoinProposeRepository.findByPropositionId(propositionId);
    }






//    @Transactional
//    public void ajouterProposition(Long appelOffreId, List<BesoinPropose> besoinsPropose,Long idFour) {
//        Fournisseur fournisseur=fournisseurRepo.findFournisseurById(idFour);
//
//        AppelOffre appelOffre = appelOffreRepository.findById(appelOffreId)
//                .orElseThrow(() -> new RuntimeException("AppelOffre non trouvé"));
//
//
//        double prixTotal = 0.0;
//        Proposition proposition = new Proposition();
//        proposition.setAppelOffre(appelOffre);
//        proposition.setFournisseur(fournisseur);
//
//
//        for (BesoinPropose besoinPropose : besoinsPropose) {
//            prixTotal += besoinPropose.getPrix().doubleValue();
//            besoinPropose.setProposition(proposition);
//            besoinProposeRepository.save(besoinPropose);
//        }
//
//        proposition.setPrixTotal(BigDecimal.valueOf(prixTotal));
//        proposition.setEtat(EtatProposition.EN_ATTENTE);
//        propositionRepository.save(proposition);
//
//    }
    ////////////////////////////////////////////////////////////////////////////////




    public List<Proposition> getAllPropositionsByFournisseur(Long fournisseurId) {
        return propositionRepository.findByFournisseurId(fournisseurId);
    }

//    public List<Proposition> findAllPropositions() {
//        return propositionRepository.findAll();
//    }






    ///////////////////////////// SALMAAYA ////////////////////////////

    public List<Proposition> findAllProposition(){
        return propositionRepository.findAll();
    }

    public List<PropositionDto> getAllPropositions() {
        List<Proposition> propositions = propositionRepository.findAll();
        List<PropositionDto> propositionDTOs = new ArrayList<>();

        for (Proposition proposition : propositions) {
            PropositionDto propositionDto = new PropositionDto();
            propositionDto.setId(proposition.getId());
            propositionDto.setPrixTotal(proposition.getPrixTotal());
            propositionDto.setDateDebut(proposition.getAppelOffre().getDateDebut());
            propositionDto.setDateFin(proposition.getAppelOffre().getDateFin());
            propositionDto.setNomFournisseur(proposition.getFournisseur().getNomSociete());
            propositionDto.setEtat(proposition.getEtat());
            propositionDTOs.add(propositionDto);
        }

        return propositionDTOs;
    }

    @Transactional
    public Proposition accepterProposition(Long propositionId) {
        Proposition proposition = propositionRepository.findById(propositionId).orElse(null);
        Long valeur = Long.valueOf(Long.parseLong("1"));

        if (proposition == null) {
            throw new EntityNotFoundException("Proposition non trouvée");
        }

        if (proposition != null) {
            // Rejeter toutes les autres propositions pour cet appel d'offres
            List<Proposition> autresPropositions = propositionRepository.findByAppelOffreIdAndIdNot(proposition.getAppelOffre().getId(), proposition.getId());
            for (Proposition autreProposition : autresPropositions) {
                autreProposition.setEtat(EtatProposition.REJETEE);
                messageService.creerMessage(Collections.singletonList(autreProposition.getFournisseur().getId()),"Rejet","Rejet",valeur);

                //notificationService.notifierFournisseurProposition(autreProposition);
            }

            // Accepter la proposition sélectionnée
            proposition.setEtat(EtatProposition.ACCEPTEE);
            //notificationService.notifierFournisseurProposition(proposition);
            Long fournisseurId = proposition.getFournisseur().getId();
            Long appelOffreId = proposition.getAppelOffre().getId();
            messageService.creerMessage(Collections.singletonList(fournisseurId),"Acceptation","Acceptation",valeur);
           appelOffreService.updateAppelOffreWithFournisseur(appelOffreId, fournisseurId);
        }

        return proposition;
    }

    @Transactional
    public Proposition rejeterProposition(Long propositionId) {
        Proposition proposition = propositionRepository.findById(propositionId).orElse(null);
        Long valeur = Long.valueOf(Long.parseLong("1"));
        if (proposition == null) {
            throw new EntityNotFoundException("Proposition non trouvée");
        }
        // Rejeter la proposition sélectionnée
        proposition.setEtat(EtatProposition.REJETEE);
        messageService.creerMessage(Collections.singletonList(proposition.getFournisseur().getId()),"Rejet","Rejet",valeur);
        //notificationService.notifierFournisseurProposition(proposition);
        return proposition;
    }

    @Transactional
    public Proposition ListeNoirProposition(Long propositionId) {
        Proposition proposition = propositionRepository.findById(propositionId).orElse(null);
        if (proposition == null) {
            throw new EntityNotFoundException("Proposition non trouvée");
        }
        // Eliminer la proposition sélectionnée
        proposition.setEtat(EtatProposition.LISTE_NOIRE);
        //notificationService.notifierFournisseurProposition(proposition);
        Long fournisseurId = proposition.getFournisseur().getId();

        Fournisseur fournisseur = proposition.getFournisseur();
        if (fournisseur == null) {
            throw new EntityNotFoundException("Fournisseur non trouvé pour la proposition");
        }
        // Récupérer ou créer une nouvelle liste noire
        ListeNoir listeNoir = new ListeNoir();
        listeNoir = listeNoirRepository.save(listeNoir);
        // Ajouter la liste noire au fournisseur
        fournisseur.setListeNoir(listeNoir);
        fournisseurRepo.save(fournisseur);

        return proposition;

    }


    public Proposition findById(Long id) {
        return propositionRepository.findById(id).orElse(null);
    }



}
