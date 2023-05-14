package com.gestresmat.springjwt.repository;

import com.gestresmat.springjwt.models.Imprimante;
import com.gestresmat.springjwt.models.Ordinateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdinateurRepository extends JpaRepository<Ordinateur, Long> {

    @Query("select o from Ordinateur o, Affectation a where o.id=a.ressource.id and a.enseignant.id=:id")
    List<Ordinateur> findOrdinateurByEns(Long id);

    Ordinateur findOrdinateurById(Long id);
}
