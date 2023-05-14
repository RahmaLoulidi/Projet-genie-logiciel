package com.gestresmat.springjwt.controllers;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;



import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;


import com.gestresmat.springjwt.models.*;
import com.gestresmat.springjwt.security.services.UserService;
import org.hibernate.type.TrueFalseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.MediaType;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.gestresmat.springjwt.payload.request.LoginRequest;
import com.gestresmat.springjwt.payload.request.SignupRequest;
import com.gestresmat.springjwt.payload.response.JwtResponse;
import com.gestresmat.springjwt.payload.response.MessageResponse;
import com.gestresmat.springjwt.repository.RoleRepository;
import com.gestresmat.springjwt.repository.UserRepository;
import com.gestresmat.springjwt.security.jwt.JwtUtils;
import com.gestresmat.springjwt.security.services.UserDetailsImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;


@RestController
@RequestMapping("/registration")
@CrossOrigin(origins = "*")
public class AuthController {
    private final UserService userService;
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;


    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String showRegistrationForm() {
        return "login";
    }


    @PostMapping("/signin")
    @CrossOrigin(origins = "*")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        String role = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining());
        Long departement = userDetails.getDepartementID();
         if(role.equals("FOURNISSEUR")){
             User user= userService.findUserByUsername(loginRequest.getUsername()).get();
            Fournisseur fournisseur = ((Fournisseur) user);
            if(fournisseur.getListeNoir()!=null){return new ResponseEntity<>(HttpStatus.NOT_FOUND);}else {

                return ResponseEntity.ok(new JwtResponse(jwt,
                        userDetails.getId(),
                        userDetails.getUsername(),
                        userDetails.getEmail(),
                        role,
                        departement
                ));}
            }
        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                role,
                departement
        ));
    }

    @PostMapping("/signup" )
    @CrossOrigin(origins = "*")
    public ResponseEntity<?> registerUser(@RequestBody SignupRequest signUpRequest) {
        /*if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

*/
        System.out.println(signUpRequest.getRole());
        // request example
        // {
        //    "username": "admin",
        //    "email": "admin@gmail",
        //    "password": "admin",
        //    "role": ["admin"]

        User user = new User(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));
        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role fournisseurRole = roleRepository.findByName(ERole.FOURNISSEUR)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(fournisseurRole);
            System.out.println(signUpRequest.toString());
            user  = new Fournisseur(signUpRequest.getNomSociete(),encoder.encode(signUpRequest.getPassword()),signUpRequest.getNomSociete());
        } else {
            for (String role : strRoles) {
                System.out.println(role);
                switch (role) {
                    case "enseignant":
                        Role enseignantRole= roleRepository.findByName(ERole.ENSEIGNANT).orElseThrow(() -> new RuntimeException("Error: Role is not found."));;

                        roles.add(enseignantRole);
                        user = new Enseignant(signUpRequest.getUsername(),
                                signUpRequest.getEmail(),
                                encoder.encode(signUpRequest.getPassword()));
                        signUpRequest.setChefDepartement(FALSE);

                        ((Enseignant) user).setChefDepartement(signUpRequest.getChefDepartement());

                        break;
                    case "responsable":
                        Role respRole = roleRepository.findByName(ERole.RESPONSABLE)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(respRole);
                        user = new Responsable(signUpRequest.getUsername(),
                                signUpRequest.getEmail(),
                                encoder.encode(signUpRequest.getPassword()));

                        break;

                    case "chefDepartement":
                        Role chefDepartementRole = roleRepository.findByName(ERole.CHEF_DEPARTEMENT)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(chefDepartementRole);
                        user = (Enseignant) new Enseignant(signUpRequest.getUsername(),
                                signUpRequest.getEmail(),
                                encoder.encode(signUpRequest.getPassword()));
                        Departement departement = new Departement();
                        departement.setNomDepartement(signUpRequest.getNomDepartement());
                        signUpRequest.setChefDepartement(TRUE);

                        ((Enseignant) user).setChefDepartement(signUpRequest.getChefDepartement());

                        break;
                    case "technicien":
                        Role technicienRole = roleRepository.findByName(ERole.TECHNICIEN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(technicienRole);
                        user = new TechnicienMaintenance(signUpRequest.getUsername(),
                                signUpRequest.getEmail(),
                                encoder.encode(signUpRequest.getPassword()));
                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.FOURNISSEUR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }

            }
        }
        user.setRoles(roles);
        userRepository.save(user);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    @GetMapping("/users")
    @CrossOrigin(origins = "*")
    public ResponseEntity<List<User>> getAllUsers(){

        List<User> users= userService.findAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping("/addEnseignant/{id}" )
    @CrossOrigin(origins = "*")
    public ResponseEntity<?> registerUser(@RequestBody SignupRequest signUpRequest ,  @PathVariable("id") Long id) {
        /*if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

*/
        System.out.println(signUpRequest.getRole());
        System.out.println(signUpRequest);
        // request example
        // {
        //    "username": "admin",
        //    "email": "admin@gmail",
        //    "password": "admin",
        //    "role": ["admin"]

        User user = new User(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));
        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role fournisseurRole = roleRepository.findByName(ERole.FOURNISSEUR)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(fournisseurRole);
            System.out.println(signUpRequest.toString());
            user  = new Fournisseur(signUpRequest.getNomSociete(),encoder.encode(signUpRequest.getPassword()),signUpRequest.getNomSociete());
        } else {
            for (String role : strRoles) {
                System.out.println(role);
                switch (role) {
                    case "enseignant":
                        Role enseignantRole= roleRepository.findByName(ERole.ENSEIGNANT).orElseThrow(() -> new RuntimeException("Error: Role is not found."));;
                        Departement dep = userService.findDepartementById(id);
                        roles.add(enseignantRole);
                        user = new Enseignant(signUpRequest.getUsername(),
                                signUpRequest.getEmail(),
                                encoder.encode(signUpRequest.getPassword()));
                        ((Enseignant) user).setChefDepartement(signUpRequest.getChefDepartement() );
                        ((Enseignant) user).setDepartement(dep);

                        break;
                    case "responsable":
                        Role respRole = roleRepository.findByName(ERole.RESPONSABLE)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(respRole);
                        user = new Responsable(signUpRequest.getUsername(),
                                signUpRequest.getEmail(),
                                encoder.encode(signUpRequest.getPassword()));

                        break;

                    case "chefDepartement":
                        Role chefDepartementRole = roleRepository.findByName(ERole.CHEF_DEPARTEMENT)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(chefDepartementRole);
                        user = (Enseignant) new Enseignant(signUpRequest.getUsername(),
                                signUpRequest.getEmail(),
                                encoder.encode(signUpRequest.getPassword()));
                        Departement departement = new Departement();
                        departement.setNomDepartement(signUpRequest.getNomDepartement());
                        signUpRequest.setChefDepartement(TRUE);

                        ((Enseignant) user).setChefDepartement(signUpRequest.getChefDepartement());

                        break;
                    case "technicien":
                        Role technicienRole = roleRepository.findByName(ERole.TECHNICIEN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(technicienRole);
                        user = new TechnicienMaintenance(signUpRequest.getUsername(),
                                signUpRequest.getEmail(),
                                encoder.encode(signUpRequest.getPassword()));
                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.FOURNISSEUR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }

            }
        }
        user.setRoles(roles);
        userRepository.save(user);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

}