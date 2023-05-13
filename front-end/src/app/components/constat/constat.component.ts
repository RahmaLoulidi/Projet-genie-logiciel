import { Component ,OnInit} from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Constat } from '../../models/constat';
import { ConstatService } from './constat.service';
import { ActivatedRoute ,Router } from '@angular/router';
import { Panne } from '../../models/panne';
import { PanneService } from '../panne/panne.service';
import { User } from 'src/app/models/user';
import { NgForm } from '@angular/forms';
import { HeaderService } from 'src/app/services/header.service';


@Component({
  selector: 'app-constat',
  templateUrl: './constat.component.html',
  styleUrls: ['./constat.component.css']
})
export class ConstatComponent implements OnInit {
  
  constat: Constat=new Constat();
  constats: Constat[] = [];
  constat_id:number=0;
  panne_id: number = 0;
  panne :Panne=new Panne();
  user:User=new User();
  user_id:number=0;
  messageAdd: string = '';
  messageEdit: string = '';
  messageDelete: string = '';


  constructor(private http: HttpClient,private panneService:PanneService,private constatService: ConstatService,private route: ActivatedRoute,private router: Router,private headerService:HeaderService) {
  }

  ngOnInit() {
    
      this.route.params.subscribe(params => {
        this.panne_id = +params['idPanne'];
        this.panneService.getPanneById(this.panne_id).subscribe(panne => {
          this.panne = panne; // Stocker la panne dans la propriété panne
          // Créer un nouvel objet Constat et attribuer la panne et son ID

         // constat.panne_id= this.panne.id; // Attribuer l'ID de la panne
         //this.constat.panne = this.panne; // Attribuer l'objet Panne
         // Utiliser l'objet Constat dans la suite de la logique du composant
         // ...
        });});
        
        this.user=this.loadUser();
         this.user_id=this.user.id;

        console.log("from constat");
        console.log(this.user);
        this.constatService.getConstatsTech(this.user.id).subscribe((constats: Constat[]) => { 
          this.constats = constats;
          });

          //////// MESSAGE DE CONFIRMATION /////////
    this.route.queryParams.subscribe(params => {
      const message = params['message'];
      if (message) {
        if (message.startsWith('Le constat a été ajouté')) {
          this.messageAdd = message;
        } else if (message.startsWith('Le constat a été modifié')) {
          this.messageEdit = message;
        } else if (message.startsWith('Le constat a été supprimé')) {
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

  onUpdateConstat(idConstat: number,mode:String) {
    this.constat_id = idConstat;
    this.constatService.getConstatById(this.constat_id).subscribe(constat => {
      this.constat = constat; 
    });
    const container = document.getElementById('main-container');
    const button = document.createElement('button');
    button.type = 'button';
    button.style.display = 'none';
    button.setAttribute('data-toggle', 'modal');
    if (mode === 'update') {
      button.setAttribute('data-target', '#updateConstatModal');
    }
    container?.appendChild(button);
  
    button.click();
  }

    onSubmit() {

      this.http.post(`http://localhost:8080/constat/save/${this.panne_id}/${this.user.id}`, this.constat).subscribe(savedConstat => {
        console.log('Constat créé avec succès :', savedConstat);
        this.router.navigate(['/constat']);
        
    });
    this.ngOnInit();
  }

  onUpdate(updated:NgForm){
    this.constatService.updateConstat(this.constat.id,this.constat).subscribe(savedConstat => {
        console.log('Constat modifié avec succès :', savedConstat);
        this.closeModal("updateConstatModal");
        this.messageEdit = 'Le constat a été modifié avec succès.'; // afficher le message de confirmation
        setTimeout(() => {
          this.messageEdit = '';
        }, 3000); // masquer le message après 3 secondes
      
    });
    
   
  
  }
  onSend(id: number){
    this.constatService.sendConstat(id,this.constat).subscribe(savedConstat => {
        console.log('Constat send avec succès :', savedConstat);
        this.ngOnInit();
    });
   

  
  }

  onDeleteConstat(id: number) {
    this.constatService.deleteConstat(id).subscribe((response) => {
      console.log(response);
      
    });
    this.ngOnInit();
   
  }

}