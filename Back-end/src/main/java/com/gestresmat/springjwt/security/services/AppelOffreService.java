package com.gestresmat.springjwt.security.services;

import com.gestresmat.springjwt.models.AppelOffre;
import com.gestresmat.springjwt.models.Besoin;
import com.gestresmat.springjwt.models.Fournisseur;
import com.gestresmat.springjwt.models.User;
import com.gestresmat.springjwt.repository.AppelOffreRepo;
import com.gestresmat.springjwt.repository.BesoinRepository;
import com.gestresmat.springjwt.repository.FournisseurRepo;
import com.gestresmat.springjwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;

@Service
public class AppelOffreService {
    private final AppelOffreRepo appelOffreRepo;

    private FournisseurRepo fournisseurRepo;

    @Autowired
    private BesoinRepository besoinRepo;

    private final UserRepository userRepository;

    @Autowired
    public AppelOffreService(AppelOffreRepo appelOffreRepo, UserRepository userRepository) {
        this.appelOffreRepo = appelOffreRepo;
        this.userRepository = userRepository;
    }

    @PersistenceContext
    private EntityManager entityManager;

    public AppelOffre addAppelOffre(AppelOffre appelOffre) {
        return appelOffreRepo.save(appelOffre);
    }

    public List<AppelOffre> findAllAppelOffres() {
        return appelOffreRepo.findAllByOrderByIdDesc();
    }

    public AppelOffre findById(Long id) {
        return appelOffreRepo.findById(id).orElse(null);
    }


    ///////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////// ADD _ EDIT _ DELETE ////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////
    public AppelOffre creerAppelOffre(List<Long> idsBesoins, Date dateDebut, Date dateFin) {
        List<Besoin> besoins = besoinRepo.findAllById(idsBesoins);
        AppelOffre appelOffre = new AppelOffre(dateDebut, dateFin);

        appelOffre = appelOffreRepo.save(appelOffre);

        for (Besoin besoin : besoins) {
            Besoin b = besoinRepo.findById(besoin.getId()).orElseThrow(() -> new RuntimeException("Besoin non trouvé"));
            b.setAppelOffre(appelOffre);
            besoinRepo.save(b);
        }

        appelOffre.setBesoins(besoins);

        return appelOffre;
    }

    public AppelOffre modifierAppelOffre(Long appelOffreId, Date dateDebut, Date dateFin) {
        AppelOffre appelOffre = appelOffreRepo.findById(appelOffreId)
                .orElseThrow(() -> new RuntimeException("Appel d'offre non trouvé"));

        appelOffre.mettreAJourDates(dateDebut, dateFin);

        return appelOffreRepo.save(appelOffre);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////// ADD _ EDIT _ DELETE ////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////

    public List<AppelOffre> findByFournisseurIdIsNull() {
        return appelOffreRepo.findByFournisseurIdIsNull();
    }





//////////////////SALMAYA///////////////////
@Transactional
public AppelOffre updateAppelOffreWithFournisseur(Long appelOffreId, Long fournisseurId) {
    AppelOffre appelOffre = appelOffreRepo.findById(appelOffreId).orElse(null);

    if (appelOffre == null) {
        throw new EntityNotFoundException("Appel d'offre non trouvée");
    }

    User fournisseur = userRepository.findUserById(fournisseurId);

    if (fournisseur == null) {
        throw new EntityNotFoundException("Fournisseur non trouvé");
    }

    appelOffre.setFournisseur((Fournisseur) fournisseur);
    appelOffreRepo.save(appelOffre);

    return appelOffre;
}










}



