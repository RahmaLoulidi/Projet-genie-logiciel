package com.gestresmat.springjwt.repository;

import com.gestresmat.springjwt.models.ListeNoir;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ListeNoirRepository extends JpaRepository<ListeNoir, Long> {

}
