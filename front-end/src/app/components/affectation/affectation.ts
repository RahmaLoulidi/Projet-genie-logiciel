export class Affectation {
    id: number;
    dateAffectation: Date;
    enseignantId: number;
    ressourceId: number;

    constructor(){
        this.id = 0;
        this.dateAffectation = new Date();
        this.enseignantId= 0;
        this.ressourceId = 0;

    }

}