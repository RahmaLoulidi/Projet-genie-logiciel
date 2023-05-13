import { Panne } from "./panne";

export class Constat {
    id : number
    explication: string;
    dateApparition: Date;
    frequence: string;
    ordre: string;
    send: string;
    reparation: string;
    renvoie: string;
  
  
  
    constructor( ) {
    
    this.id = 0;
    this.explication = '';
    this.dateApparition = new Date();
    this.frequence = '';
    this.ordre = '';
    this.send = '';
    this.reparation = '';
    this.renvoie= '';
  
  

  }
     

  }