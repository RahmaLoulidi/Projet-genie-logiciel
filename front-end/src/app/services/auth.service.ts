import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../models/user';
import { HttpHeaders } from '@angular/common/http';





@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private authenticatedUser: any;
  private u : any;
  private id : number ;
  private username : string;
  private nomSociete : string;
  private role : string;
  private token : string;
  private chefDepartement! :boolean;
  //private isLogged : boolean = false;

  constructor(private http : HttpClient) {
    this.id = 0 ;
    this.username = "";
    this.nomSociete = "";
    this.role = "";
    this.token = "";
  }

  getChefDepartement(){
    return this.chefDepartement;
  }

  setUsername(username : string){
    this.username = username;
  }
  getUsername(){
    return this.username;
  }
  setRole(role : string){
    this.role = role;
  }
  getRole(){
    return this.role;
  }
  setId(id : number ){
    this.id = id;
  }
  getId(){
    return this.id;
  }
  setToken(token : string){
    this.token = token;
  }
  getToken(){
    return this.token;
  }
  setNomSociete(nomSociete : string){
    this.nomSociete = nomSociete;
  }
  getNomSociete(){
    return this.nomSociete;
  }
  


  setLogged(isLogged : string){ localStorage.setItem("isLogged",isLogged);}
  getLogged(){ return localStorage.getItem("isLogged");}

  setRoleLocal(role : string){ localStorage.setItem("role",role);}
  getRoleLocal(){ return localStorage.getItem("role");}


  isLogged(){
    // Check if the authenticated user object or token is present
    return this.getLogged()=='true';
  }



  public login(user : User):Observable<User>{

     this.u=this.http.post<User>("http://localhost:8080/registration/signin",user);


     return this.u;
  }

  url = 'http://localhost:8080/registration/signup';
  public register(user : User):Observable<User>{


    const headers = new HttpHeaders().set('Origin', 'http://localhost:4200');
     return this.http.post<User>(this.url,user,{ headers });

  }

  signOut():Observable<any>{
    const token = this.getToken();
    const httpOption ={
      headers :{
        "Authorization" : "Bearer "+token,
      }
    }

    return this.http.get<any>("http://localhost:8080/registration/logout",httpOption);

  }

}
