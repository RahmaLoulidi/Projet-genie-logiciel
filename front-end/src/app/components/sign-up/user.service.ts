import { Injectable } from '@angular/core';
import {User } from './user';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, observable } from 'rxjs';
import { JsonPipe } from '@angular/common';



@Injectable({
  providedIn: 'root'
})

export class UserService {

  url = 'http://localhost:8080/registration/signup';

  constructor(private httpClient : HttpClient) { }
  addUser(newUser:User):Observable<User>{

    console.log(newUser);
    const headers = new HttpHeaders().set('Origin', 'http://localhost: 4200');
    return this.httpClient.post<User>(this.url,newUser,{ headers });
    alert('Registread successfully');
}

// registerUser(signUpRequest :User){
//   alert(JSON.stringify(signUpRequest) );
//      this.httpClient.post<User>('http://localhost:8080/regisrtation/signup',JSON.stringify(signUpRequest));


//     }

   }

