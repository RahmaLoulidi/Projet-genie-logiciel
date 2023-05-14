package com.gestresmat.springjwt.repository;

import com.gestresmat.springjwt.models.Enseignant;
import com.gestresmat.springjwt.models.Message;
import com.gestresmat.springjwt.models.Panne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface PanneRepo extends JpaRepository<Panne,Long> {
    Optional<Panne> findPanneById(Long id);

    void deletePanneById(Long id);

    List<Panne> findPannesByEnseignant(Enseignant enseignant);

    public List<Panne> findAllByOrderByIdDesc();

    @Query("select p from Panne p WHERE  NOT EXISTS (SELECT c FROM p.constat c) order by p.id desc ")
    public List<Panne> findAllTech();

}
