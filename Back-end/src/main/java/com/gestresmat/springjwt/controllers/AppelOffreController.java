package com.gestresmat.springjwt.controllers;

import com.gestresmat.springjwt.models.AppelOffre;
import com.gestresmat.springjwt.security.services.AppelOffreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/appelOffre")
public class AppelOffreController {
    private final AppelOffreService appelOffreService;


    public AppelOffreController(AppelOffreService appelOffreService) {
        this.appelOffreService = appelOffreService;
    }


    @GetMapping("/all")
    public ResponseEntity<List<AppelOffre>> getAllAppelOffres(){
        List<AppelOffre> appelOffres= appelOffreService.findAllAppelOffres();
        return new ResponseEntity<>(appelOffres, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppelOffre> findById(@PathVariable(value = "id") Long id) {
        AppelOffre appelOffre = appelOffreService.findById(id);
        if(appelOffre != null) {
            return new ResponseEntity<>(appelOffre, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/creer")
    public ResponseEntity<AppelOffre> creerAppelOffre(@RequestBody AppelOffre request) {
        AppelOffre nouvelAppelOffre = appelOffreService.creerAppelOffre(request.getBesoins(), request.getDateDebut(), request.getDateFin());
        return ResponseEntity.ok(nouvelAppelOffre);
    }

    @PutMapping("/modifier/{appelOffreId}")
    public ResponseEntity<AppelOffre> modifierAppelOffre(@PathVariable Long appelOffreId,
                                                         @RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") Date dateDebut,
                                                         @RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") Date dateFin) {
        AppelOffre appelOffreModifie = appelOffreService.modifierAppelOffre(appelOffreId, dateDebut, dateFin);
        return ResponseEntity.ok(appelOffreModifie);
    }


    @GetMapping("/fournisseurIdNull")
    public List<AppelOffre> findAppelOffreByFournisseurIdIsNull() {
        List<AppelOffre> appelOffreByFournisseurIdIsNull = appelOffreService.findByFournisseurIdIsNull();
        return new ResponseEntity<>(appelOffreByFournisseurIdIsNull, HttpStatus.OK).getBody();
    }







}
