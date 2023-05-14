package com.gestresmat.springjwt.controllers;

import com.gestresmat.springjwt.models.Ordinateur;
import com.gestresmat.springjwt.models.Ressource;
import com.gestresmat.springjwt.security.services.RessourceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ressource")
@CrossOrigin(origins = "*")
public class RessourceController {
    private final RessourceService ressourceService;

    public RessourceController(RessourceService ressourceService) {
        this.ressourceService = ressourceService;
    }

    @GetMapping("/isprinter/{id}")
    public boolean isPrinter(@PathVariable("id")  Long id) {
        if (ressourceService.isPrinter(id) == false) {
            return false;
        }
        return true;
    }


    @GetMapping("/find/{id}")
    public ResponseEntity<Ressource> getRess(@PathVariable("id")  Long id) {
        Ressource ressource= ressourceService.getRessourceById(id);
        return new ResponseEntity<>(ressource,HttpStatus.OK);
    }
}
