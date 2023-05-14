package com.gestresmat.springjwt.controllers;

import com.gestresmat.springjwt.exceptions.RessourceIntrouvableException;
import com.gestresmat.springjwt.models.Imprimante;

import com.gestresmat.springjwt.models.Ordinateur;
import com.gestresmat.springjwt.models.Ressource;
import com.gestresmat.springjwt.security.services.ImprimanteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/ressource/imprimante")
@CrossOrigin(origins = "*")
public class ImprimanteController {
    private final ImprimanteService imprimanteService;

    public ImprimanteController(ImprimanteService imprimanteService) {
        this.imprimanteService = imprimanteService;
    }
    @PostMapping("/add")
    @CrossOrigin(origins = "*")
    public ResponseEntity<Imprimante> addImprimante(@RequestBody Imprimante imprimante){
        Imprimante newImprimante = imprimanteService.addImprimante(imprimante);
        return new ResponseEntity<>(newImprimante, HttpStatus.CREATED);
    }
    @GetMapping("/all")
    public ResponseEntity<List<Imprimante>> getAllImprimantes(){
        List<Imprimante> imprimantes= imprimanteService.findAllImprimante();
        return new ResponseEntity<>(imprimantes,HttpStatus.OK);
    }


    @PutMapping("/update/{id}")
    public Imprimante updateImprimante(@PathVariable(value = "id") Long id, @RequestBody Map<String, String> imprimanteDetails) throws ParseException, RessourceIntrouvableException {
        String dureeGarantie = imprimanteDetails.get("dureeGarantie");
        String dateLivraisonString = imprimanteDetails.get("dateLivraison");
        Date dateLivraison = new SimpleDateFormat("yyyy-MM-dd").parse(dateLivraisonString);
        return imprimanteService.updateImprimante(id, dureeGarantie, dateLivraison);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteImprimante(@PathVariable("id") Long id) {
        imprimanteService.deleteImprimante(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping("/all/{id}")
    public ResponseEntity<List<Imprimante>> getAllImprimantesByEns(@PathVariable("id") Long id){
        List<Imprimante> imprimantes=imprimanteService.findImprimantesByEns(id);
        return new ResponseEntity<>(imprimantes,HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Imprimante> getRess(@PathVariable("id")  Long id) {
        Imprimante ressource= imprimanteService.getImprimanteById(id);
        return new ResponseEntity<>(ressource,HttpStatus.OK);
    }

}

