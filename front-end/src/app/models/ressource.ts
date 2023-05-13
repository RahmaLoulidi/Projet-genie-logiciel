export class ressource {

    id: number;
    cpu: string;
    disqueDur: string;
    ecran: string;
    ram: string;
    codeBarre: string;
    dureeGarantie: string;
    dateLivraison : Date;
    marque: string;
    
    constructor() {
      this.id = 0;
      this.codeBarre="";
      this.cpu="";
      this.dateLivraison=new Date();
      this.disqueDur="";
      this.dureeGarantie="";
      this.ecran="";
      this.marque="";
      this.ram="";
     }
  }