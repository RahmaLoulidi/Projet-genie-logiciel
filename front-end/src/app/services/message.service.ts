import { Injectable } from '@angular/core';
import { Message } from '../models/message'; 
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../models/user';

@Injectable({
  providedIn: 'root'
})
export class MessageService {
  private baseUrl = 'http://localhost:8080/message';
  user : User = new User();
  

  constructor(private http: HttpClient) { }
  getMessagesByRecepId(id: number): Observable<Message[]> {
    return this.http.get<Message[]>(`${this.baseUrl}/find/${id}`); // Remplacer l'URL avec l'URL de votre backend Spring Boot
  }
}
