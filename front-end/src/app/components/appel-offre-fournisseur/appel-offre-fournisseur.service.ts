import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AppelOffre } from '../appel-offre/appel-offre';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AppelOffreFournisseurService {

  private baseUrl = 'http://localhost:8080/appelOffre';

  constructor(private http: HttpClient) { }

    /////////////// PROPOSITIONS //////////////
    getAppelOffreByFournisseurIdNull () : Observable<AppelOffre[]> {
      return this.http.get<AppelOffre[]>(`${this.baseUrl}/fournisseurIdNull`);
    }
}
