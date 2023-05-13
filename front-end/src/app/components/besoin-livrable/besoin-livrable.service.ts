import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Besoin } from '../besoin/besoin';
import { Observable } from 'rxjs';
import { Ordinateur } from '../ressource/ordinateur';
import { Imprimante } from '../ressource/imprimante';

@Injectable({
  providedIn: 'root'
})
export class BesoinLivrableService {

  private url = 'http://localhost:8080';
  constructor(private http: HttpClient) { }

 /* getAllBesoins(): Observable<Besoin[]> {
    return this.http.get<Besoin[]>(`${this.url}/livraison/all`);
  }*/
  public getOrdinateursLivrés(): Observable<Besoin[]> {
    return this.http.get<Besoin[]>(`${this.url}/livraison/Ordinateurs`);
  }

  public getImprimantesLivrées(): Observable<Besoin[]> {
    return this.http.get<Besoin[]>(`${this.url}/livraison/Imprimantes`);
  }


  public addRessource(ordinateur: Ordinateur): Observable<Ordinateur> {
    return this.http.post<Ordinateur>(`${this.url}/ressource/ordinateur/add`, ordinateur);
  }

  public addImprimante(imprimante: Imprimante): Observable<Imprimante> {
    return this.http.post<Imprimante>(`${this.url}/ressource/imprimante/add`, imprimante);
  }
}
