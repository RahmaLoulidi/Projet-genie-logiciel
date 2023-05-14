package com.gestresmat.springjwt.controllers;

import com.gestresmat.springjwt.models.Enseignant;

import com.gestresmat.springjwt.security.services.EnseignantService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ens")
public class EnseignantController {
    private final EnseignantService enseignantService;


    public EnseignantController(EnseignantService enseignantService) {
        this.enseignantService = enseignantService;
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Enseignant> getEnseignantById(@PathVariable("id") Long id){
        Enseignant enseignant=enseignantService.findEnseignantById(id);
        return  new ResponseEntity<>(enseignant, HttpStatus.OK);

    }
}
