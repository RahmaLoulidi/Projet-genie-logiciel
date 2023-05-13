import { Component, OnInit } from '@angular/core';
import { Ordinateur } from '../ressource/ordinateur';
import { Imprimante } from '../ressource/imprimante';
import { User } from 'src/app/models/user';
import { HttpClient } from '@angular/common/http';
import { OrdinateurService } from '../ressource/ordinateur.service';
import { AuthService } from 'src/app/services/auth.service';
import { ImprimanteService } from '../ressource/imprimante.service';
import { PanneService } from '../panne/panne.service';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { Panne } from 'src/app/models/panne';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-affectation-ens',
  templateUrl: './affectation-ens.component.html',
  styleUrls: ['./affectation-ens.component.css']
})
export class AffectationEnsComponent implements OnInit{
  
  showOrdianteurInputs: string = 'Ordinateur';
  user:User=new User();
  user_id:number=0;
  ordinateurs: Ordinateur[] = [];
  imprimantes: Imprimante[] = [];
  ordinateur:Ordinateur= new Ordinateur;
  ordi_id:number=0;
  imprimante:Imprimante= new Imprimante;
  imp_id:number=0;
  panne:Panne= new Panne;


  constructor(private http: HttpClient,private ordinateurService: OrdinateurService, private imprimanteService: ImprimanteService ,private authService: AuthService,panneService:PanneService,private router: Router) {
    
  }
  ngOnInit(): void {
    this.user=this.loadUser();
    this.user_id=this.user.id;
    this.ordinateurService.getOrdinateurEns(this.user_id).subscribe((ordinateurs:Ordinateur[])=> {
      this.ordinateurs=ordinateurs;
    })
    this.imprimanteService.getImprimanteEns(this.user_id).subscribe((imprimantes:Imprimante[])=> {
      this.imprimantes=imprimantes;
    })
  }

  loadUser(){

    return JSON.parse(localStorage.getItem('user') || '{}');
  }

  onTypeChange($event: Event) {
    console.log(($event.target as HTMLInputElement).value);
    this.showOrdianteurInputs = ($event.target as HTMLInputElement).value;
  }

  public onOpenModal( idRess: number,mode:String): void {
    
    const container = document.getElementById('main-container');
    const button = document.createElement('button');
    button.type = 'button';
    button.style.display = 'none';
    button.setAttribute('data-toggle', 'modal');
    
  
    if (mode === 'panneImp') {
      button.setAttribute('data-target', '#addPanneIModal');
      this.imp_id = idRess;
    this.imprimanteService.getImprimanteById(this.imp_id).subscribe(imprimante => {
      this.imprimante = imprimante; 
    });
    }
    if (mode === 'panneOrd') {
      button.setAttribute('data-target', '#addPanneOModal');
      this.ordi_id = idRess;
    this.ordinateurService.getOrdinateurById(this.ordi_id).subscribe(ordinateur => {
      this.ordinateur = ordinateur; 
    });
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

  onSubmitOrd(addPanne:NgForm) {
    this.http.post(`http://localhost:8080/panne/saveRes/${this.user.id}/${this.ordi_id}`, this.panne).subscribe(savedPanne=> {
      console.log('Panne créé avec succès :', savedPanne);

      window.location.reload();
      this.ngOnInit(); // Actualiser la page
      
    
    
  });
  
  }
  
  public cancel(): void {
    console.log("Cancel button clicked!");
    const container = document.getElementById('main-container');
    const modalBackdrop = document.getElementsByClassName('modal-backdrop')[0];
    if (container) {
      const button = document.createElement('button');
      button.type = 'button';
      button.style.display = 'none';
      button.setAttribute('data-dismiss', 'modal');
      container.appendChild(button);
      button.click();
      modalBackdrop.remove(); // Supprimer le bouton modal de la page
    }
  }
  onSubmitImp(addPanne:NgForm) {
  this.http.post(`http://localhost:8080/panne/saveRes/${this.user.id}/${this.imp_id}`, this.panne).subscribe(savedPanne=> {
    console.log('Panne créé avec succès :', savedPanne);
    this.cancel();
    this.router.navigate(['/panneEns'], { queryParams: { message: 'La panne a été ajoutée avec succès.' } });

    
  
});

  
  }

}
