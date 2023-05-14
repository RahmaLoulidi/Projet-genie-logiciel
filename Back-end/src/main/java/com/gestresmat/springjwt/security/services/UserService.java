package com.gestresmat.springjwt.security.services;

import com.gestresmat.springjwt.models.Departement;
import com.gestresmat.springjwt.models.User;
import com.gestresmat.springjwt.repository.DepartementRepo;
import com.gestresmat.springjwt.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    private final DepartementRepo departementRepo;

    public UserService(UserRepository userRepository, DepartementRepo departementRepo) {
        this.userRepository = userRepository;
        this.departementRepo = departementRepo;
    }

    public User findUserById(Long id) {
        return userRepository.findUserById(id);
    }

    public List<User> findAllUsers(){


        return userRepository.findAllByOrderByIdDesc();

    }
    public Departement findDepartementById(Long id){ return departementRepo.findDepartementById(id);}

    public Optional<User> findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
