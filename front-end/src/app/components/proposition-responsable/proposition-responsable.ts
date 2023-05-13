export class Proposition {
    id : number ;
    dateDebut: Date;
    dateFin: Date;
    nomFournisseur: string;
    prixTotal: number;
    etat: string;
    
 
    constructor(dateDebut: Date, dateFin: Date) {
        this.id=0;
        this.dateDebut=dateDebut;
        this.dateFin=dateFin;
        this.nomFournisseur="";
        this.prixTotal=0;   
        this.etat = "";
     }

    }
