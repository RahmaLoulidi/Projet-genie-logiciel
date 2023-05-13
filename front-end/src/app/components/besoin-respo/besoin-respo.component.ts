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
  selector: 'app-besoin-respo',
  templateUrl: './besoin-respo.component.html',
  styleUrls: ['./besoin-respo.component.css']
})
export class BesoinRespoComponent implements OnInit{
  showOrdianteurInputs: string = 'Ordinateur';
  user:User=new User();
  user_id:number=0;
  public besoins: Besoin[] = [];
  ordinateurs: Besoin[] = [];
  imprimantes: Besoin[] = [];
  ordinateur: Besoin = new Besoin;
  imprimante: Besoin = new Besoin;
  besoin: Besoin = new Besoin;

  

  constructor(private http: HttpClient,private besoinService: BesoinService, private authService: AuthService) {
    
  }
  ngOnInit(): void {
    this.user=this.loadUser();
    this.user_id=this.user.id;
    this.besoinService.getOrdiSend().subscribe((ordinateurs:Besoin[])=> {
      this.ordinateurs=ordinateurs;
    })
    this.besoinService.getImpSend().subscribe((imprimantes:Besoin[])=> {
      this.imprimantes=imprimantes;
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
    this.ngOnInit()
  }


  
  

}

