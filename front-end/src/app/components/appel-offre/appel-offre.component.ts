import { Component, OnInit } from '@angular/core';
import { AppelOffre } from './appel-offre';
import { HttpClient } from '@angular/common/http';
import { AppelOffreService } from './appel-offre.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Besoin } from '../besoin/besoin';
import { BesoinService } from '../besoin/besoin.service';
import { User } from 'src/app/models/user';




@Component({
  selector: 'app-appel-offre',
  templateUrl: './appel-offre.component.html',
  styleUrls: ['./appel-offre.component.css']
})
export class AppelOffreComponent implements OnInit{
  appelOffre: AppelOffre = new AppelOffre([], new Date(), new Date());
  appelOffres: AppelOffre[] = [];

  besoinsNonAffectes : Besoin[] = [];
  selectedBesoinIds: number[] = [];

  besoinsOrdinateur : Besoin[] = [];
  besoinsImprimante : Besoin[] = [];

  dateDebut: Date = new Date();
  dateFin: Date = new Date();

  user : User = new User;

  messageAdd: string = '';
  messageEdit: string = '';
  messageDelete: string = '';



  constructor(
    private http: HttpClient,
    private appelOffeService: AppelOffreService,
    private router:Router,
    private besoinService: BesoinService,
    private route: ActivatedRoute,


    ) {}

  ngOnInit() {
    this.user=this.loadUser();
    this.getAllAppelOffres();
    this.getBesoinsNonAffectes();
    this.findBesoinsOrdinateur();
    this.findBesoinsImprimante();


    //////// MESSAGE DE CONFIRMATION /////////
    this.route.queryParams.subscribe(params => {
      const message = params['message'];
      if (message) {
        if (message.startsWith("L'appel d'offre a été ajoutée")) {
          this.messageAdd = message;
        } else if (message.startsWith("L'appel d'offre a été modifiée")) {
          this.messageEdit = message;
        } else if (message.startsWith("L'appel d'offre a été supprimée")) {
          this.messageDelete = message;
        }
        setTimeout(() => {
          this.messageAdd = '';
          this.messageEdit = '';
          this.messageDelete = '';
        }, 3000);
      }
    });
  }

  loadUser(){
    return JSON.parse(localStorage.getItem('user') || '{}');
  }
  

  getAllAppelOffres() {
    this.appelOffeService.getAppelOffres().subscribe((appelOffres: AppelOffre[]) => {
      this.appelOffres = appelOffres;
    });
  }

  onSubmit() {
    this.http.post<AppelOffre>('http://localhost:8080/appelOffre/add', this.appelOffre).subscribe(savedAppelOffre => {
      console.log("Appel d'offre créé avec succès :", savedAppelOffre);
      
    });  
  }

////////////////////////////////////// BESOINS ///////////////////////////////
  getBesoinsNonAffectes(): void {
    this.besoinService.getBesoinsNonAffectes().subscribe(besoins => {
      this.besoinsNonAffectes = besoins;
    });
  }


  onSelectBesoin(id: number): void { 
    const index = this.selectedBesoinIds.indexOf(id);
    if (index > -1) { 
      this.selectedBesoinIds.splice(index, 1);
    } else {
      this.selectedBesoinIds.push(id);
    }
}
  

  creerAppelOffre() : void {
      const besoinsIds: any[] = this.selectedBesoinIds.map(id => ({ id: id }));
      const nouvelAppelOffre: AppelOffre = {
        besoins : besoinsIds,
        dateDebut: this.dateDebut,
        dateFin: this.dateFin
        };
        this.appelOffeService.creerAppelOffre(nouvelAppelOffre).subscribe(() => {
          // Faire quelque chose après la création de l'appel d'offre, par exemple :
          this.findBesoinsOrdinateur();
          this.findBesoinsImprimante();
          this.ngOnInit();
          this.selectedBesoinIds = [];
          this.dateDebut = new Date();
          this.dateFin = new Date();
          this.messageEdit = "L'appel d'offre a été modifiée avec succès."; // afficher le message de confirmation
          setTimeout(() => {
          this.messageEdit = '';
        }, 3000); // masquer le message après 3 secondes
        window.scroll({
          top: 0,
          left: 0,
          behavior: 'smooth'
        });
        
        });
    }


  findBesoinsOrdinateur() : void {
    this.besoinService.findOrdinateursNonAffectes()
    .subscribe(besoins => this.besoinsOrdinateur = besoins);
  }
 

  findBesoinsImprimante() : void {
    this.besoinService.findImprimantesNonAffectes()
    .subscribe(besoins => this.besoinsImprimante = besoins);
  }
   







}
