export class Imprimante {
    id: number;
    vitesse: string;
    resolution: string;
    codeBarre: string;
    dureeGarantie: string;
    dateLivraison : string;
    marque: string;
    
  
  
    constructor() {
      this.id = 0;
      this.codeBarre="";
      this.dateLivraison="";
      this.dureeGarantie="";
      this.resolution="";
      this.marque="";
      this.vitesse="";
     }
  }