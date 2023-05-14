package com.gestresmat.springjwt.repository;

import com.gestresmat.springjwt.models.TechnicienMaintenance;
import com.gestresmat.springjwt.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TechnicienRepo extends JpaRepository<TechnicienMaintenance, Long> {
    TechnicienMaintenance findTechnicienMaintenanceById(Long id);
}
