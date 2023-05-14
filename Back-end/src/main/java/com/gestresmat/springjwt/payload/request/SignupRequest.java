package com.gestresmat.springjwt.payload.request;

import java.util.Set;
import javax.validation.constraints.*;
 
public class SignupRequest {

    @Size(min = 3, max = 20)
    private String username;
 

    @Size(max = 50)
    @Email
    private String email;
    
    private Set<String> role;
    

    @Size(min = 6, max = 40)
    private String password;

    @Size(max = 40)
    private String nomSociete;

    @Size(max = 40)
    private String gerant;

    @Size(max = 40)
    private String lieu ;

    //boolean

    // intial value is 0
    @Min(0)
    @Max(1)
    private boolean chefDepartement ;


    public boolean getChefDepartement() {
        return chefDepartement;
    }

    public void setChefDepartement(boolean chefDepartement) {
        this.chefDepartement = chefDepartement;
    }

    @Size(max = 40)
    private String nomDepartement;

    public String getNomDepartement() {
        return nomDepartement;
    }

    public void setNomDepartement(String nomDepartement) {
        this.nomDepartement = nomDepartement;
    }

    public String getNomSociete() {
        return nomSociete;
    }

    public void setNomSociete(String nomSociete) {
        this.nomSociete = nomSociete;
    }

    public String getGerant() {
        return gerant;
    }

    public void setGerant(String gerant) {
        this.gerant = gerant;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public String getUsername() {
        return username;
    }
 
    public void setUsername(String username) {
        this.username = username;
    }
 
    public String getEmail() {
        return email;
    }
 
    public void setEmail(String email) {
        this.email = email;
    }
 
    public String getPassword() {
        return password;
    }
 
    public void setPassword(String password) {
        this.password = password;
    }
    
    public Set<String> getRole() {
      return this.role;
    }
    
    public void setRole(Set<String> role) {
      this.role = role;
    }
}
