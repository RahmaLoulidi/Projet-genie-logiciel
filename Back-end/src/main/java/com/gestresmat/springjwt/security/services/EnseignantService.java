package com.gestresmat.springjwt.security.services;

import com.gestresmat.springjwt.models.Enseignant;
import com.gestresmat.springjwt.repository.EnseignantRepository;
import org.springframework.stereotype.Service;

@Service
public class EnseignantService {
    private final EnseignantRepository enseignantRepository;

    public EnseignantService(EnseignantRepository enseignantRepository) {
        this.enseignantRepository = enseignantRepository;
    }

    public Enseignant findEnseignantById(Long id){
        return enseignantRepository.findEnseignantById(id);
    }
}
