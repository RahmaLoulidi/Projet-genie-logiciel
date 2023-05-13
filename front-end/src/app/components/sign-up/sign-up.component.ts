import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { User } from './user';
import { UserService } from './user.service';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.css']
})
export class SignUpComponent   {

   u : User = new User();




   // send data to server spring boot

   constructor(private httpClient : HttpClient,private userService: UserService) {



   }



  ngOnInit(): void {}




  save() {
    // alert(JSON.stringify(signUpRequest) );

     console.log(this.u);
      // return this.httpClient.post<User>('http://localhost:8080/registration/signup',this.u);
      this.userService.addUser(this.u).subscribe(data => console.log(data));
      }
}
