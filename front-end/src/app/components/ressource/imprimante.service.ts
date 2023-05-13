import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Imprimante } from './imprimante';

@Injectable({
  providedIn: 'root'
})
export class ImprimanteService {
  private baseUrl = 'http://localhost:8080/ressource/imprimante';
  constructor(private http: HttpClient) { }
  getImprimante(): Observable<Imprimante[]> {
    return this.http.get<Imprimante[]>(`${this.baseUrl}/all`);
  }
  addImprimante(imprimante:Imprimante) : Observable<Imprimante> {
    return this.http.post<Imprimante>(`${this.baseUrl}/add`,imprimante);
  } 
  updateImprimante(imprimante:Imprimante) : Observable<Imprimante> {
    return this.http.put<Imprimante>(`${this.baseUrl}/update`,imprimante);
  } 
  public deleteImprimante(imprimanteId: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/delete/${imprimanteId}`);
  }




  getImprimanteEns(id:number): Observable<Imprimante[]> {
    return this.http.get<Imprimante[]>(`${this.baseUrl}/all/${id}`);
  }

  getImprimanteById(id: number): Observable<Imprimante> {
    return this.http.get<Imprimante>(`${this.baseUrl}/find/${id}`); // Remplacer l'URL avec l'URL de votre backend Spring Boot
  }



}