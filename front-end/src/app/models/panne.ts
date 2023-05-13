export class Panne {
    id : number
    explication: string;
    datePanne: string;
    
  
  
    constructor() {
      this.id=0;
      this.explication = '';
      this.datePanne= '';
  
     }

     getId(){
      return this.id;
     }
  }