import { Injectable } from '@angular/core';
import { Besoin } from './besoin';
import { Observable } from 'rxjs';
import { User } from '../sign-up/user';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class BesoinService {

  private url = 'http://localhost:8080/besoin';

  constructor(private http: HttpClient) { }

  public getBesoins(): Observable<Besoin[]> {
    return this.http.get<Besoin[]>(`${this.url}/all`);
  }

  public addBesoin(besoin: Besoin,user: User): Observable<Besoin> {
    return this.http.post<Besoin>(`${this.url}/add`, besoin);
  }

  public updateBesoin(besoin: Besoin): Observable<Besoin> {
    return this.http.put<Besoin>(`${this.url}/update`, besoin);
  }

  public deleteBesoin(besoinId: number): Observable<void> {
    return this.http.delete<void>(`${this.url}/delete/${besoinId}`);
  }

  public getOrdinateursLivrés(): Observable<Besoin[]> {
    return this.http.get<Besoin[]>(`${this.url}/livraison/Ordinateurs`);
  }

  public getImprimantesLivrées(): Observable<Besoin[]> {
    return this.http.get<Besoin[]>(`${this.url}/livraison/Imprimantes`);
  }

  
  getBesoinsNonAffectes(): Observable<Besoin[]> {
    return this.http.get<Besoin[]>(`${this.url}/nonAffectes`);
  }


  findOrdinateursNonAffectes() {
    return this.http.get<Besoin[]>(`${this.url}/ordinateurs-non-affectes`);
  }
  
  findImprimantesNonAffectes() {
    return this.http.get<Besoin[]>(`${this.url}/imprimantes-non-affectes`);
  }

  findOrdinateursAffectes(id: number) {
    return this.http.get<Besoin[]>(`${this.url}/ordinateurs-affectes/${id}`);
  }

  findImprimantesAffectes(id: number) {
    return this.http.get<Besoin[]>(`${this.url}/imprimantes-affectes/${id}`);
  }
  
  /////////Rahma////////////


  public getOrdinateurs(): Observable<Besoin[]> {
    return this.http.get<Besoin[]>(`${this.url}/ordinateurs`);
  }

  public getImprimantes(): Observable<Besoin[]> {
    return this.http.get<Besoin[]>(`${this.url}/imprimantes`);
  }
  public getOrdinateursDep(id:number): Observable<Besoin[]> {
    return this.http.get<Besoin[]>(`${this.url}/ordinateurs/${id}`);
  }

  public getImprimantesDep(id:number): Observable<Besoin[]> {
    return this.http.get<Besoin[]>(`${this.url}/imprimantes/${id}`);
  }

  updateOrdi(id:number ,ordinateur: Besoin): Observable<any> {
    const headers = new HttpHeaders().set('Content-Type', 'application/json');

    return this.http.put<Besoin>(`${this.url}/updateO/${id}`,ordinateur);
   }

   updateImp(id:number ,imprimante: Besoin): Observable<any> {
    return this.http.put<Besoin>(`${this.url}/updateI/${id}`,imprimante);
   }

   envoieBesoin(id:number ,besoin: Besoin): Observable<any> {
    return this.http.put<Besoin>(`${this.url}/send/${id}`,besoin);
   }

   getEns(id:number):Observable<string>{
    return this.http.get<string>(`${this.url}/besoinEns/${id}`);
 }

 public getOrdiSend(): Observable<Besoin[]> {
  return this.http.get<Besoin[]>(`${this.url}/allsendO`);
}

public getImpSend(): Observable<Besoin[]> {
  return this.http.get<Besoin[]>(`${this.url}/allsendI`);
}
  
}
