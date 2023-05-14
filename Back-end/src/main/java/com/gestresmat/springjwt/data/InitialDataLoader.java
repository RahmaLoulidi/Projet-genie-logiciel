package com.gestresmat.springjwt.data;

import com.gestresmat.springjwt.models.ERole;
import com.gestresmat.springjwt.models.Role;
import com.gestresmat.springjwt.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class InitialDataLoader implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {
        // check first if there is any role in the database
        if (roleRepository.count() > 0) {
            return;
        }
        roleRepository.save(new Role(ERole.USER));
        roleRepository.save(new Role(ERole.ENSEIGNANT));
        roleRepository.save(new Role(ERole.RESPONSABLE));
        roleRepository.save(new Role(ERole.CHEF_DEPARTEMENT));
        roleRepository.save(new Role(ERole.TECHNICIEN));
        roleRepository.save(new Role(ERole.FOURNISSEUR));

    }
}
