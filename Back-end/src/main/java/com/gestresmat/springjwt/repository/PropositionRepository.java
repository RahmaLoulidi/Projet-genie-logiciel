package com.gestresmat.springjwt.repository;

import com.gestresmat.springjwt.models.Proposition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropositionRepository extends JpaRepository<Proposition,Long> {
    List<Proposition> findByAppelOffreId(Long appelOffreId);

    List<Proposition> findByFournisseurId(Long fournisseurId);


    List<Proposition> findByAppelOffreIdAndIdNot(Long id, Long id1);


}
