
import { AppelOffre } from '../../components/appel-offre/appel-offre';
import { Fournisseur } from './fournisseur';


export class Proposition {
    id?: number;
    prixTotal: number;
    appelOffre: number;
    fournisseur: number;
    etat: string;
    besoinsProposes: number[];
  
    constructor(
        id: number,
        prixTotal: number,
        appelOffre: number,
        fournisseur: number,
        etat: string,
        besoinsProposes: number[]
      ) {
        this.id = id;
        this.prixTotal = prixTotal;
        this.appelOffre = appelOffre;
        this.fournisseur = fournisseur;
        this.etat = etat;
        this.besoinsProposes = besoinsProposes;
      }

}

