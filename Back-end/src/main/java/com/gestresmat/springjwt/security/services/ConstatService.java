package com.gestresmat.springjwt.security.services;

import com.gestresmat.springjwt.models.Constat;
import com.gestresmat.springjwt.models.Panne;
import com.gestresmat.springjwt.models.Ressource;
import com.gestresmat.springjwt.models.TechnicienMaintenance;
import com.gestresmat.springjwt.repository.ConstatRepo;
import com.gestresmat.springjwt.repository.PanneRepo;
import com.gestresmat.springjwt.repository.RessourceRepo;
import com.gestresmat.springjwt.repository.TechnicienRepo;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;

@Service
public class ConstatService {
    private final ConstatRepo constatRepo;
    private final PanneRepo panneRepo;
    private final TechnicienRepo technicienRepo;
    private final RessourceRepo ressourceRepo;
    private final MessageService messageService;


    public ConstatService(ConstatRepo constatRepo, PanneRepo panneRepo, TechnicienRepo technicienRepo, RessourceRepo ressourceRepo, MessageService messageService) {
        this.constatRepo = constatRepo;
        this.panneRepo = panneRepo;

        this.technicienRepo = technicienRepo;
        this.ressourceRepo = ressourceRepo;
        this.messageService = messageService;
    }

    public Constat addConstat(Constat constat){
        return constatRepo.save(constat);
    }

    public List<Constat> findAllConstat(){
        return constatRepo.findAllByOrderByIdDesc();
    }

    public void saveConstat(Constat constat,Long id){
        Panne panne=panneRepo.findPanneById(id).get();
        constat.setPanne(panne);
        constatRepo.save(constat);
    }
    public void saveConstatTech(Constat constat,Long idPanne,Long idTech){
        TechnicienMaintenance technicienMaintenance=technicienRepo.findTechnicienMaintenanceById(idTech);
        Panne panne=panneRepo.findPanneById(idPanne).get();
        Ressource ressource = panne.getRessource();
        if(ressource != null){
            constat.setRessource(ressource);
        }
        constat.setPanne(panne);
        constat.setTechnicienMaintenance(technicienMaintenance);
        constatRepo.save(constat);
    }

    public Constat updateConstat(Constat constat){
        return constatRepo.save(constat);
    }

    @Transactional
    public void deleteConstat(Long id) {
        constatRepo.deleteConstatById(id);
    }

    public Constat findConstatById(Long id){
        return constatRepo.findConstatById(id);
    }

    public List<Constat> findAllConstatTech( Long idTech){
        TechnicienMaintenance technicienMaintenance= technicienRepo.findTechnicienMaintenanceById(idTech);
        List<Constat> constats= constatRepo.findConstatsByTechnicienMaintenance(technicienMaintenance);

        return constats;
    }

    public List<Constat> findAllConstatSend(){
        return constatRepo.findConstatSend();
    }

    public Constat sendConstat(Constat constat){
        Long valeur = Long.valueOf(Long.parseLong("1"));
        messageService.creerMessage(Collections.singletonList(valeur), "Constat envoyé", "Constat envoyé", constat.getTechnicienMaintenance().getId());
        constat.setSend(1);
        return constatRepo.save(constat);
    }
    public Constat reparerConstat(Constat constat){
        constat.setReparation(1);
        return constatRepo.save(constat);
    }

    public Constat revoyerConstat(Constat constat){
        constat.setRenvoie(1);
        return constatRepo.save(constat);
    }

}


