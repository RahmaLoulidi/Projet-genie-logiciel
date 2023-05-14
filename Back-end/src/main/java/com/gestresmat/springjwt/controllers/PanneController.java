package com.gestresmat.springjwt.controllers;

import com.gestresmat.springjwt.models.Panne;
import com.gestresmat.springjwt.repository.PanneRepo;
import com.gestresmat.springjwt.security.services.PanneService;
import com.gestresmat.springjwt.security.services.RessourceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/panne")
public class PanneController {
    private final PanneService panneService;
    private final PanneRepo panneRepo;

    private final RessourceService ressourceService;


    public PanneController(PanneService panneService, PanneRepo panneRepo, RessourceService ressourceService) {
        this.panneService = panneService;
        this.panneRepo = panneRepo;
        this.ressourceService = ressourceService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Panne>> getAllPannes(){
        List<Panne> pannes= panneService.findAllPanne();
        return new ResponseEntity<>(pannes, HttpStatus.OK);
    }
    @GetMapping("/all/{id}")
    public ResponseEntity<List<Panne>> getAllPannesEns( @PathVariable("id") Long idEns){
        List<Panne> pannes= panneService.findAllPanneEnseignant(idEns);
        return new ResponseEntity<>(pannes, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Panne> addPanne(@RequestBody Panne panne){
        Panne newPanne= panneService.addPanne(panne);
        return new ResponseEntity<>(newPanne, HttpStatus.CREATED);
    }
    @PostMapping("/save/{id}")
    public void savePanne(@RequestBody Panne panne, @PathVariable("id") Long idEns){
        panneService.savePanne(panne,idEns);
    }
    @PostMapping("/saveRes/{idE}/{idR}")
    public void savePanneRes(@RequestBody Panne panne, @PathVariable("idE") Long idEns,@PathVariable("idR") Long idRes){
        panneService.savePanneRes(panne,idEns,idRes);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Panne> getPanneById(@PathVariable("id") Long id){
        Panne panne =panneService.findPanneById(id);
        return new ResponseEntity<>(panne,HttpStatus.OK);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Panne> updatePanne(@RequestBody Panne newPanne,@PathVariable("id") Long id){
        Panne panneToUpdate = panneRepo.findPanneById(id).get();
        if (panneToUpdate != null) {
            // Mettre à jour les propriétés de la panne existante avec les nouvelles informations
            panneToUpdate.setExplication(newPanne.getExplication());
            panneToUpdate.setDatePanne(newPanne.getDatePanne());

            // Appeler la méthode de service pour mettre à jour la panne dans la base de données
            Panne updatedPanne = panneService.updatePanne(panneToUpdate);

            // Retourner la réponse avec la panne mise à jour et le code de statut HTTP 200 OK
            return new ResponseEntity<>(updatedPanne, HttpStatus.OK);
        } else {
            // Retourner une réponse avec le code de statut HTTP 404 NOT FOUND si l'ID de la panne n'existe pas
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePanne(@PathVariable("id") Long id) {
        panneService.deletePanne(id);
        return new ResponseEntity<>("Panne supprimée avec succès.", HttpStatus.OK);
    }

    @GetMapping("/isprinter/{id}")
    public ResponseEntity<Boolean> isPrinterPanne(@PathVariable("id") Long id){
        Panne panne = panneService.findPanneById(id);
        if(ressourceService.isPrinter(panne.getRessource().getId())==false){
            return new ResponseEntity<>(false, HttpStatus.OK);
        }
        else return new ResponseEntity<>(true, HttpStatus.OK);

    }


}
