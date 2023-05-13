import { Component, OnInit } from '@angular/core';
import { AppelOffre } from '../appel-offre/appel-offre';
import { AppelOffreService } from '../appel-offre/appel-offre.service';
import { User } from 'src/app/models/user';
import { PropositionFournisseurService } from './proposition-fournisseur.service';
import { Proposition } from './proposition';
import { ActivatedRoute } from '@angular/router';


@Component({
  selector: 'app-proposition-fournisseur',
  templateUrl: './proposition-fournisseur.component.html',
  styleUrls: ['./proposition-fournisseur.component.css']
})
export class PropositionFournisseurComponent implements OnInit {

  user : User = new User;

  propositions : Proposition[] = [];

  messageAdd: string = '';
  messageEdit: string = '';
  messageDelete: string = '';

  
  
  ngOnInit() {
    this.user=this.loadUser();
    this.getPropositionsByFournisseur();

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




  constructor(
    private route: ActivatedRoute,
    private propositionFournisseurService : PropositionFournisseurService,
  ) {}


  loadUser(){
    return JSON.parse(localStorage.getItem('user') || '{}');
  }

  getPropositionsByFournisseur() {
    this.propositionFournisseurService.getAllPropositionsByFournisseur(this.user.id).subscribe(
      (propositions: Proposition[]) => { this.propositions = propositions; 
        console.log(this.propositions);
      });
  }

  
}
