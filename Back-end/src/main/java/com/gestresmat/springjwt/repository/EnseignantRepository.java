package com.gestresmat.springjwt.repository;

import com.gestresmat.springjwt.models.Enseignant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnseignantRepository extends JpaRepository<Enseignant, Long> {
    Enseignant findEnseignantById(Long id);
}
