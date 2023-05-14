package com.gestresmat.springjwt.repository;

import com.gestresmat.springjwt.models.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BesoinRepository extends JpaRepository<Besoin, Long> {



    List<Besoin> findByTypeAndLivrableIsTrue(String type);

    ///////////////////////////////////// AHLAM /////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////

    List<Besoin> findByTypeAndAppelOffreIsNull(String type);
    @Query("SELECT b FROM Besoin b WHERE  b.envoie=1 and b.type=:type and  NOT EXISTS (SELECT a FROM b.appelOffre a)  order by b.id desc ")
    List<Besoin> findByTypeAndAppelOffreIsNullAndEnvoie(String type);

    List<Besoin> findByAppelOffreIdAndType(Long id, String type);

    List<Besoin> findByAppelOffre(AppelOffre appelOffre);


    ////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////Rahma///////////////////////////////////////////////////////////
    @Query("SELECT b FROM Besoin b WHERE b.type=:type and  NOT EXISTS (SELECT a FROM b.appelOffre a) order by b.id desc ")
    List<Besoin> findAllByTypeOrderByIdDesc(String type);


    public Besoin getBesoinById(Long id);

    @Query("SELECT b FROM Besoin b WHERE b.envoie=1 and b.type=:type order by b.id DESC ")
    List<Besoin> findBesoinEnvoie(String type);

    @Query("SELECT b FROM Besoin b WHERE b.type=:type and b.enseignant.departement=:dep order by b.id DESC ")
    public List<Besoin> findBesoinByDepAndType(Departement dep,String type);

}
