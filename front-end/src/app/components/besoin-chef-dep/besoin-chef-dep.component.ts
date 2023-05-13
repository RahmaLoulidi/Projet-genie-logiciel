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
  selector: 'app-besoin-chef-dep',
  templateUrl: './besoin-chef-dep.component.html',
  styleUrls: ['./besoin-chef-dep.component.css']
})
export class BesoinChefDepComponent implements OnInit{
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
    this.besoinService.getOrdinateursDep(this.user_id).subscribe((ordinateurs:Besoin[])=> {
      this.ordinateurs=ordinateurs;
    })
    this.besoinService.getOrdinateursDep(this.user_id).subscribe((imprimantes:Besoin[])=> {
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
  }

  public onOpenModal(besoin: Besoin, mode: string): void {

    const container = document.getElementById('main-container');
    const button = document.createElement('button');
    button.type = 'button';
    button.style.display = 'none';
    button.setAttribute('data-toggle', 'modal');
    if (mode === 'o') {
      this.ordinateur=besoin;
      button.setAttribute('data-target', '#updateOModal');
    }
    if (mode === 'i') {
      this.imprimante=besoin;
      button.setAttribute('data-target', '#updateIModal');
    }
    /*if (mode === 'edit') {
      this.editEmployee = employee;
      button.setAttribute('data-target', '#updateEmployeeModal');
    }
    if (mode === 'delete') {
      this.deleteEmployee = employee;
      button.setAttribute('data-target', '#deleteEmployeeModal');
    }*/
    container?.appendChild(button);
  
    button.click();
  }
  
  onUpdateO(updatedO:NgForm){
    this.besoinService.updateOrdi(this.ordinateur.id,this.ordinateur).subscribe(saved => {
        console.log('modifié avec succès :', saved);
        updatedO.reset();
        this.ngOnInit();
    });
    
    
  
  }
  onUpdateI(updatedI:NgForm){
    this.besoinService.updateOrdi(this.imprimante.id,this.imprimante).subscribe(saved => {
        console.log('modifié avec succès :', saved);
        updatedI.reset();
        this.ngOnInit();
    });
    
 
  
  }
  onSend(id: number){
    this.besoinService.envoieBesoin(id,this.besoin).subscribe(saved => {
        console.log('send avec succès :', saved);
        this.ngOnInit();
    });
   

  
  }

}
