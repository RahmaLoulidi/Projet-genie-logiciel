package com.gestresmat.springjwt.repository;

import com.gestresmat.springjwt.models.AppelOffre;
import com.gestresmat.springjwt.models.Fournisseur;
import org.hibernate.sql.Select;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FournisseurRepo extends JpaRepository<Fournisseur,Long> {


    Fournisseur findFournisseurById(Long id);
}
