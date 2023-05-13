import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { User } from '../../models/user';
import { Observable } from 'rxjs';
import { HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class UtlisateursService {

  private baseUrl = 'http://localhost:8080/registration/users';

  constructor(private http: HttpClient , route : ActivatedRoute) { }


  getUsers(): Observable<User[]> {
    const headers = new HttpHeaders().set('Origin', 'http://localhost: 4200');
    return this.http.get<User[]>(this.baseUrl,{ headers });
    


  }
  

  
}
