import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AppelOffre } from '../appel-offre/appel-offre';
import { Observable } from 'rxjs';
import { Proposition } from './proposition';

@Injectable({
  providedIn: 'root'
})
export class PropositionFournisseurService {

  private baseUrl = 'http://localhost:8080/proposition';
  constructor(private http: HttpClient) { }


  getAllPropositionsByFournisseur(fournisseurId: number): Observable<Proposition[]> {
    return this.http.get<Proposition[]>(`${this.baseUrl}/fournisseur/${fournisseurId}`);
  }


}
