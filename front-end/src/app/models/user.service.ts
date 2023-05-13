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

  url = 'http://localhost:8080/registration/signin';

  constructor(private httpClient : HttpClient , private router :Router ) { }
  addUser(newUser:User){

    console.log(newUser);
    const headers = new HttpHeaders().set('Origin', 'http://localhost: 4200');
     this.httpClient.post<User>(this.url,newUser,{ headers }).subscribe((data: any) => {


       this.user = data;
       return this.router.navigate(['/besoin'],{ state: { u : this.user } });
      });



  }


  getAllUsers():Observable<User[]>{
    return this.httpClient.get<User[]>('http://localhost:8080/user/all');
  }



}

// registerUser(signUpRequest :User){
//   alert(JSON.stringify(signUpRequest) );
//      this.httpClient.post<User>('http://localhost:8080/regisrtation/signup',JSON.stringify(signUpRequest));


//     }



