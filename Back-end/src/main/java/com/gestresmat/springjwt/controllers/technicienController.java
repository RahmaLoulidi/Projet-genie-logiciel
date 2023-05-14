package com.gestresmat.springjwt.controllers;

import com.gestresmat.springjwt.models.Panne;
import com.gestresmat.springjwt.models.TechnicienMaintenance;
import com.gestresmat.springjwt.security.services.TechnicienService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tech")
public class technicienController {
    private final TechnicienService technicienService;

    public technicienController(TechnicienService technicienService) {
        this.technicienService = technicienService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<TechnicienMaintenance>> getAllTechnicienMaintenances(){
        List<TechnicienMaintenance> technicienMaintenances= technicienService.findAllTechnicienMaintenance();
        return new ResponseEntity<>(technicienMaintenances, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<TechnicienMaintenance> getTechnicienById(@PathVariable("id") Long id){
        TechnicienMaintenance technicien =technicienService.findTechnicienMaintenanceById(id);
        return new ResponseEntity<>(technicien, HttpStatus.OK);
    }
}
