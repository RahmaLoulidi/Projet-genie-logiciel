package com.gestresmat.springjwt.repository;

import com.gestresmat.springjwt.models.Affectation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AffectationRepository extends JpaRepository<Affectation, Long> {

}
