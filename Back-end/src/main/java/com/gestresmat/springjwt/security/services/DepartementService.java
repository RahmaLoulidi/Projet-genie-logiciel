package com.gestresmat.springjwt.security.services;

import com.gestresmat.springjwt.models.Departement;
import com.gestresmat.springjwt.repository.DepartementRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DepartementService {

    private final DepartementRepo departementRepo;

    @Autowired
    public DepartementService(DepartementRepo departementRepo) {
        this.departementRepo =  departementRepo;
    }


    public Departement addDepartement(Departement departement){
        return departementRepo.save(departement);
    }

    public List<Departement> findAllDepartements(){
        return departementRepo.findAll();
    }
}
