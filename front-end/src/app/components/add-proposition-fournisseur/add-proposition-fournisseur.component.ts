import { Component, OnInit } from '@angular/core';
import { AddPropositionFournisseurService } from './add-proposition-fournisseur.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Besoin } from '../besoin/besoin';
import { BesoinProposeImprimante } from './besoin-propose-imprimante';
import { BesoinProposeOrdinateur } from './besoin-propose-ordinateur';
import { User } from 'src/app/models/user';
import { Proposition } from '../proposition-fournisseur/proposition';
import { PropositionFournisseurService } from '../proposition-fournisseur/proposition-fournisseur.service';
import { ElementRef } from '@angular/core';
import { BesoinPropose } from './besoin-propose';



@Component({
  selector: 'app-add-proposition-fournisseur',
  templateUrl: './add-proposition-fournisseur.component.html',
  styleUrls: ['./add-proposition-fournisseur.component.css']
})
export class AddPropositionFournisseurComponent implements OnInit{

  user : User = new User;

  besoins: Besoin[] = [];

  besoinsOrdinateurs: Besoin[] = [];
  besoinsImprimantes: Besoin[] = [];

  besoinsPropose: BesoinPropose[] = [];
  besoinsProposeImprimante: BesoinProposeImprimante[] = [];
  besoinsProposeOrdinateur: BesoinProposeOrdinateur[] = [];

  propositions : Proposition[] = []


  constructor(
    private addPropositionService: AddPropositionFournisseurService,
    private propositionFournisseurService : PropositionFournisseurService,
    private route: ActivatedRoute,
    private router: Router,
    private elementRef: ElementRef,

  ) {}

  ngOnInit() {
    this.user=this.loadUser();
    this.afficherBesoins();

  }

  loadUser(){
    return JSON.parse(localStorage.getItem('user') || '{}');
  }
  

  afficherBesoins() {
    const appelOffreId = +this.route.snapshot.paramMap.get('id')!;
    this.addPropositionService.getBesoins(appelOffreId).subscribe(
      (data: Besoin[]) => {
        this.besoins = data;
        this.besoinsOrdinateurs = this.addPropositionService.getBesoinsOrdinateurs(data);
        this.besoinsImprimantes = this.addPropositionService.getBesoinsImprimantes(data);
        this.besoinsProposeOrdinateur = this.addPropositionService.getBesoinsOrdinateurs(data).map(b => new BesoinPropose(b.id, '', 0));
        this.besoinsProposeImprimante = this.addPropositionService.getBesoinsImprimantes(data).map(b => new BesoinPropose(b.id, '', 0));
      },
      (error) => {
        console.error(error);
      }
    );
  }
  
  



  ajouterProposition(): void {
    const appelOffreId = +this.route.snapshot.paramMap.get('id')!;
    const propositions: BesoinPropose[] = [];
  
    for (let i = 0; i < this.besoinsOrdinateurs.length; i++) {
      const besoinProposeOrdinateur = this.besoinsProposeOrdinateur[i];
      if (besoinProposeOrdinateur.marque && besoinProposeOrdinateur.prix) {
        propositions.push(besoinProposeOrdinateur);
      }
    }
  
    for (let i = 0; i < this.besoinsImprimantes.length; i++) {
      const besoinProposeImprimante = this.besoinsProposeImprimante[i];
      if (besoinProposeImprimante.marque && besoinProposeImprimante.prix) {
        propositions.push(besoinProposeImprimante);
      }
    }
  
  //   this.addPropositionService.ajouterProposition(appelOffreId, propositions,this.user.id).subscribe(
  //     (data: any) => {
  //       this.router.navigate(['/appel-offre', appelOffreId]);
  //     },
  //     (error) => {
  //       console.error(error);
  //     }
  //   );
  // }
  

  this.addPropositionService.ajouterProposition(appelOffreId, propositions,this.user.id).subscribe(
    (data: any) => {
      // Redirige l'utilisateur vers le contenu HTML du component PropositionFournisseurComponent
      this.router.navigate(['/proposition/fournisseur'], { queryParams: { message: 'La proposition a été ajoutée avec succès.' } });
    },
    (error) => {
      console.error(error);
      console.log(error.error); 
    }
  );
  
  

  }




}
