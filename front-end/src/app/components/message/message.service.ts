import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class MessageService {
  private baseUrl = 'http://localhost:8080/message';

  constructor(private http: HttpClient) { }

  getMessages(): Observable<any> {
    return this.http.get(`${this.baseUrl}/all`);

  }

  setSeen(id: number): Observable<any> {
    return this.http.post(`${this.baseUrl}/seen/${id}`, {})
  }

  addMessage(message: { subject :string , contenu : string , emetteur : number , recepteurs : number[] | any }): Observable<any> {
    let idR = message.recepteurs
    let idE = message.emetteur
    console.log('servce', {subject: message.subject , content : message.contenu})

    return this.http.post(`${this.baseUrl}/creer/${idR}/${idE}`, {subject: message.subject , content : message.contenu});
  }



}
