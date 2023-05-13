import { Component } from '@angular/core';
import { Ressource } from './ressource';
import { AffectationService } from './affectation.service';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Besoin } from '../besoin/besoin';
import { Ordinateur } from '../ressource/ordinateur';
import { Imprimante } from '../ressource/imprimante';
import { Affectation } from './affectation';
import { NgForm } from '@angular/forms';
import { BesoinDto } from './BesoinDto';
import { AffectationDto } from './affectationDto';
import { User } from 'src/app/models/user';

@Component({
  selector: 'app-affectation',
  templateUrl: './affectation.component.html',
  styleUrls: ['./affectation.component.css']
})
export class AffectationComponent {
  user : User = new User;
  public affectations: AffectationDto[] = [];
  public ressources: Ressource[] = [];
  public ordinateurs: Ordinateur[] = [];
  public imprimantes: Imprimante[] = [];
  public besoins: BesoinDto[] = [];
  besoinsel: BesoinDto= new BesoinDto;
  affectation: Affectation = new Affectation;
  enseignant_id: number = 0;
  ressource_id: number = 0;
  editAffectationDto:AffectationDto = new AffectationDto;
  deleteAffectationDto:AffectationDto = new AffectationDto;
  affectationDto:AffectationDto = new AffectationDto;

  
  constructor(private http: HttpClient,private affectationService: AffectationService) {
  }

  ngOnInit(){
    this.user=this.loadUser();
    this.getOrdinateurs();
    this.getImprimantes();
    this.getBesoins();
    this.getRessources();
    this.getAffectations();


  }

  loadUser(){
    return JSON.parse(localStorage.getItem('user') || '{}');
  }


  public getAffectations(): void{
    this.affectationService.getAllAffectations().subscribe(
      (data: AffectationDto[]) => {
        this.affectations = data;
      },
      (error: any) => console.log(error)
    );

  }

  public getOrdinateurs(): void{
    this.affectationService.getOrdinateurs().subscribe(
      (data: Ordinateur[]) => {
        this.ordinateurs = data;
      },
      (error: any) => console.log(error)
    );

  }

  public getImprimantes(): void{
    this.affectationService.getImprimantes().subscribe(
      (data: Imprimante[]) => {
        this.imprimantes = data;
      },
      (error: any) => console.log(error)
    );

  }
  public getRessources(): void{
    this.affectationService.getRessources().subscribe(
      (data: Ressource[]) => {
        this.ressources = data;
      },
      (error: any) => console.log(error)
    );

  }


  public getBesoins(): void{
    this.affectationService.getAllBesoins().subscribe(
      (data: BesoinDto[]) => {
        this.besoins = data;
        console.log(this.besoins[0].enseignantId);
      },
      (error: any) => console.log(error)
    );

  }

  public onOpenModalD(affectationDto: AffectationDto, mode: string): void {
    const container = document.getElementById('main-container');
    const button = document.createElement('button');
    button.type = 'button';
    button.style.display = 'none';
    button.setAttribute('data-toggle', 'modal');
    if (mode === 'edit') {
      this.editAffectationDto = affectationDto;
      button.setAttribute('data-target', '#updateAffectationDtoModal');
    }
    if (mode === 'delete') {
      this.deleteAffectationDto = affectationDto;
      button.setAttribute('data-target', '#deleteOrdinateurModal');
    }
    container?.appendChild(button);
    button.click();
  }


  public onOpenModal(mode: string): void {
    const container = document.getElementById('main-container');
    const button = document.createElement('button');
    button.type = 'button';
    button.style.display = 'none';
    button.setAttribute('data-toggle', 'modal');
    if (mode === 'add') {
      button.setAttribute('data-target', '#addAffectationModal');
    }
    container?.appendChild(button);
  
    button.click();
  }


  public selectionnerBesoin(besoin: BesoinDto){
    this.besoinsel=besoin;
    this.onOpenModal('add');
    console.log("Besoin sélectionné:",this.besoinsel.enseignantId);



  }


  onAddAffectation(addAffectationForm: NgForm): void {
    this.affectation.enseignantId=this.besoinsel.enseignantId;
  
    this.affectationService.addAffectation(this.affectation)
      .subscribe((data: any) => {
        console.log(data);
        // vider le formulaire
        this.affectation = new Affectation();
        addAffectationForm.reset();
        window.location.reload();
        
      }, (error: any) => console.log(error));

      const selectedRessourceId = this.affectation.ressourceId;
      console.log('Selected ressource ID:', selectedRessourceId);
  }

    onUpdateAffectation(id: number, affectationDTOO: AffectationDto): void {
    this.affectationService.updateAffectation(id, affectationDTOO)
      .subscribe(
        (response) => {
          console.log(response);
          // faire quelque chose avec la réponse
        },
        (error) => {
          console.log(error);
          // gérer l'erreur
        }
      );
    }




  
  

}



