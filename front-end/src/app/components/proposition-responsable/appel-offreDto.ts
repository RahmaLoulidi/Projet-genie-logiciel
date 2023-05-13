import { Proposition } from "./proposition-responsable";

export class AppelOffreDto {
    id?: string;
    dateDebut: Date;
    dateFin: Date;
    propositions: Proposition[];
   
  
    constructor(dateDebut: Date, dateFin: Date) {
      this.dateDebut = dateDebut;
      this.dateFin = dateFin;
      this.propositions = [];
      
    }
   


}

