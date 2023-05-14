package com.gestresmat.springjwt.controllers;


import com.gestresmat.springjwt.DTO.BesoinProposeDTO;
import com.gestresmat.springjwt.DTO.PropositionDto;
import com.gestresmat.springjwt.models.AppelOffre;
import com.gestresmat.springjwt.models.Besoin;
import com.gestresmat.springjwt.models.BesoinPropose;
import com.gestresmat.springjwt.models.Proposition;
import com.gestresmat.springjwt.repository.AppelOffreRepo;
import com.gestresmat.springjwt.repository.BesoinRepository;
import com.gestresmat.springjwt.security.services.AppelOffreService;
import com.gestresmat.springjwt.security.services.BesoinProposeService;
import com.gestresmat.springjwt.security.services.BesoinService;
import com.gestresmat.springjwt.security.services.PropositionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/proposition")
@CrossOrigin(origins = "*")
public class PropositionController {
    private final PropositionService propositionService;
    private final AppelOffreService appelOffreService;
    private final BesoinService besoinService;
    private final BesoinProposeService besoinProposeService;

    private final AppelOffreRepo appelOffreRepository;
    private final BesoinRepository besoinRepository;

    public PropositionController(PropositionService propositionService, AppelOffreService appelOffreService, BesoinService besoinService, BesoinProposeService besoinProposeService, AppelOffreRepo appelOffreRepository, BesoinRepository besoinRepository) {
        this.propositionService = propositionService;
        this.appelOffreService = appelOffreService;
        this.besoinService = besoinService;
        this.besoinProposeService = besoinProposeService;
        this.appelOffreRepository = appelOffreRepository;
        this.besoinRepository = besoinRepository;
    }


    /////////////////////// AJOUTER PROPOSITION FOURNISSEUR /////////////////////////////
    @GetMapping("appelOffre/{id}")
    public ResponseEntity<List<Besoin>> afficherPageAjouterProposition(@PathVariable(value = "id") Long appelOffreId, Model model) {
        AppelOffre appelOffre = appelOffreRepository.findById(appelOffreId)
                .orElseThrow(() -> new RuntimeException("AppelOffre non trouve"));

        List<Besoin> besoins = besoinRepository.findByAppelOffre(appelOffre);

        List<Besoin> besoinsOrdinateurs = besoins.stream()
                .filter(b -> b.getType().equals("Ordinateur"))
                .collect(Collectors.toList());

        List<Besoin> besoinsImprimantes = besoins.stream()
                .filter(b -> b.getType().equals("Imprimante"))
                .collect(Collectors.toList());

        model.addAttribute("appelOffre", appelOffre);
        model.addAttribute("besoinsOrdinateurs", besoinsOrdinateurs);
        model.addAttribute("besoinsImprimantes", besoinsImprimantes);
        model.addAttribute("besoinsProposeOrdinateur", besoinsOrdinateurs.stream().map(b -> new BesoinProposeDTO(b.getId(), "", BigDecimal.ZERO)).collect(Collectors.toList()));
        model.addAttribute("besoinsProposeImprimante", besoinsImprimantes.stream().map(b -> new BesoinProposeDTO(b.getId(), "", BigDecimal.ZERO)).collect(Collectors.toList()));

        return new ResponseEntity<>(besoins, HttpStatus.OK);

    }


    ///////////////////////////////////////////////////////////////////////////////////////////
    ///////////////// ADD_UPDATE_DELETE_PROPOSITION ///////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////
    @PostMapping("appelOffre/{id}/{idFour}")
    public Proposition sauvegarderProposition(@PathVariable(value = "id") Long appelOffreId,
                                         @RequestBody List<BesoinProposeDTO> besoinsPropose,
                                         @PathVariable(value = "idFour") Long fournisseurId) {
        List<BesoinPropose> propositions = new ArrayList<>();
        for (BesoinProposeDTO besoinProposeDTO : besoinsPropose) {
            Besoin besoin = besoinRepository.findById(besoinProposeDTO.getBesoinId())
                    .orElseThrow(() -> new RuntimeException("Besoin non trouvé"));
            BesoinPropose proposition = new BesoinPropose();
            proposition.setBesoin(besoin);
            proposition.setMarque(besoinProposeDTO.getMarque());
            proposition.setPrix(besoinProposeDTO.getPrix().doubleValue());
            propositions.add(proposition);
        }

        Proposition proposition = propositionService.ajouterProposition(appelOffreId, propositions, fournisseurId);
        return proposition;
    }



    @PutMapping("/fournisseur/{propositionId}/modifier/{fournisseurId}")
    public Proposition modifierProposition(@PathVariable(value = "propositionId") Long propositionId,
                                           @RequestBody List<BesoinProposeDTO> besoinsProposeDTO,
                                           @PathVariable(value = "fournisseurId") Long fournisseurId) {
        List<BesoinPropose> besoinsPropose = new ArrayList<>();
        for (BesoinProposeDTO besoinProposeDTO : besoinsProposeDTO) {
            Besoin besoin = besoinRepository.findById(besoinProposeDTO.getBesoinId())
                    .orElseThrow(() -> new RuntimeException("Besoin non trouvé"));
            BesoinPropose besoinPropose = new BesoinPropose();
            besoinPropose.setId(besoinProposeDTO.getId());
            besoinPropose.setBesoin(besoin);
            besoinPropose.setMarque(besoinProposeDTO.getMarque());
            besoinPropose.setPrix(besoinProposeDTO.getPrix().doubleValue());
            besoinsPropose.add(besoinPropose);
        }

        Proposition proposition = propositionService.modifierProposition(propositionId, besoinsPropose, fournisseurId);
        return proposition;
    }


    @DeleteMapping("/fournisseur/{propositionId}/supprimer")
    public ResponseEntity<?> supprimerProposition(@PathVariable("propositionId") Long propositionId) {
        try {
            propositionService.supprimerProposition(propositionId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////// ADD_UPDATE_DELETE_PROPOSITION ///////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////


    @GetMapping("/fournisseur/{propositionId}/besoinsProposes")
    public List<BesoinPropose> getBesoinsProposes(@PathVariable("propositionId") Long propositionId) {
        return propositionService.getBesoinsProposes(propositionId);
    }



    @GetMapping("/fournisseur/{fournisseurId}")
    public List<Proposition> getAllPropositionsByFournisseur(@PathVariable Long fournisseurId) {
        return propositionService.getAllPropositionsByFournisseur(fournisseurId);
    }







    ////////////////////////// SALMAAYA /////////////////////////////

    @GetMapping("/all")
    public ResponseEntity<List<PropositionDto>> getAllProposition(){
        List<PropositionDto> propositions= propositionService.getAllPropositions();
        return new ResponseEntity<>(propositions, HttpStatus.OK);
    }

    @PutMapping("/{id}/accepter")
    public ResponseEntity<?> accepterProposition(@PathVariable("id") Long id) {
        Proposition proposition = propositionService.accepterProposition(id);
        return ResponseEntity.ok(proposition);
    }

    @PutMapping("/{id}/rejeter")
    public ResponseEntity<?> rejeterProposition(@PathVariable("id") Long id) {
        Proposition proposition = propositionService.rejeterProposition(id);
        return ResponseEntity.ok(proposition);
    }

    @PutMapping("/{id}/eliminer")
    public ResponseEntity<?> eliminerProposition(@PathVariable("id") Long id) {
        Proposition proposition = propositionService.ListeNoirProposition(id);
        return ResponseEntity.ok(proposition);
    }



    @GetMapping("/fournisseur/details/{id}")
    public ResponseEntity<Proposition> findById(@PathVariable(value = "id") Long id) {
        Proposition proposition = propositionService.findById(id);
        if(proposition != null) {
            return new ResponseEntity<>(proposition, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    ////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////
    @GetMapping("/fournisseur/besoinpropose/ids")
    public List<BesoinPropose> getBesoinProposeByIds(@RequestParam("ids") List<Long> ids) {
        return besoinProposeService.getBesoinProposeByIds(ids);
    }

}
