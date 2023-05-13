export class User {
  id:number;
  username: string;
  password: string;
  nomSociete: string;
  email: string;
  roles: string | any;
  departement: number | any;
  



  constructor() {
    this.id=0;
    this.username = '';
    this.password = '';
    this.nomSociete = '';
    this.email='';
    this.roles='';
    this.departement=0;


   }
}

