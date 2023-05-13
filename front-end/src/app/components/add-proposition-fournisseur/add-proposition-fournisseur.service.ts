import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Besoin } from '../besoin/besoin';
import { BesoinPropose } from './besoin-propose';

@Injectable({
  providedIn: 'root'
})
export class AddPropositionFournisseurService {

  private baseUrl = 'http://localhost:8080/proposition/appelOffre';

  constructor(private http: HttpClient) { }


  // getBesoins(appelOffreId: number): Observable<Besoin[]> {
  //   const url = `http://localhost:8080/proposition/appelOffre/${appelOffreId}`;
  //   return this.http.get<Besoin[]>(url);
  // }

  getBesoins(appelOffreId: number): Observable<Besoin[]> {
    const url = `${this.baseUrl}/${appelOffreId}`;
    return this.http.get<Besoin[]>(url);
  }

  getBesoinsOrdinateurs(besoins: Besoin[]): Besoin[] {
    return besoins.filter(b => b.type === 'Ordinateur');
  }

  getBesoinsImprimantes(besoins: Besoin[]): Besoin[] {
    return besoins.filter(b => b.type === 'Imprimante');
  }
  

  ajouterProposition(appelOffreId: number, besoinsPropose: BesoinPropose[], fournisseurId : number): Observable<any> {
    const url = `http://localhost:8080/proposition/appelOffre/${appelOffreId}/${fournisseurId}`;
    return this.http.post(url, besoinsPropose);
  }
  
}
