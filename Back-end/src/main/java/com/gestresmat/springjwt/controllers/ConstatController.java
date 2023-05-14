package com.gestresmat.springjwt.controllers;

import com.gestresmat.springjwt.models.Constat;
import com.gestresmat.springjwt.repository.ConstatRepo;
import com.gestresmat.springjwt.security.services.ConstatService;
import com.gestresmat.springjwt.security.services.RessourceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/constat")
public class ConstatController {
    private final ConstatService constatService;
    private final ConstatRepo constatRepo;

    private final RessourceService ressourceService;

    public ConstatController(ConstatService constatService, ConstatRepo constatRepo, RessourceService ressourceService) {
        this.constatService = constatService;
        this.constatRepo = constatRepo;
        this.ressourceService = ressourceService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Constat>> getAllConstats(){
        List<Constat> constats= constatService.findAllConstat();
        return new ResponseEntity<>(constats,HttpStatus.OK);
    }
    @GetMapping("/all/{id}")
    public ResponseEntity<List<Constat>> getAllConstatsTech(@PathVariable("id") Long idTech){
        List<Constat> constats= constatService.findAllConstatTech(idTech);
        return new ResponseEntity<>(constats, HttpStatus.OK);
    }
    @PostMapping("/add")
    public ResponseEntity<Constat> addConstat(@RequestBody Constat constat){
        Constat newConstat= constatService.addConstat(constat);
        return new ResponseEntity<>(newConstat, HttpStatus.CREATED);
    }
    @PostMapping("/add/{id}")
    public void saveConstat(@RequestBody Constat constat,@PathVariable("id") Long id){
        constatService.saveConstat(constat,id);
    }
    @PostMapping("/save/{id}/{idTech}")
    public void saveConstatTech(@RequestBody Constat constat,@PathVariable("id") Long idPanne,@PathVariable("idTech")Long idTech){
        constatService.saveConstatTech(constat,idPanne,idTech);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Constat> updateConstat(@RequestBody Constat newConstat, @PathVariable("id") Long id){
        Constat constatToUpdate = constatRepo.findConstatById(id);
        if (constatToUpdate != null) {
            // Mettre à jour les propriétés de la panne existante avec les nouvelles informations
            constatToUpdate.setExplication(newConstat.getExplication());
            constatToUpdate.setDateApparition(newConstat.getDateApparition());
            constatToUpdate.setFrequence(newConstat.getFrequence());
            constatToUpdate.setOrdre(newConstat.getOrdre());

            // Appeler la méthode de service pour mettre à jour la panne dans la base de données
            Constat updatedConstat = constatService.updateConstat(constatToUpdate);

            // Retourner la réponse avec la panne mise à jour et le code de statut HTTP 200 OK
            return new ResponseEntity<>(updatedConstat, HttpStatus.OK);
        } else {
            // Retourner une réponse avec le code de statut HTTP 404 NOT FOUND si l'ID de la panne n'existe pas
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePanne(@PathVariable("id") Long id) {
        constatService.deleteConstat(id);
        return new ResponseEntity<>("Panne supprimée avec succès.", HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Constat> getConstatById(@PathVariable("id") Long id){
        Constat constat =constatService.findConstatById(id);
        return new ResponseEntity<>(constat,HttpStatus.OK);
    }

    @GetMapping("/allsend")
    public ResponseEntity<List<Constat>> getAllConstatSend(){
        List<Constat> constats= constatService.findAllConstatSend();
        return new ResponseEntity<>(constats,HttpStatus.OK);
    }

    @PutMapping("/send/{id}")
    public ResponseEntity<Constat> sendConstat( @PathVariable("id") Long id,Constat constat){
        Constat constatToUpdate = constatRepo.findConstatById(id);
        if (constatToUpdate != null) {


            // Appeler la méthode de service pour mettre à jour la panne dans la base de données
            Constat updatedConstat = constatService.sendConstat(constatToUpdate);

            // Retourner la réponse avec la panne mise à jour et le code de statut HTTP 200 OK
            return new ResponseEntity<>(updatedConstat, HttpStatus.OK);
        } else {
            // Retourner une réponse avec le code de statut HTTP 404 NOT FOUND si l'ID de la panne n'existe pas
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/reparer/{id}")
    public ResponseEntity<Constat> reparerConstat( @PathVariable("id") Long id,Constat constat){
        Constat constatToUpdate = constatRepo.findConstatById(id);
        if (constatToUpdate != null) {


            // Appeler la méthode de service pour mettre à jour la panne dans la base de données
            Constat updatedConstat = constatService.reparerConstat(constatToUpdate);

            // Retourner la réponse avec la panne mise à jour et le code de statut HTTP 200 OK
            return new ResponseEntity<>(updatedConstat, HttpStatus.OK);
        } else {
            // Retourner une réponse avec le code de statut HTTP 404 NOT FOUND si l'ID de la panne n'existe pas
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/renvoyer/{id}")
    public ResponseEntity<Constat> revoyerConstat( @PathVariable("id") Long id,Constat constat){
        Constat constatToUpdate = constatRepo.findConstatById(id);
        if (constatToUpdate != null) {


            // Appeler la méthode de service pour mettre à jour la panne dans la base de données
            Constat updatedConstat = constatService.revoyerConstat(constatToUpdate);

            // Retourner la réponse avec la panne mise à jour et le code de statut HTTP 200 OK
            return new ResponseEntity<>(updatedConstat, HttpStatus.OK);
        } else {
            // Retourner une réponse avec le code de statut HTTP 404 NOT FOUND si l'ID de la panne n'existe pas
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
