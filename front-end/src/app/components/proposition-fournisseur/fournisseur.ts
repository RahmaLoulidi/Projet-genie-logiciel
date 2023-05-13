

export class Fournisseur {
    id?: number;
    username: string;
    email: string;
    nomSociete: string;
    gerant: string;
    lieu: string;
    listeNoirId: number;
    //propositions: Proposition[];

    constructor() {
        this.username = '';
        this.email = '';
        this.nomSociete = '';
        this.gerant = '';
        this.lieu = '';
        this.listeNoirId = 0;
      }

  }
  