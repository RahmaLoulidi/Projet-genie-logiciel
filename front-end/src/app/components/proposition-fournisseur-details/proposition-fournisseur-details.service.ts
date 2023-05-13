import { Injectable } from '@angular/core';
import { Proposition } from '../proposition-fournisseur/proposition';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { BesoinProposeDetails } from './besoin-propose-details';
import { BesoinPropose } from '../add-proposition-fournisseur/besoin-propose';



@Injectable({
  providedIn: 'root'
})
export class PropositionFournisseurDetailsService {

  private baseUrl = 'http://localhost:8080/proposition/fournisseur';

  constructor(private http: HttpClient) { }


  getPropositionById(id: number): Observable<Proposition> {
    return this.http.get<Proposition>(`${this.baseUrl}/details/${id}`);

  }

  getBesoinProposeByIds(ids : number[]): Observable<BesoinProposeDetails[]> {
    const params = { ids: ids.toString() };
    const url = `${this.baseUrl}/besoinpropose/ids`;
    return this.http.get<BesoinProposeDetails[]>(url, { params });
  }


  deleteProposition(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${id}/supprimer`);
  }

  // Récupération des besoins proposés d'une proposition par id
  getBesoinsProposes(propositionId: number): Observable<BesoinProposeDetails[]> {
    return this.http.get<BesoinProposeDetails[]>(`${this.baseUrl}/${propositionId}/besoinsProposes`);
  }

  // Modification des besoins proposés d'une proposition
  modifierBesoinsProposes(propositionId: number, besoinsProposes: BesoinPropose[], fournisseurId : number): Observable<any> {
    return this.http.put(`${this.baseUrl}/${propositionId}/modifier/${fournisseurId}`, besoinsProposes);
  }
  

  }


