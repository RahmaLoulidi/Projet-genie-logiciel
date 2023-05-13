import { Injectable } from '@angular/core';
import {User } from './user';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, observable } from 'rxjs';
import { JsonPipe } from '@angular/common';
import { Router , NavigationExtras} from '@angular/router';



@Injectable({
  providedIn: 'root'
})

export class UserService {

  user : User = new User();
  url1= 'http://localhost:8080/registration';

  url = 'http://localhost:8080/registration/signup';

  constructor(private httpClient : HttpClient) { }
  addUser(newUser:User | any):Observable<User>{

    console.log(newUser);
    const headers = new HttpHeaders().set('Origin', 'http://localhost: 4200');
    return this.httpClient.post<User>(this.url,newUser,{ headers });
    alert('Registread successfully');
}

addEnseignant(newUser:User | any , id:number):Observable<User>{

  console.log(newUser);
  const headers = new HttpHeaders().set('Origin', 'http://localhost: 4200');
  return this.httpClient.post<User>(`${this.url1}/addEnseignant/${id}`, newUser);
  alert('Registread successfully');
}

// registerUser(signUpRequest :User){
//   alert(JSON.stringify(signUpRequest) );
//      this.httpClient.post<User>('http://localhost:8080/regisrtation/signup',JSON.stringify(signUpRequest));


//     }

   }

