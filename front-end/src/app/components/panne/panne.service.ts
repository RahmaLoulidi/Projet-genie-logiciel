import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Panne } from '../../models/panne';
import { User } from '../../models/user';

@Injectable({
  providedIn: 'root'
})
export class PanneService {
  private baseUrl = 'http://localhost:8080/panne';
  user : User = new User();
  

  constructor(private http: HttpClient) { }
  
  getPanneById(id: number): Observable<Panne> {
    return this.http.get<Panne>(`${this.baseUrl}/find/${id}`); // Remplacer l'URL avec l'URL de votre backend Spring Boot
  }
  getPannes(): Observable<Panne[]> {
    return this.http.get<Panne[]>(`${this.baseUrl}/all`);
}
getPannesEns(id: number): Observable<Panne[]> {
  return this.http.get<Panne[]>(`${this.baseUrl}/all/${id}`);
}
addPanne(panne: Panne,userId:number): Observable<any> {
  return this.http.post(`${this.baseUrl}/save/${userId}`, panne);
}
 updatePanne(id:number ,panne: Panne): Observable<any> {
  return this.http.put<Panne>(`${this.baseUrl}/update/${id}`,panne);
 }
deletePanne(id: number) {
  return this.http.delete(`${this.baseUrl}/delete/${id}`);
}

isPrinter(id:number):Observable<boolean>{
   return this.http.get<boolean>(`${this.baseUrl}/isprinter/${id}`);
}

}