package com.gestresmat.springjwt.repository;

import com.gestresmat.springjwt.models.Departement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartementRepo extends JpaRepository<Departement,Long> {
    Departement findDepartementById(Long id);
}
