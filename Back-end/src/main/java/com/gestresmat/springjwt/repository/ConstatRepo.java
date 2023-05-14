package com.gestresmat.springjwt.repository;

import com.gestresmat.springjwt.models.Constat;
import com.gestresmat.springjwt.models.Enseignant;
import com.gestresmat.springjwt.models.Panne;
import com.gestresmat.springjwt.models.TechnicienMaintenance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ConstatRepo extends JpaRepository<Constat,Long> {

    Constat findConstatById(Long id);

    void deleteConstatById(Long id);

    List<Constat> findConstatsByTechnicienMaintenance(TechnicienMaintenance technicienMaintenance);

    List<Constat> findAllByOrderByIdDesc();

    @Query("SELECT c FROM Constat c WHERE c.send=1  order by c.id DESC ")
    List<Constat> findConstatSend();


}
