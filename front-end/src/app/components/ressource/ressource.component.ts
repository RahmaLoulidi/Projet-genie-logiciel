import { Component , OnInit } from '@angular/core';
import { Ordinateur } from './ordinateur';
import { OrdinateurService } from './ordinateur.service';
import { Imprimante } from './imprimante';
import { ImprimanteService } from './imprimante.service';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { User } from 'src/app/models/user';

@Component({
  selector: 'app-ressource',
  templateUrl: './ressource.component.html',
  styleUrls: ['./ressource.component.css']
})


export class RessourceComponent implements OnInit{
  user : User = new User;
  ordinateurs: Ordinateur[]=[];
  imprimantes: Imprimante[]=[];
  ordinateur: Ordinateur = new Ordinateur;
  editOrdinateur: Ordinateur = new Ordinateur;
  deleteOrdinateur: Ordinateur = new Ordinateur;
  imprimante: Imprimante=new Imprimante;
  editImprimante:Imprimante = new Imprimante;
  deleteImprimante:Imprimante = new Imprimante;
  constructor(private ordinateurService: OrdinateurService,private http: HttpClient,private imprimanteService: ImprimanteService,private router: Router){}
  
  ngOnInit() {
    this.user=this.loadUser();
    this.ordinateurService.getOrdinateur().subscribe((ordinateurs: Ordinateur[]) => {
      this.ordinateurs = ordinateurs;
    });
    this.imprimanteService.getImprimante().subscribe((imprimantes: Imprimante[]) => {
      this.imprimantes = imprimantes;
    });   
  }
  loadUser(){
    return JSON.parse(localStorage.getItem('user') || '{}');
  }
  onSubmitO() {

    this.http.post<Ordinateur>('http://localhost:8080/ressource/ordinateur/add', this.ordinateur).subscribe(savedOrdinateur => {
      console.log('Ordinateur ajoutee avec succes:', savedOrdinateur);
      this.router.navigate(['/ressource',savedOrdinateur.codeBarre]);
    });


  }

  onSubmitI(){
    this.http.post<Imprimante>('http://localhost:8080/ressource/imprimante/add', this.imprimante).subscribe(savedImprimante => {
      console.log('Imprimante ajoutee avec succes:', savedImprimante);
      this.router.navigate(['/ressource',savedImprimante.codeBarre]);});
  }


  public onOpenModal(ordinateur: Ordinateur, mode: string): void {
    const container = document.getElementById('main-container');
    const button = document.createElement('button');
    button.type = 'button';
    button.style.display = 'none';
    button.setAttribute('data-toggle', 'modal');
    if (mode === 'edit') {
      this.editOrdinateur = ordinateur;
      button.setAttribute('data-target', '#updateOrdinateurModal');
    }
    if (mode === 'delete') {
      this.deleteOrdinateur = ordinateur;
      button.setAttribute('data-target', '#deleteOrdinateurModal');
    }
    container?.appendChild(button);
    button.click();
  }

  public onOpenModalI(imprimante: Imprimante, mode: string): void {
    const container = document.getElementById('main-container');
    const button = document.createElement('button');
    button.type = 'button';
    button.style.display = 'none';
    button.setAttribute('data-toggle', 'modal');
    if (mode === 'edit') {
      this.editImprimante = imprimante;
      button.setAttribute('data-target', '#updateImprimanteModal');
    }
    if (mode === 'delete') {
      this.deleteImprimante = imprimante;
      button.setAttribute('data-target', '#deleteImprimanteModal');
    }
    container?.appendChild(button);
    button.click();
  }
  


  onUpdateOrdinateur() {
    const url = `http://localhost:8080/ressource/ordinateur/update/${this.editOrdinateur.id}`;
    console.log(this.ordinateur.id);
    const body = {
      dureeGarantie: this.editOrdinateur.dureeGarantie,
      dateLivraison: this.editOrdinateur.dateLivraison
    };
    this.http.put(url, body).subscribe(response => {
      console.log('Ordinateur mis à jour avec succès', response);
      window.location.reload();
      this.ngOnInit();
    }, error => {
      console.error('Erreur lors de la mise à jour de l\'ordinateur', error);
    });
  }

  onUpdateImprimante() {
    const url = `http://localhost:8080/ressource/imprimante/update/${this.editImprimante.id}`;
    const body = {
      dureeGarantie: this.editImprimante.dureeGarantie,
      dateLivraison: this.editImprimante.dateLivraison
    };
    this.http.put(url, body).subscribe(response => {
      console.log('Imprimante mise à jour avec succès', response);
      window.location.reload();
      this.ngOnInit();
    }, error => {
      console.error('Erreur lors de la mise à jour de l\'imprimante', error);
    });
  }

  public onDeleteOrdinateur(ordinateurId: number): void {
    this.ordinateurService.deleteOrdinateur(ordinateurId).subscribe(
      (response: void) => {
        console.log(response);
        window.location.reload();
        this.ngOnInit();
       
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public onDeleteImprimante(imprimanteId: number): void {
    this.imprimanteService.deleteImprimante(imprimanteId).subscribe(
      (response: void) => {
        console.log(response);
        window.location.reload();
        this.ngOnInit();
       
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  

 
  
}