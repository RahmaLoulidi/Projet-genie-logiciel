package com.gestresmat.springjwt.controllers;

import com.gestresmat.springjwt.exceptions.RessourceIntrouvableException;
import com.gestresmat.springjwt.models.Imprimante;
import com.gestresmat.springjwt.models.Ordinateur;

import com.gestresmat.springjwt.models.Ressource;
import com.gestresmat.springjwt.security.services.OrdinateurService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/ressource/ordinateur")
@CrossOrigin(origins = "*")
public class OrdinateurController {
    private final OrdinateurService ordinateurService;

    public OrdinateurController(OrdinateurService ordinateurService) {
        this.ordinateurService = ordinateurService;
    }

    @PostMapping("/add")
    @CrossOrigin(origins = "*")
    public ResponseEntity<Ordinateur> addOrdinateur(@RequestBody Ordinateur ordinateur){
        Ordinateur newOrdinateur = ordinateurService.addOrdinateur(ordinateur);
        return new ResponseEntity<>(newOrdinateur, HttpStatus.CREATED);
    }
    @GetMapping("/all")
    public ResponseEntity<List<Ordinateur>> getAllOrdinateur(){
        List<Ordinateur> ordinateur= ordinateurService.findAllOrdinateur();
        return new ResponseEntity<>(ordinateur,HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public Ordinateur updateOrdinateur(@PathVariable(value = "id") Long id, @RequestBody Map<String, String> ordinateurDetails) throws ParseException, RessourceIntrouvableException {
        String dureeGarantie = ordinateurDetails.get("dureeGarantie");
        String dateLivraisonString = ordinateurDetails.get("dateLivraison");
        Date dateLivraison = new SimpleDateFormat("yyyy-MM-dd").parse(dateLivraisonString);
        return ordinateurService.updateOrdinateur(id, dureeGarantie, dateLivraison);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteOrdinateur(@PathVariable("id") Long id) {
        ordinateurService.deleteOrdinateur(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping("/all/{id}")
    public ResponseEntity<List<Ordinateur>> getAllOrdinateursByEns(@PathVariable("id") Long id){
        List<Ordinateur> ordinateurs=ordinateurService.findOrdinateursByEns(id);
        return new ResponseEntity<>(ordinateurs,HttpStatus.OK);
    }


    @GetMapping("/find/{id}")
    public ResponseEntity<Ordinateur> getRess(@PathVariable("id")  Long id) {
        Ordinateur ressource= ordinateurService.getOrdinateurById(id);
        return new ResponseEntity<>(ressource,HttpStatus.OK);
    }

}