import { Component ,OnInit} from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Constat } from '../../models/constat';
import { ConstatService } from '../constat/constat.service';
import { ActivatedRoute ,Router } from '@angular/router';
import { Panne } from '../../models/panne';
import { PanneService } from '../panne/panne.service';
import { User } from 'src/app/models/user';
import { NgForm } from '@angular/forms';
import { HeaderService } from 'src/app/services/header.service';


@Component({
  selector: 'app-consta-respo',
  templateUrl: './constat-respo.component.html',
  styleUrls: ['./constat-respo.component.css']
})
export class ConstatRespoComponent implements OnInit {
  
  constat: Constat=new Constat();
  constats: Constat[] = [];
  constat_id:number=0;
  panne_id: number = 0;
  panne :Panne=new Panne();
  user:User=new User();
  user_id:number=0;


  constructor(private http: HttpClient,private panneService:PanneService,private constatService: ConstatService,private route: ActivatedRoute,private router: Router,private headerService:HeaderService) {
  }

  ngOnInit() {
  
        
        this.user=this.loadUser();
         this.user_id=this.user.id;

        console.log("from constat");
        console.log(this.user);
        this.constatService.getConstatsResp().subscribe((constats: Constat[]) => { 
          this.constats = constats;
          });
    


  }
  loadUser(){

    return JSON.parse(localStorage.getItem('user') || '{}');
  }

  reparer(id: number){
    this.constatService.reparer(id,this.constat).subscribe(savedConstat => {
        console.log('Constat en réparation avec succès :', savedConstat);
        this.ngOnInit();
    });

    
  
  }
  renvoyer(id: number){
    this.constatService.renvoyer(id,this.constat).subscribe(savedConstat => {
        console.log('Constat renvoyé avec succès :', savedConstat);
        this.ngOnInit();
    });

    
  
  }
 

  
}