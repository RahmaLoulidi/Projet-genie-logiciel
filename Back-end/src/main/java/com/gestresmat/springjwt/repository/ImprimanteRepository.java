package com.gestresmat.springjwt.repository;

import com.gestresmat.springjwt.models.ERole;
import com.gestresmat.springjwt.models.Imprimante;
import com.gestresmat.springjwt.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ImprimanteRepository extends JpaRepository<Imprimante, Long> {
    Imprimante findImprimanteById(Long id);

    @Query("select i from Imprimante i, Affectation a where i.id=a.ressource.id and a.enseignant.id=:id")
    List<Imprimante> findImprimantesByEns(Long id);
}
