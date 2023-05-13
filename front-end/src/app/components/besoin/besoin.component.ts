import { Component ,OnInit} from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Besoin } from './besoin';
import { BesoinService } from './besoin.service';
import { FormBuilder, FormGroup } from '@angular/forms';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-besoin',
  templateUrl: './besoin.component.html',
  styleUrls: ['./besoin.component.css']
})
export class BesoinComponent implements OnInit {

  showOrdianteurInputs: string = 'Ordinateur';
  besoin : Besoin = new Besoin();
  public besoins: Besoin[] = [];
  besoinForm:  FormGroup;
  currentUser: User = new User();

  messageAdd: string = '';
  messageEdit: string = '';
  messageDelete: string = '';

  besoinsNonAffectes: Besoin[] = [];
  
  besoinsOrdinateur : Besoin[] = [];
  besoinsImprimante : Besoin[] = [];


  
  constructor(private http: HttpClient,private besoinService: BesoinService, private formBuilder: FormBuilder,private route: ActivatedRoute, private authService: AuthService) {
    this.besoinForm = this.formBuilder.group({
      marque: '',
      cpu: '',
      disqueDur:'',
      ecran:'',
      ram: '',
      resolution: '',
      type: '',
      vitesse: ''
    });

  }

  loadUser(){

    return JSON.parse(localStorage.getItem('user') || '{}');
  }

  ngOnInit(){
    
    this.currentUser=this.loadUser();
    console.log(this.currentUser);
    console.log(this.currentUser.id);

    this.route.queryParams.subscribe(params => {
      const message = params['message'];
      if (message) {
        if (message.startsWith('Le besoin a été ajouté')) {
          this.messageAdd = message;
        } else if (message.startsWith('Le besoin a été modifié')) {
          this.messageEdit = message;
        } else if (message.startsWith('Le besoin a été supprimé')) {
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


  onTypeChange($event: Event) {
    console.log(($event.target as HTMLInputElement).value);
    this.showOrdianteurInputs = ($event.target as HTMLInputElement).value;
  }


  
  public getBesoins(): void{
    this.besoinService.getBesoins().subscribe({
      next: (response: Besoin[]) => {
        this.besoins = response;
        console.log(this.besoins);
      },
      error: (error: HttpErrorResponse) => {
            alert(error.message);
      }
    });
  }


  /*public onAddBesoin(): void{
    this.besoinService.addBesoin(this.besoin).subscribe({
      next: (response: Besoin) => {
        console.log("Besoin ajouté avec succès:", response);
      },
      error: (error: HttpErrorResponse) => {
            alert(error.message);
      }
    });
  }*/

  /*ngOnInit() {
    this.besoinService.getBesoins().subscribe((besoins: Besoin[]) => {
      this.besoins = besoins;
    });
  }*/

  
  onAddBesoin() {
    
    this.http.post<Besoin>(`http://localhost:8080/besoin/add/${this.currentUser.id}`, this.besoin).subscribe(savedBesoin => {
      console.log('Besoin ajouté avec succès :', savedBesoin);
     
      this.ngOnInit();      
      this.besoinForm.reset();
      this.messageAdd = 'Le besoin a été ajouté avec succès.'; // afficher le message de confirmation
      
  });
}

  
}





