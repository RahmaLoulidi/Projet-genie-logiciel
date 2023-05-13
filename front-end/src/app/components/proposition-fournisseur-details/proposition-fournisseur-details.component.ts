import { Component, OnInit} from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { PropositionFournisseurDetailsService } from './proposition-fournisseur-details.service';
import { AppelOffreService } from '../appel-offre/appel-offre.service';
import { Proposition } from '../proposition-fournisseur/proposition';
import { AppelOffre } from '../appel-offre/appel-offre';
import { BesoinProposeDetails } from './besoin-propose-details';
import { Observable } from 'rxjs';
import { User } from 'src/app/models/user';
import { BesoinPropose } from '../add-proposition-fournisseur/besoin-propose';



@Component({
  selector: 'app-proposition-fournisseur-details',
  templateUrl: './proposition-fournisseur-details.component.html',
  styleUrls: ['./proposition-fournisseur-details.component.css']
})
export class PropositionFournisseurDetailsComponent implements OnInit{

  user : User = new User;

  id!: number;
  proposition!: Proposition;
  appelOffre!: AppelOffre;
  besoinsProposesDetails: BesoinProposeDetails[] = [];

  besoinsProposesOrdinateurs : BesoinProposeDetails[] = [];
  besoinsProposesImprimantes : BesoinProposeDetails[] = [];
  

  besoinsProposesFormatted : BesoinPropose[] = [];


  messageAdd: string = '';
  messageEdit: string = '';
  messageDelete: string = '';

  

  constructor(
    private route: ActivatedRoute,
    private propositionService: PropositionFournisseurDetailsService,
    private appelOffreService: AppelOffreService,
    private router: Router,
    // private besoinProposeService: BesoinProposeService
  ) { }



  
  ngOnInit() {
    this.user=this.loadUser();


    this.id = this.route.snapshot.params['id'];
    this.propositionService.getPropositionById(this.id).subscribe(
      (data: Proposition) => {
        this.proposition = data;
        this.getAppelOffreById(this.proposition.appelOffre);
  
        // appel de la méthode getBesoinProposeByIds() qui retourne un Observable
        this.getBesoinProposeByIds(this.proposition.besoinsProposes).subscribe(
          (data: BesoinProposeDetails[]) => {
            this.besoinsProposesDetails = data;
  
            // filtrer les besoins ordinateurs
            this.besoinsProposesOrdinateurs = this.besoinsProposesDetails.filter(besoinPropose => besoinPropose.besoin.type === 'Ordinateur');
            console.log("Besoins ordinateurs :", this.besoinsProposesOrdinateurs);
  
            // filtrer les besoins imprimantes
            this.besoinsProposesImprimantes = this.besoinsProposesDetails.filter(besoinPropose => besoinPropose.besoin.type === 'Imprimante');
            console.log("Besoins imprimantes :", this.besoinsProposesImprimantes);
  
            console.log(this.besoinsProposesDetails);
          },
          (error) => console.log(error)
        );
      },
      (error) => console.log(error)
    );


     //////// MESSAGE DE CONFIRMATION /////////
    this.route.queryParams.subscribe(params => {
      const message = params['message'];
      if (message) {
        if (message.startsWith('La proposition a été ajoutée')) {
          this.messageAdd = message;
        } else if (message.startsWith('La proposition a été modifiée')) {
          this.messageEdit = message;
        } else if (message.startsWith('La proposition a été supprimée')) {
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
  


  getAppelOffreById(id: number) {
    this.appelOffreService.getAppelOffreById(id).subscribe(
      (data: any) => this.appelOffre = data,
      (error) => console.log(error)
    );
  }



  getBesoinProposeByIds(ids: number[]): Observable<BesoinProposeDetails[]> {
    return this.propositionService.getBesoinProposeByIds(ids);
  }






  // getBesoinsProposesModif(propositionId: number): void {
  //   this.propositionService.getBesoinsProposes(propositionId)
  //     .subscribe(besoinsProposesModif => this.besoinsProposesModif = besoinsProposesModif);
  // }



   
  ////////////////////////////////////////////////////////////////////////////////////////
  //////////////////////////// MODAL ////////////////////////////////////////////////////
  //////////////////////////////////////////////////////////////////////////////////////


  
  public onOpenModal(mode: string): void {
    console.log("Open Modal");
    const container = document.getElementById('main-container');
    if (container) {
      const button = document.createElement('button');
      button.type = 'button';
      button.style.display = 'none';
      button.setAttribute('data-toggle', 'modal');
      if (mode === 'edit') {
        button.setAttribute('data-target', '#updatePropositionModal');
      }
      if (mode === 'delete') {
        button.setAttribute('data-target', '#deletePropositionModal');
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
  
  

  public closeModal(modalId: string): void {
    const modal = document.getElementById(modalId);
    if (modal) {
      modal.style.display = "none";
      document.body.classList.remove("modal-open");
      const modalBackdrop = document.getElementsByClassName("modal-backdrop")[0];
      if (modalBackdrop) {
        modalBackdrop.remove();
      }
    }
  }
  


  public modifierProposition(): void {
    const propositionId = this.route.snapshot.params['id'];
    const fournisseurId = this.user.id;
  
    this.besoinsProposesFormatted = this.besoinsProposesDetails.map(besoinPropose => {
      return {
        id: besoinPropose.id,
        prix: besoinPropose.prix,
        marque: besoinPropose.marque,
        besoinId: besoinPropose.besoin.id
      };
    });
  
    this.propositionService.modifierBesoinsProposes(propositionId, this.besoinsProposesFormatted, fournisseurId)
    .subscribe(
      (response: Proposition) => {
        this.closeModal("updatePropositionModal");
        this.messageEdit = 'La proposition a été modifiée avec succès.'; // afficher le message de confirmation
        setTimeout(() => {
          this.messageEdit = '';
        }, 3000); // masquer le message après 3 secondes
      },
      (error: any) => {
        console.log(error);
      }
    );
  
  }
  

  onDeleteProposition(id: number) {
    // this.id = this.route.snapshot.params['id'];
     this.propositionService.deleteProposition(id)
     .subscribe((response) => {
      this.cancel();
      this.router.navigate(['/proposition/fournisseur'], { queryParams: { message: 'La proposition a été supprimée avec succès.' } });
      this.cancel(); // Fermer le modal
     });
   }
  
  


  
  




}
  
  


// this.propositionService.modifierBesoinsProposes(propositionId, this.besoinsProposesFormatted, fournisseurId)
//     .subscribe(
//       (response: Proposition) => {
 
//         //this.cancel();
//         this.router.navigate(['/proposition/fournisseur'], { queryParams: { message: 'La proposition a été modifiée avec succès.' } });
//         this.cancel(); // Fermer le modal

//       },
//       (error: any) => {
//         console.log(error);
//       }
//     );




// public cancel(): void {
//   console.log("Cancel button clicked!");
//   const container = document.getElementById('main-container');
//   const modalBackdrop = document.getElementsByClassName('modal-backdrop')[0];
//   if (container) {
//     const button = document.createElement('button');
//     button.type = 'button';
//     button.style.display = 'none';
//     button.setAttribute('data-dismiss', 'modal');
//     container.appendChild(button);
//     button.click();
//     modalBackdrop.remove(); // Supprimer le bouton modal de la page
//   }
// }
