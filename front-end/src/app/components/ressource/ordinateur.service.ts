import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Ordinateur } from './ordinateur';
@Injectable({
  providedIn: 'root'
})
export class OrdinateurService {
  private baseUrl = 'http://localhost:8080/ressource/ordinateur';
  constructor(private http: HttpClient) { }
  getOrdinateur(): Observable<Ordinateur[]> {
    return this.http.get<Ordinateur[]>(`${this.baseUrl}/all`);
  }
  addOrdinateur(ordinateur: Ordinateur): Observable<Ordinateur>{
    return this.http.post<Ordinateur>(`${this.baseUrl}/add`,ordinateur);
  }
  updateOrdinateur(id: number, dureeGarantie: string, dateLivraison: Date): Observable<Ordinateur> {
    const url = `${this.baseUrl}/update/${id}`;
    return this.http.put<Ordinateur>(url, {dureeGarantie, dateLivraison});
  }
  public deleteOrdinateur(ordinateurId: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/delete/${ordinateurId}`);
  }


  getOrdinateurEns(id:number): Observable<Ordinateur[]> {
    return this.http.get<Ordinateur[]>(`${this.baseUrl}/all/${id}`);
  }

  getOrdinateurById(id: number): Observable<Ordinateur> {
    return this.http.get<Ordinateur>(`${this.baseUrl}/find/${id}`); // Remplacer l'URL avec l'URL de votre backend Spring Boot
  }




}
