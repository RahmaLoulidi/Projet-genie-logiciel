package com.gestresmat.springjwt.controllers;

import com.gestresmat.springjwt.models.Besoin;

import com.gestresmat.springjwt.security.services.BesoinService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/livraison")
@CrossOrigin(origins = "*")
public class LivraisonController {
    private final BesoinService besoinService;


    public LivraisonController(BesoinService besoinService) {
        this.besoinService = besoinService;

    }

    @GetMapping("/Ordinateurs")
    public ResponseEntity<List<Besoin>> findOrdinateursLivrés(){
        List<Besoin> ordinateursLivrés = besoinService.findByTypeAndLivrableIsTrue("Ordinateur");
        return  new ResponseEntity<>(ordinateursLivrés, HttpStatus.OK);
    }

    @GetMapping("/Imprimantes")
    public ResponseEntity<List<Besoin>> findImprimantesLivrées(){
        List<Besoin> imprimantesLivrées = besoinService.findByTypeAndLivrableIsTrue("Imprimante");
        return  new ResponseEntity<>(imprimantesLivrées, HttpStatus.OK);
    }

}
