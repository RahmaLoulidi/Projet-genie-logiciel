
export class AppelOffre {
    id?: string;
    dateDebut: Date;
    dateFin: Date;
    besoins: number[];
    
  
    constructor(besoins: [], dateDebut: Date, dateFin: Date) {
      this.besoins = besoins;
      this.dateDebut = dateDebut;
      this.dateFin = dateFin;
    }

}

