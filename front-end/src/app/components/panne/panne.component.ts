import { Component ,OnInit} from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Panne } from '../../models/panne';
import { ConstatService } from '../constat/constat.service';
import { PanneService } from './panne.service';
import { Router } from '@angular/router';
import { User } from 'src/app/models/user';
import { Constat } from '../../models/constat';
import { NgForm } from '@angular/forms';



@Component({
  selector: 'app-panne',
  templateUrl: './panne.component.html',
  styleUrls: ['./panne.component.css']
})
export class PanneComponent implements OnInit {
  panne: Panne = new Panne();
  pannes: Panne[] = [];
  user:User = new User();
  constat: Constat=new Constat();
  constats: Constat[] = [];
  panne_id: number=0;
  isPrinter: boolean=false;

  constructor(private http: HttpClient,private panneService: PanneService,private router: Router,private constatService:ConstatService) {}

  ngOnInit() {
    this.user = this.loadUser();
    this.panneService.getPannes().subscribe ((pannes: Panne[]) => {
      this.pannes = pannes;
  });
   
  }
  loadUser(){
    return JSON.parse(localStorage.getItem('user')!);
   }
  onAjouterConstat(idPanne: number,mode:String) {
    this.panne_id = idPanne;
    this.panneService.getPanneById(this.panne_id).subscribe(panne => {
      this.panne = panne; 
    });

    this.panneService.isPrinter(idPanne).subscribe((result) => {
      this.isPrinter = result;
      console.log(this.isPrinter);
    });

   // this.router.navigate(['/constat',idPanne ]);
    const container = document.getElementById('main-container');
    const button = document.createElement('button');
    button.type = 'button';
    button.style.display = 'none';
    button.setAttribute('data-toggle', 'modal');
    if (mode === 'addC') {
      button.setAttribute('data-target', '#addConstatModal');
    }
  
    container?.appendChild(button);
  
    button.click();
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
  
 

onSubmitConstat(addConstat:NgForm) : void {

  this.http.post(`http://localhost:8080/constat/save/${this.panne_id}/${this.user.id}`, this.constat).subscribe(savedConstat => {
    console.log('Constat créé avec succès :', savedConstat);
    this.cancel();
    this.router.navigate(['/constat'], { queryParams: { message: 'Le constat a été ajouté avec succès.' } });
  
    
});

}}

