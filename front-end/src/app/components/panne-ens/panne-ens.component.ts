import { Component ,OnInit} from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Panne } from '../../models/panne';
import { ConstatService } from '../constat/constat.service';
import { PanneService } from '../panne/panne.service';
import { ActivatedRoute, Router } from '@angular/router';
import { User } from 'src/app/models/user';
import { Constat } from '../../models/constat';
import { NgForm } from '@angular/forms';
import { HeaderService } from 'src/app/services/header.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-panne-ens',
  templateUrl: './panne-ens.component.html',
  styleUrls: ['./panne-ens.component.css']
})
export class PanneEnsComponent implements OnInit {
  panne: Panne = new Panne();
  pannes: Panne[] = [];
  user:User = new User();
  constat: Constat=new Constat();
  constats: Constat[] = [];
  panne_id: number=0;
  user_id:number=0;
  messageAdd: string = '';
  messageEdit: string = '';
  messageDelete: string = '';


  constructor(private http: HttpClient,private panneService: PanneService,private router: Router,private route: ActivatedRoute,private constatService:ConstatService,private headerService:HeaderService) {}

  ngOnInit() {
   
    this.user=this.loadUser();
    this.user_id=this.user.id;
    this.panneService.getPannesEns(this.user_id).subscribe((pannes: Panne[]) => {
      this.pannes = pannes;
    });

     //////// MESSAGE DE CONFIRMATION /////////
     this.route.queryParams.subscribe(params => {
      const message = params['message'];
      if (message) {
        if (message.startsWith('La panne a été ajoutée')) {
          this.messageAdd = message;
        } else if (message.startsWith('La panne a été modifiée')) {
          this.messageEdit = message;
        } else if (message.startsWith('Le panne a été supprimée')) {
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
  onUpdatePanne(idPanne: number,mode:String) {
    this.panne_id = idPanne;
    this.panneService.getPanneById(this.panne_id).subscribe(panne => {
      this.panne = panne; 
    });
    const container = document.getElementById('main-container');
    const button = document.createElement('button');
    button.type = 'button';
    button.style.display = 'none';
    button.setAttribute('data-toggle', 'modal');
    if (mode === 'update') {
      button.setAttribute('data-target', '#updatePanneModal');
    }
    container?.appendChild(button);
  
    button.click();
  }

  public onOpenModal( mode: string): void {
    const container = document.getElementById('main-container');
    const button = document.createElement('button');
    button.type = 'button';
    button.style.display = 'none';
    button.setAttribute('data-toggle', 'modal');
    
    if (mode === 'update') {
      button.setAttribute('data-target', '#updatePanneModal');
    }
    if (mode === 'addP') {
      button.setAttribute('data-target', '#addPanneModal');
    }
    if (mode === 'delete') {
      button.setAttribute('data-target', '#deletePanneModal');
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

  onUpdate(updatedPanne:NgForm){
    this.panneService.updatePanne(this.panne.id,this.panne).subscribe(savedPanne => {
        console.log('Panne modifié avec succès :', savedPanne);
        this.closeModal("updatePanneModal");
        this.messageEdit = 'La panne a été modifiée avec succès.'; // afficher le message de confirmation
        setTimeout(() => {
          this.messageEdit = '';
        }, 3000); // masquer le message après 3 secondes
      
    });

    

  
  }

  onDeletePanne(id: number) {
    this.panneService.deletePanne(id).subscribe((response) => {
      console.log(response);  
      this.messageDelete = 'La panne a été supprimée avec succès.'; // afficher le message de confirmation
    
      this.ngOnInit(); // Actualiser la page
      
    });
    
    
  }

  

  onSubmit(addPanne:NgForm) {
    this.panneService.addPanne(this.panne,this.user_id).subscribe(savedPanne => {
      console.log(this.user_id);
      this.messageAdd = 'La panne a été ajoutée avec succès.'; // afficher le message de confirmation
      this.ngOnInit();

    
  });
  
  }

}
