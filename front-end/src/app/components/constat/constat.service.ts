import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Cons, Observable } from 'rxjs';
import { Constat } from '../../models/constat';
import { Panne } from '../../models/panne';
import { User } from 'src/app/models/user';

@Injectable({
  providedIn: 'root'
})
export class ConstatService {
  private baseUrl = 'http://localhost:8080/constat';
  user :User=new User();

  constructor(private http: HttpClient) { }


  getConstats(): Observable<Constat[]> {
    return this.http.get<Constat[]>(`${this.baseUrl}/all`);

}
getConstatsTech(id: number): Observable<Constat[]> {
  return this.http.get<Constat[]>(`${this.baseUrl}/all/${id}`);

}
getConstatsResp(): Observable<Constat[]> {
  return this.http.get<Constat[]>(`${this.baseUrl}/allsend`);

}

getConstatById(id: number): Observable<Constat> {
  return this.http.get<Constat>(`${this.baseUrl}/find/${id}`); // Remplacer l'URL avec l'URL de votre backend Spring Boot
}


updateConstat(id:number ,constat: Constat): Observable<any> {
  return this.http.put<Constat>(`${this.baseUrl}/update/${id}`,constat);
 }

 sendConstat(id:number ,constat: Constat): Observable<any> {
  return this.http.put<Constat>(`${this.baseUrl}/send/${id}`,constat);
 }

 reparer(id:number ,constat: Constat): Observable<any> {
  return this.http.put<Constat>(`${this.baseUrl}/reparer/${id}`,constat);
 }

 renvoyer(id:number ,constat: Constat): Observable<any> {
  return this.http.put<Constat>(`${this.baseUrl}/renvoyer/${id}`,constat);
 }

deleteConstat(id: number) {
  return this.http.delete(`${this.baseUrl}/delete/${id}`);
}
  setUser(user:User){
    console.log("from constat service setter");
    this.user=user;
    console.log(this.user);
  }
  getUser(){
    return this.user;
  }

}