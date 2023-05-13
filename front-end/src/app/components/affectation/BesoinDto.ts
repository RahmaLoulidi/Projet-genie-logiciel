export class BesoinDto {
    id: number;
    marque: string;
    cpu: string;
    disqueDur: string;
    ecran: string;
    ram: string;
    resolution: string;
    vitesse: string;
    type: string;
    enseignant: string;
    enseignantId: number;

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
        this.enseignantId= 0;
        this.enseignant = ""
    
       }
  
  }
  