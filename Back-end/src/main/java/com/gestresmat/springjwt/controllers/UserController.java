package com.gestresmat.springjwt.controllers;


import com.gestresmat.springjwt.models.Message;
import com.gestresmat.springjwt.models.TechnicienMaintenance;
import com.gestresmat.springjwt.models.User;
import com.gestresmat.springjwt.security.services.TechnicienService;
import com.gestresmat.springjwt.security.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;

    }
    @GetMapping("/find/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id){
        User user =userService.findUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> users= userService.findAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

}
