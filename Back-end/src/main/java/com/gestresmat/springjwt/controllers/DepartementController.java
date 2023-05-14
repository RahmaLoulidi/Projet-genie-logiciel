package com.gestresmat.springjwt.controllers;

import com.gestresmat.springjwt.models.Departement;
import com.gestresmat.springjwt.security.services.DepartementService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departement")
public class DepartementController {

    private final DepartementService departementService;


    public DepartementController(DepartementService departementService) {
        this.departementService = departementService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Departement>> getAllDepartements(){
        List<Departement> departements= departementService.findAllDepartements();
        return new ResponseEntity<>(departements, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Departement> addDepartement(@RequestBody Departement departement){
        Departement newDepartement= departementService.addDepartement(departement);
        return new ResponseEntity<>(newDepartement, HttpStatus.CREATED);
    }
}
