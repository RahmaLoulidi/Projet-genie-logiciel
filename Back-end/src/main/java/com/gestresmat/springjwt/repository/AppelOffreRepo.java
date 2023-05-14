package com.gestresmat.springjwt.repository;

import com.gestresmat.springjwt.models.AppelOffre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppelOffreRepo extends JpaRepository<AppelOffre,Long> {
    List<AppelOffre> findByFournisseurIdIsNull();

    List<AppelOffre> findAllByOrderByIdDesc();
}
