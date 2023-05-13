import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AppelOffre } from './appel-offre';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AppelOffreService {

  private baseUrl = 'http://localhost:8080/appelOffre';

  constructor(private http: HttpClient) { }

  getAppelOffres(): Observable<AppelOffre[]> {
    return this.http.get<AppelOffre[]>(`${this.baseUrl}/all`);
  }

  creerAppelOffre(appelOffre: AppelOffre): Observable<AppelOffre> {
    return this.http.post<AppelOffre>(`${this.baseUrl}/creer`, appelOffre);
  }
  
  getAppelOffreById(id: number): Observable<AppelOffre> {
    return this.http.get<AppelOffre>(`${this.baseUrl}/${id}`);
  }
  

  modifierAppelOffre(id: number, dateDebut: string, dateFin: string): Observable<AppelOffre> {
    const url = `${this.baseUrl}/modifier/${id}`;
    const body = {
      dateDebut,
      dateFin
    };
    return this.http.put<AppelOffre>(url, body);
  }
  
  
}
