import { PropositionResponsableService } from './proposition-responsable.service';
import { Component,OnInit, ElementRef, Renderer2 } from '@angular/core';
import { Proposition } from './proposition-responsable';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';

import { AppelOffreService } from '../appel-offre/appel-offre.service';
import { AppelOffre } from '../appel-offre/appel-offre';
import { AppelOffreDto } from './appel-offreDto';
import { User } from 'src/app/models/user';
import { ActivatedRoute } from '@angular/router';
@Component({
  selector: 'app-proposition-responsable',
  templateUrl: './proposition-responsable.component.html',
  styleUrls: ['./proposition-responsable.component.css']
})
export class PropositionResponsableComponent  {
  public propositions: Proposition[] = [];
  public propositionsEnAttente: Proposition[] = [];
  public propositionsAcceptees: Proposition[] = [];
  messageAdd: string = '';
  messageEdit: string = '';
  messageDelete: string = '';

  user : User = new User;

  constructor(private elementRef: ElementRef, private renderer: Renderer2, private http: HttpClient,private propoService: PropositionResponsableService,private route: ActivatedRoute, private appelOffreService: AppelOffreService,) {
  }
  ngOnInit(){
    this.getPropositions();
    this.user=this.loadUser();
    this.route.queryParams.subscribe(params => {
      const message = params['message'];
      if (message) {
        if (message.startsWith('La proposition a été acceptée')) {
          this.messageAdd = message;
        } else if (message.startsWith('La proposition a été rejetée')) {
          this.messageEdit = message;
        } else if (message.startsWith('Le fournisseur a été ajouté à la liste noire')) {
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


  public getPropositions(): void{
    this.propoService.getPropositions().subscribe({
      next: (response: Proposition[]) => {
        this.propositions = response;
        this.propositionsEnAttente = response.filter(p => p.etat === 'EN_ATTENTE');
        this.propositionsAcceptees = response.filter(p => p.etat === 'ACCEPTEE');
        console.log(this.propositions);
      },
      error: (error: HttpErrorResponse) => {
            alert(error.message);
      }
    });
  }

  public accepterProposition(id: number) {
    this.propoService.accepterProposition(id).subscribe(
      proposition => {
        console.log(proposition);
        window.location.reload(); // Actualiser la page
        this.messageAdd = 'La proposition a été acceptée avec succès.'; // afficher le message de confirmation
      },
      error => console.log(error)
    );
  }

  public rejeterProposition(id: number) {
    this.propoService.rejeterProposition(id).subscribe(
      proposition => {
        console.log(proposition);
        window.location.reload();
        this.ngOnInit(); // Actualiser la page
        this.messageEdit = 'La proposition a été rejetée avec succès.'; // afficher le message de confirmation
      },
      error => console.log(error)
    );
  }

  public eliminerProposition(id: number) {
    this.propoService.eliminerProposition(id).subscribe(
      proposition => {
        console.log(proposition);
        window.location.reload();
        this.ngOnInit(); // Actualiser la page
        this.messageDelete = 'Le fournisseur a été ajouté à la liste noire avec succès.'; // afficher le message de confirmation
      },
      error => console.log(error)
    );
  }


  
  

}
  



  

