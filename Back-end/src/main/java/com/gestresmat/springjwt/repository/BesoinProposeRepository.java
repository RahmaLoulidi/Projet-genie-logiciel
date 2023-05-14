package com.gestresmat.springjwt.repository;

import com.gestresmat.springjwt.models.Besoin;
import com.gestresmat.springjwt.models.BesoinPropose;
import com.gestresmat.springjwt.models.Proposition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BesoinProposeRepository extends JpaRepository<BesoinPropose,Long> {

    BesoinPropose findByPropositionAndBesoin(Proposition proposition, Besoin besoin);

    void deleteByPropositionId(Long propositionId);

    List<BesoinPropose> findByPropositionId(Long propositionId);



}
