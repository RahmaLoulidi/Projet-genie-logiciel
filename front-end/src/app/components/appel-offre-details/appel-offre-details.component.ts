import { Component, OnInit, ViewChild } from '@angular/core';
import { AppelOffre } from '../appel-offre/appel-offre';
import { ActivatedRoute, Router } from '@angular/router';
import { AppelOffreService } from '../appel-offre/appel-offre.service';
import { BesoinService } from '../besoin/besoin.service';
import { Besoin } from '../besoin/besoin';
import { NgForm } from '@angular/forms';
import { formatDate } from '@angular/common';
import { User } from 'src/app/models/user';


@Component({
  selector: 'app-appel-offre-details',
  templateUrl: './appel-offre-details.component.html',
  styleUrls: ['./appel-offre-details.component.css']
})
export class AppelOffreDetailsComponent implements OnInit {
  appelOffre: AppelOffre = new AppelOffre([], new Date(), new Date());;

  dateDebutA = new Date(this.appelOffre.dateDebut);
  dateFinA = new Date(this.appelOffre.dateDebut);


  user : User = new User;

  ordinateursAffectes: Besoin[] = [];
  imprimantesAffectes: Besoin[] = [];

  @ViewChild('content') content: any;
  showModal: boolean = false;


  constructor(private route: ActivatedRoute,
    private appelOffreService: AppelOffreService,
    private besoinService: BesoinService,
    private router: Router

  ) { }

  ngOnInit() {
    this.user=this.loadUser();
    this.getAppelOffreById();
    this.findOrdinateursAffectes();
    this.findImprimantesAffectes();
  }

  loadUser(){
    return JSON.parse(localStorage.getItem('user') || '{}');
  }

  getAppelOffreById() {
    const id = +this.route.snapshot.paramMap.get('id')!;
    if (id) {
      this.appelOffreService.getAppelOffreById(id).subscribe( appelOffre => {
        this.appelOffre = appelOffre;
      })
    } else {
      console.log("id non trouve", id)
    }
  }



  findOrdinateursAffectes() {
    const id = +this.route.snapshot.paramMap.get('id')!;
    if (id) {
      console.log(id)
      this.besoinService.findOrdinateursAffectes(id).subscribe( besoins => {
        this.ordinateursAffectes = besoins;
      })
    } else {
      console.log("id non trouve", id)
    }
  }

  findImprimantesAffectes() {
    const id = +this.route.snapshot.paramMap.get('id')!;
    if (id) {
      console.log(id)
      this.besoinService.findImprimantesAffectes(id).subscribe( besoins => {
        this.imprimantesAffectes = besoins;
      })
    } else {
      console.log("id non trouve", id)
    }
  }
  
  ////////////////////////////////////////////////////////////////////////////////////////
  //////////////////////////// MODAL ////////////////////////////////////////////////////
  //////////////////////////////////////////////////////////////////////////////////////


  
  public onOpenModal(mode: string): void {
    console.log("Open Modal");
    console.log(this.appelOffre.dateDebut);
    const container = document.getElementById('main-container');
    if (container) {
      const button = document.createElement('button');
      button.type = 'button';
      button.style.display = 'none';
      button.setAttribute('data-toggle', 'modal');
      if (mode === 'edit') {
        button.setAttribute('data-target', '#updateAppelOffreModal');
      }
      if (mode === 'delete') {
        button.setAttribute('data-target', '#deleteAppelOffreModal');
      }
      container.appendChild(button);
      button.click();
    }
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


  modifierAppelOffre() {
    const id = parseInt(this.route.snapshot.paramMap.get('id')!);
    const dateDebut = this.appelOffre.dateDebut.toISOString().split('T')[0];
    const dateFin = this.appelOffre.dateFin.toISOString().split('T')[0];
    console.log(dateDebut)
    console.log(dateFin)
    this.appelOffreService.modifierAppelOffre(id, dateDebut, dateFin)
      .subscribe(() => {
        // La modification a réussi, naviguer vers la page de détails de l'appel d'offre
        this.router.navigate(['/appel-offre/' + id]);
      }, (erreur) => {
        // Afficher l'erreur dans la console
        console.error(erreur);
      });
  }
  


  onDeleteAppelOffre(id: number) {
    // // this.id = this.route.snapshot.params['id'];
    //  this.propositionService.deleteProposition(id)
    //  .subscribe((response) => {
    //   this.cancel();
    //   this.router.navigate(['/proposition/fournisseur'], { queryParams: { message: 'La proposition a été supprimée avec succès.' } });
    //   this.cancel(); // Fermer le modal
    //  });
   }
  

}
