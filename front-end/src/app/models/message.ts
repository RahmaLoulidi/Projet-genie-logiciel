import { User } from "./user";

export class Message {
    id : number
    subject: string;
    content: string;
    seen: number;
    emetteur: User = new User;
  
  
  
    constructor( ) {
    
    this.id = 0;
    this.subject = '';
    this.content = '';
    this.seen = 0;
  

  }
}