import { Component, OnInit } from '@angular/core';
import { Besoin } from '../besoin/besoin';
import { BesoinService } from '../besoin/besoin.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AuthService } from 'src/app/services/auth.service';
import { User } from 'src/app/models/user';
import { Ordinateur } from '../ressource/ordinateur';
import { Imprimante } from '../ressource/imprimante';
import { NgForm } from '@angular/forms';


@Component({
  selector: 'app-besoin-aff',
  templateUrl: './besoin-aff.component.html',
  styleUrls: ['./besoin-aff.component.css']
})
export class BesoinAffComponent implements OnInit{
  showOrdianteurInputs: string = 'Ordinateur';
  user:User=new User();
  user_id:number=0;
  public besoins: Besoin[] = [];
  ordinateurs: Besoin[] = [];
  imprimantes: Besoin[] = [];
  ordinateur: Besoin = new Besoin;
  imprimante: Besoin = new Besoin;
  besoin: Besoin = new Besoin;
  username: String ='';

  

  constructor(private http: HttpClient,private besoinService: BesoinService, private authService: AuthService) {
    
  }
  ngOnInit(): void {
    this.user=this.loadUser();
    this.user_id=this.user.id;
    this.besoinService.getOrdinateurs().subscribe((ordinateurs:Besoin[])=> {
      this.ordinateurs=ordinateurs;
      this.ordinateurs.forEach((ordinateur) => {
       this.besoinService.getEns(ordinateur.id,).subscribe((result) => {

         ordinateur.username = JSON.parse(JSON.stringify(result)).username;


      });
      });
    })
    this.besoinService.getImprimantes().subscribe((imprimantes:Besoin[])=> {
      this.imprimantes=imprimantes;
      this.imprimantes.forEach((imprimante) => {
        this.besoinService.getEns(imprimante.id,).subscribe((result) => {
 
          imprimante.username = JSON.parse(JSON.stringify(result)).username;
 
 
       });
       });
    })
    this.besoinService.getBesoins().subscribe((besoins:Besoin[])=> {
      this.besoins=besoins;
    })
    
  }

  loadUser(){

    return JSON.parse(localStorage.getItem('user') || '{}');
  }

  onTypeChange($event: Event) {
    console.log(($event.target as HTMLInputElement).value);
    this.showOrdianteurInputs = ($event.target as HTMLInputElement).value;
  }

  
  
  
    
 
  

  onSend(id: number){
    this.besoinService.envoieBesoin(id,this.besoin).subscribe(saved => {
        console.log('send avec succès :', saved);
        this.ngOnInit();
    });
   

  
  }

}

