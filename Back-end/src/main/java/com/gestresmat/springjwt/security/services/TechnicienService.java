package com.gestresmat.springjwt.security.services;

import com.gestresmat.springjwt.exceptions.PanneNotFoundException;
import com.gestresmat.springjwt.exceptions.TechnicienNotFoundException;
import com.gestresmat.springjwt.exceptions.UserNotFoundException;
import com.gestresmat.springjwt.models.Panne;
import com.gestresmat.springjwt.models.TechnicienMaintenance;
import com.gestresmat.springjwt.models.User;
import com.gestresmat.springjwt.repository.TechnicienRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TechnicienService {
    final private TechnicienRepo technicienRepo;

    public TechnicienService(TechnicienRepo technicienRepo) {
        this.technicienRepo = technicienRepo;
    }

    public TechnicienMaintenance findTechnicienMaintenanceById(Long id) {
        return technicienRepo.findTechnicienMaintenanceById(id);
    }

    public List<TechnicienMaintenance> findAllTechnicienMaintenance(){
        return technicienRepo.findAll();
    }
}
