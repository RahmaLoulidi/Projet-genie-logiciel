import { BesoinService } from './../besoin/besoin.service';
import { Ordinateur } from './../ressource/ordinateur';
import { Component, OnInit } from '@angular/core';
import { Besoin } from '../besoin/besoin';
import { BesoinLivrableService } from './besoin-livrable.service';
import { HttpErrorResponse } from '@angular/common/http';
import { NgForm } from '@angular/forms';
import { Imprimante } from '../ressource/imprimante';

@Component({
  selector: 'app-besoin-livrable',
  templateUrl: './besoin-livrable.component.html',
  styleUrls: ['./besoin-livrable.component.css']
})
export class BesoinLivrableComponent implements OnInit  {
  showOrdianteurInputs: string = 'Ordinateur';
  besoins: Besoin[] = [];
  besoinsel: Besoin = new Besoin;
  ordinateur: Ordinateur = new Ordinateur;
  imprimante: Imprimante = new Imprimante;
  besoinsOrdinateurL: Besoin[] = [];
  besoinsImprimanteL: Besoin[] = [];
  codeOrd:string='';
  codeImp:string='';


  constructor(private besoinLivrableService: BesoinLivrableService, private besoinService: BesoinService) { }

  ngOnInit() {
   this.besoinLivrableService.getOrdinateursLivrés().subscribe({
      next: (response: Besoin[]) => {
        this.besoinsOrdinateurL = response;
      },
      error: (error: HttpErrorResponse) => {
            alert(error.message);
      }
    });
    this.besoinLivrableService.getImprimantesLivrées().subscribe({
      next: (response: Besoin[]) => {
        this.besoinsImprimanteL = response;
      },
      error: (error: HttpErrorResponse) => {
            alert(error.message);
      }
    });
  }

  /*findBesoinsOrdinateurL(): void {
    this.besoinLivrableService.getOrdinateursLivrés().subscribe({
      next: (response: Besoin[]) => {
        this.besoinsOrdinateurL = response;
      },
      error: (error: HttpErrorResponse) => {
            alert(error.message);
      }
    });
  }*/
  generateCodeOrdinateur() {
    const min = 1;
    const max = 9999;
    const randomNumber = Math.floor(Math.random() * (max - min + 1)) + min;
    const codeBarreOInput = document.getElementById('codeBarreO') as HTMLInputElement;
    this.codeOrd = 'Ordinateur' + randomNumber.toString().padStart(4, '0');
    this.ordinateur.codeBarre=this.codeOrd;
  
  }

  generateCodeImprimante() {
    const min = 1;
    const max = 9999;
    const randomNumber = Math.floor(Math.random() * (max - min + 1)) + min;
    this.codeImp= 'Imprimante' + randomNumber.toString().padStart(4, '0');
    this.imprimante.codeBarre = this.codeImp;
  }

  findBesoinsImprimanteL(): void {
    this.besoinService.getImprimantesLivrées().subscribe({
      next: (response: Besoin[]) => {
        this.besoinsImprimanteL = response;
      },
      error: (error: HttpErrorResponse) => {
            alert(error.message);
      }
    });
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
    if (mode === 'add') {
      button.setAttribute('data-target', '#addRessourceModal');
    }
    if (mode === 'addI') {
      button.setAttribute('data-target', '#addImprimanteModal');
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


  public selectionnerBesoin(besoin: Besoin){
    this.besoinsel=besoin;
    this.onOpenModal(besoin, 'add');
    console.log(this.besoinsel);



  }

  public selectionnerBesoinI(besoin: Besoin){
    this.besoinsel=besoin;
    this.onOpenModal(besoin, 'addI');
    console.log(this.besoinsel);



  }



  public onAddRessource(addRessource: NgForm): void {
    this.ordinateur.cpu=this.besoinsel.cpu;
    this.ordinateur.ecran=this.besoinsel.ecran;
    this.ordinateur.marque=this.besoinsel.marque;
    this.ordinateur.ram=this.besoinsel.ram;
    this.ordinateur.disqueDur=this.besoinsel.disqueDur;

    this.besoinLivrableService.addRessource(this.ordinateur).subscribe({
      next: (response: Ordinateur) => {
        console.log(response);
        addRessource.reset();
      },
      error: (error: HttpErrorResponse) => {
            alert(error.message);
            addRessource.reset();
      }
    });
  }

  public onAddImprimante(addImprimante: NgForm): void {
    
    this.imprimante.resolution=this.besoinsel.resolution;
    this.imprimante.vitesse=this.besoinsel.vitesse;
    this.imprimante.marque=this.besoinsel.marque;
  

    this.besoinLivrableService.addImprimante(this.imprimante).subscribe({
      next: (response: Imprimante) => {
        console.log(response);
        addImprimante.reset();
      },
      error: (error: HttpErrorResponse) => {
            alert(error.message);
            addImprimante.reset();
      }
    });
  }



}
