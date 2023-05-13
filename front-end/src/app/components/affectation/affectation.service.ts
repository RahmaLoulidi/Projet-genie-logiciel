import { Injectable } from '@angular/core';
import { Besoin } from '../besoin/besoin';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Ressource } from './ressource';
import { Affectation } from './affectation';
import { Ordinateur } from '../ressource/ordinateur';
import { Imprimante } from '../ressource/imprimante';
import { BesoinDto } from './BesoinDto';
import { AffectationDto } from './affectationDto';

@Injectable({
  providedIn: 'root'
})
export class AffectationService {

  private url = 'http://localhost:8080/affectation';

  constructor(private http: HttpClient) { }

  
  getAllAffectations(): Observable<AffectationDto[]> {
    return this.http.get<AffectationDto[]>(`${this.url}/all`);
  }

  getAllBesoins(): Observable<BesoinDto[]> {
    return this.http.get<BesoinDto[]>(`${this.url}/besoins`);
  }

  public getRessources(): Observable<Ressource[]>{
    return this.http.get<Ressource[]>(`${this.url}/ressources`);
   
  }
  public getOrdinateurs(): Observable<Ordinateur[]>{
    return this.http.get<Ordinateur[]>(`${this.url}/ordinateurs`);
   
  }

  public getImprimantes(): Observable<Imprimante[]>{
    return this.http.get<Imprimante[]>(`${this.url}/imprimantes`);
   
  }


  public addAffectation(affectation: Affectation): Observable<Affectation> {
    return this.http.post<Affectation>(`${this.url}/add`, affectation);
  }

  public updateAffectation(id: number, affectationDTOO: AffectationDto): Observable<any> {
    return this.http.put<any>(`${this.url}/update/${id}`, affectationDTOO);
}


}
