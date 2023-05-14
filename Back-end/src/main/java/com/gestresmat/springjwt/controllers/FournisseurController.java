package com.gestresmat.springjwt.controllers;

import com.gestresmat.springjwt.models.Fournisseur;
import com.gestresmat.springjwt.repository.FournisseurRepo;
import com.gestresmat.springjwt.security.services.FournisseurService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/fourn")
public class FournisseurController {
    private final FournisseurService fournisseurService;

    public FournisseurController(FournisseurService fournisseurService) {
        this.fournisseurService = fournisseurService;
    }

    @GetMapping("find/{id}")
    public ResponseEntity<Fournisseur> getFournisseurById(@PathVariable("id") Long id){
        Fournisseur fournisseur=fournisseurService.findFournisseurById(id);
        return  new ResponseEntity<>(fournisseur, HttpStatus.OK);
    }

}
