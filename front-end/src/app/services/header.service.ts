import { Injectable } from '@angular/core';
import { User } from '../models/user';
import { ActivatedRoute } from '@angular/router';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class HeaderService {

  constructor(private route: ActivatedRoute ,private authService: AuthService) {

  }
  user : User = new User();
  ngOnInit(user : User) {
    this.user = user;
    console.log(this.user);
  }

  getVariable(): User {
    return this.user;
  }
  

  signOut(){
    this.authService.signOut().subscribe({
      next: (response : any) => {
        console.log("response");
        this.authService.setLogged('false');
      }
    });

  }
}
