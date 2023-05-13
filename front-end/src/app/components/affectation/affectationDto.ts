export class AffectationDto {
    id: number;
    dateAffectation: Date;
    enseignantEmail: string;
    ressourceCodeBarre: string;

    constructor(){
        this.id = 0;
        this.dateAffectation = new Date();
        this.enseignantEmail= "";
        this.ressourceCodeBarre = "";

    }

}