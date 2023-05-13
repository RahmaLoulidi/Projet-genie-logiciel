import { User } from 'src/app/models/user';

export class Besoin {
    id: number;
    cpu: string;
    disqueDur: string;
    ecran: string;
    marque: string;
    ram: string;
    resolution: string;
    type: string;
    vitesse: string;
    enseignant_id: number | null;
    envoie : number;
    username:string ='';
    
    constructor() {
      this.id = 0;
      this.marque = '';
      this.cpu = '';
      this.disqueDur= '';
      this.ecran= '';
      this.ram= '';
      this.resolution= '';
      this.type= '';
      this.vitesse= '';
      this.enseignant_id= null;
      this.envoie=0;
  
     }


  }