package com.gestresmat.springjwt.controllers;


import com.gestresmat.springjwt.models.Besoin;
import com.gestresmat.springjwt.models.Constat;
import com.gestresmat.springjwt.models.Panne;
import com.gestresmat.springjwt.repository.BesoinRepository;
import com.gestresmat.springjwt.repository.UserRepository;

import com.gestresmat.springjwt.security.services.BesoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/besoin")
@CrossOrigin(origins = "*")
public class BesoinController {
    private final BesoinService besoinService;
    private final AuthController authController;



    @Autowired
    UserRepository userRepository;

    @Autowired
    BesoinRepository besoinRepository;

    public BesoinController(BesoinService besoinService, AuthController authController) {
        this.besoinService = besoinService;
        this.authController = authController;
    }
    @PostMapping("/add/{id}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<Besoin> addBesoin(@RequestBody Besoin besoin, @PathVariable("id") Long id){
        Besoin newBesoin = besoinService.addBesoin(besoin,id);
        return new ResponseEntity<>(newBesoin, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Besoin>> getAllBesoin(){
        List<Besoin> besoin= besoinService.findAllBesoin();
        return new ResponseEntity<>(besoin,HttpStatus.OK);
    }
    @GetMapping("/find/{id}")
    public ResponseEntity<Optional<Besoin>> getBesoinById(@PathVariable("id") Long id){
        Optional<Besoin> besoin = besoinService.findByIdBesoin(id);
        return  new ResponseEntity<Optional<Besoin>>(besoin,HttpStatus.OK);
    }

    @GetMapping("/livraison/Ordinateurs")
    public ResponseEntity<List<Besoin>> findOrdinateursLivrés(){
        List<Besoin> ordinateursLivrés = besoinService.findByTypeAndLivrableIsTrue("Ordinateur");
        return  new ResponseEntity<>(ordinateursLivrés, HttpStatus.OK);
    }

    @GetMapping("/livraison/Imprimantes")
    public ResponseEntity<List<Besoin>> findImprimantesLivrées(){
        List<Besoin> imprimantesLivrées = besoinService.findByTypeAndLivrableIsTrue("Imprimante");
        return  new ResponseEntity<>(imprimantesLivrées, HttpStatus.OK);
    }

    ///////////////////////////////////// AHLAM /////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////

    @GetMapping("/ordinateurs-non-affectes")
    public ResponseEntity<List<Besoin>> findOrdinateursNonAffectes() {
        List<Besoin> ordinateursNonAffectes = besoinService.findByTypeAndAppelOffreIsNull("ordinateur");
        return new ResponseEntity<>(ordinateursNonAffectes, HttpStatus.OK);
    }

    @GetMapping("/imprimantes-non-affectes")
    public ResponseEntity<List<Besoin>> findImprimantesNonAffectes() {
        List<Besoin> imprimantesNonAffectes = besoinService.findByTypeAndAppelOffreIsNull("imprimante");
        return new ResponseEntity<>(imprimantesNonAffectes, HttpStatus.OK);
    }

    @GetMapping("/ordinateurs-affectes/{id}")
    public ResponseEntity<List<Besoin>> findOrdinateursAffectes(@PathVariable(value = "id") Long id) {
        List<Besoin> ordinateursAffectes;
        ordinateursAffectes = besoinService.getBesoinsByAppelOffreIdAndType(id, "ordinateur");
        return new ResponseEntity<>(ordinateursAffectes, HttpStatus.OK);
    }

    @GetMapping("/imprimantes-affectes/{id}")
    public ResponseEntity<List<Besoin>> findImprimantesAffectes(@PathVariable(value = "id") Long id) {
        List<Besoin> imprimantesAffectes;
        imprimantesAffectes = besoinService.getBesoinsByAppelOffreIdAndType(id, "imprimante");
        return new ResponseEntity<>(imprimantesAffectes, HttpStatus.OK);
    }


    ////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////Rahma///////////////////////////////////////////////////////////




    @GetMapping("/ordinateurs")
    public ResponseEntity<List<Besoin>> findOrdinateurs(){
        List<Besoin> ordinateurs = besoinService.findByType("Ordinateur");
        return  new ResponseEntity<>(ordinateurs, HttpStatus.OK);
    }

    @GetMapping("/imprimantes")
    public ResponseEntity<List<Besoin>> findImprimantes(){
        List<Besoin> imprimantes = besoinService.findByType("Imprimante");
        return  new ResponseEntity<>(imprimantes, HttpStatus.OK);

    }

    @PutMapping("/updateO/{id}")
    public ResponseEntity<Besoin> updateO(@RequestBody Besoin newBesoin, @PathVariable("id") Long id){
        Besoin besoinToUpdate = besoinRepository.getBesoinById(id);
        if (besoinToUpdate != null) {
            // Mettre à jour les propriétés de la panne existante avec les nouvelles informations
            besoinToUpdate.setMarque(newBesoin.getMarque());
            besoinToUpdate.setCpu(newBesoin.getCpu());
            besoinToUpdate.setDisqueDur(newBesoin.getDisqueDur());
            besoinToUpdate.setEcran(newBesoin.getEcran());
            besoinToUpdate.setRam(newBesoin.getRam());

            // Appeler la méthode de service pour mettre à jour la panne dans la base de données
            Besoin updatedBesoin = besoinService.updateOrdi(besoinToUpdate);

            // Retourner la réponse avec la panne mise à jour et le code de statut HTTP 200 OK
            return new ResponseEntity<>(updatedBesoin, HttpStatus.OK);
        } else {
            // Retourner une réponse avec le code de statut HTTP 404 NOT FOUND si l'ID de la panne n'existe pas
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/updateI/{id}")
    public ResponseEntity<Besoin> updateI(@RequestBody Besoin newBesoin, @PathVariable("id") Long id){
        Besoin besoinToUpdate = besoinRepository.getBesoinById(id);
        if (besoinToUpdate != null) {
            // Mettre à jour les propriétés de la panne existante avec les nouvelles informations
            besoinToUpdate.setMarque(newBesoin.getMarque());
            besoinToUpdate.setResolution(newBesoin.getResolution());
            besoinToUpdate.setVitesse(newBesoin.getVitesse());

            // Appeler la méthode de service pour mettre à jour la panne dans la base de données
            Besoin updatedBesoin = besoinService.updateImp(besoinToUpdate);

            // Retourner la réponse avec la panne mise à jour et le code de statut HTTP 200 OK
            return new ResponseEntity<>(updatedBesoin, HttpStatus.OK);
        } else {
            // Retourner une réponse avec le code de statut HTTP 404 NOT FOUND si l'ID de la panne n'existe pas
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/send/{id}")
    public ResponseEntity<Besoin> envoieBesoin( @PathVariable("id") Long id,Besoin besoin){
        Besoin besoinToUpdate = besoinRepository.getBesoinById(id);
        if (besoinToUpdate != null) {


            // Appeler la méthode de service pour mettre à jour la panne dans la base de données
            Besoin updatedBesoin = besoinService.envoieBesoin(besoinToUpdate);

            // Retourner la réponse avec la panne mise à jour et le code de statut HTTP 200 OK
            return new ResponseEntity<>(updatedBesoin, HttpStatus.OK);
        } else {
            // Retourner une réponse avec le code de statut HTTP 404 NOT FOUND si l'ID de la panne n'existe pas
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/besoinEns/{id}")
    public ResponseEntity<Map<String, String>> getbesoinEns(@PathVariable("id") Long id) {
        Besoin besoin = besoinRepository.getBesoinById(id);
        Map<String, String> responseMap = new HashMap<>();
        responseMap.put("username", besoin.getEnseignant().getUsername());
        return new ResponseEntity<>(responseMap, HttpStatus.OK);
    }

    @GetMapping("/allsendO")
    public ResponseEntity<List<Besoin>> getAllBesoinSendO(){
        List<Besoin> besoins= besoinService.findAllBesoinEnvoie("Ordinateur");
        return new ResponseEntity<>(besoins,HttpStatus.OK);
    }

    @GetMapping("/allsendI")
    public ResponseEntity<List<Besoin>> getAllBesoinSendI(){
        List<Besoin> besoins= besoinService.findAllBesoinEnvoie("Imprimante");
        return new ResponseEntity<>(besoins,HttpStatus.OK);
    }


    @GetMapping("/ordinateurs/{id}")
    public ResponseEntity<List<Besoin>> findOrdinateursDep(@PathVariable("id") Long idEns){
        List<Besoin> ordinateurs = besoinService.findAllBesoinDep(idEns,"Ordinateur");
        return  new ResponseEntity<>(ordinateurs, HttpStatus.OK);
    }

    @GetMapping("/imprimantes/{id}")
    public ResponseEntity<List<Besoin>> findImprimantesDep(@PathVariable("id") Long idEns){
        List<Besoin> imprimantes = besoinService.findAllBesoinDep(idEns,"Imprimante");
        return  new ResponseEntity<>(imprimantes, HttpStatus.OK);
    }



}





