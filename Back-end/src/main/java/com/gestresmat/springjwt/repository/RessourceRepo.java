package com.gestresmat.springjwt.repository;


import com.gestresmat.springjwt.models.Enseignant;
import com.gestresmat.springjwt.models.Ressource;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RessourceRepo extends JpaRepository<Ressource, Long> {
    Ressource findRessourceById(Long id);

    Ressource findByCodeBarre(String codeBarre);
}
