import { Injectable } from '@angular/core';
import { Proposition } from './proposition-responsable';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class PropositionResponsableService {

  private url = 'http://localhost:8080/proposition';
  constructor(private http: HttpClient) { }

  public getPropositions(): Observable<Proposition[]> {
    return this.http.get<Proposition[]>(`${this.url}/all`);
  }

  public accepterProposition(id: number) {
    return this.http.put(`${this.url}/${id}/accepter`, {});
  }

  public rejeterProposition(id: number) {
    return this.http.put(`${this.url}/${id}/rejeter`, {});
  }
  public eliminerProposition(id: number) {
    return this.http.put(`${this.url}/${id}/eliminer`, {});
  }
}


