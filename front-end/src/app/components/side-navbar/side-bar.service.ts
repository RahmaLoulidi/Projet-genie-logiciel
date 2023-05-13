import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class SideBarService {

   role!: any ;
  constructor() { }

  ngOnInit(role : any ) {
    this.role = role;
  }

  getVariable(): any {
    return this.role;
  }

}
