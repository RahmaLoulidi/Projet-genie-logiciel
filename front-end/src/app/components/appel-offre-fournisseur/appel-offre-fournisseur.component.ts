import { Component, OnInit } from '@angular/core';
import { AppelOffre } from '../appel-offre/appel-offre';
import { AppelOffreService } from '../appel-offre/appel-offre.service';
import { AppelOffreFournisseurService } from './appel-offre-fournisseur.service';
import { User } from 'src/app/models/user';

@Component({
  selector: 'app-appel-offre-fournisseur',
  templateUrl: './appel-offre-fournisseur.component.html',
  styleUrls: ['./appel-offre-fournisseur.component.css']
})
export class AppelOffreFournisseurComponent implements OnInit {

  user : User = new User;
  appelOffreByFournisseurIdNull : AppelOffre[] = [];

  constructor(
    private appelOffeFournisseurService: AppelOffreFournisseurService,
    ) {}

  ngOnInit() {
    this.getAppelOffresByFournisseurIdNull();
    this.user=this.loadUser();
  }


  loadUser(){
    return JSON.parse(localStorage.getItem('user') || '{}');
  }


  getAppelOffresByFournisseurIdNull() {
    this.appelOffeFournisseurService.getAppelOffreByFournisseurIdNull().subscribe((appelOffreByFournisseurIdNull: AppelOffre[]) => {
      this.appelOffreByFournisseurIdNull = appelOffreByFournisseurIdNull;
    });
  }


}
