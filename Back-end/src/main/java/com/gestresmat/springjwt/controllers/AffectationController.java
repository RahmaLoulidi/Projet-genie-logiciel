package com.gestresmat.springjwt.controllers;

import com.gestresmat.springjwt.DTO.AffectationDTOO;
import com.gestresmat.springjwt.DTO.AffectationDto;
import com.gestresmat.springjwt.DTO.BesoinDto;
import com.gestresmat.springjwt.models.*;
import com.gestresmat.springjwt.security.services.AffectationService;
import com.gestresmat.springjwt.security.services.BesoinService;
import javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/affectation")
@CrossOrigin(origins = "*")
public class AffectationController {
    private final AffectationService affectationService;


    public AffectationController(AffectationService affectationService) {
        this.affectationService = affectationService;
    }

    @PostMapping("/add")
    @CrossOrigin(origins = "*")
    public ResponseEntity<Affectation> addAffectation(@RequestBody AffectationDto affectationDto) {
        Date dateAffectation = affectationDto.getDateAffectation();
        Long enseignantId = affectationDto.getEnseignantId();
        Long ressourceId = affectationDto.getRessourceId();
        Affectation affectation = affectationService.saveAffectation(dateAffectation, enseignantId, ressourceId);
        return new ResponseEntity<>(affectation, HttpStatus.CREATED);
    }

    @GetMapping("/ressources")
    public ResponseEntity<List<Ressource>> getAllRessources(){
        List<Ressource> ressources= affectationService.getAllRessources();
        return new ResponseEntity<>(ressources, HttpStatus.OK);
    }

    @GetMapping("/ordinateurs")
    public ResponseEntity<List<Ordinateur>> getAllOrdinateurs(){
        List<Ordinateur> ordinateurs= affectationService.getAllOrdinateurs();
        return new ResponseEntity<>(ordinateurs, HttpStatus.OK);
    }

    @GetMapping("/imprimantes")
    public ResponseEntity<List<Imprimante>> getAllImprimantes(){
        List<Imprimante> imprimantes= affectationService.getAllImprimantes();
        return new ResponseEntity<>(imprimantes, HttpStatus.OK);
    }

    @GetMapping("/besoins")
    public ResponseEntity<List<BesoinDto>> getAllBesoin(){
        List<BesoinDto> besoins= affectationService.getAllBesoins();
        return new ResponseEntity<>(besoins,HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<AffectationDTOO>> getAllAffectations() {
        List<AffectationDTOO> affectations = affectationService.getAllAffectations();
        return new ResponseEntity<>(affectations, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<AffectationDTOO> updateAffectation(@PathVariable("id") Long id, @RequestBody AffectationDTOO affectationDTOO) {
        AffectationDTOO updatedAffectation = affectationService.updateAffectation(id, affectationDTOO);
        return ResponseEntity.ok(updatedAffectation);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteAffectation(@PathVariable("id") Long id) {
        affectationService.deleteAffectation(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }





}
