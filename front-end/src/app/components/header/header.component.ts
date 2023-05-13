import { ActivatedRoute,Router } from '@angular/router';
import {Component} from '@angular/core';
import {HeaderService} from 'src/app/services/header.service';
import {MessageService} from "../message/message.service";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent {

  user: {
    id: number;
    username: string;
    role: string;
  } = {
    id: 0,
    username: '',
    role: ''
  }

  current_user : any;
  message_count = 0;

  // MSG

  usernames: string = "";
  subject: string = "";
  message: string = "";
  messages: any[] = [];


  constructor(private hreder: HeaderService, private msg_service: MessageService,private route:Router) {
    this.current_user = this.loadUser();
  }


  msgCount() {
    let count = 0;
    this.msg_service.getMessages().subscribe(
      data => {
        for (let message of data) {
          console.log(message.emetteur.id != this.current_user.id ,  message.seen == null)
          if (message.emetteur.id != this.current_user.id && message.seen == null)
            count++;
        }


        console.log('message count', count)
        this.message_count = count;
      })
  }

  ngOnInit() {
    this.loadMessages();
    this.user = this.loadUser();
    this.msgCount();
  }

  signOut(){

    console.log("signOut");
    localStorage.clear();
    this.route.navigate(["/"]);


    }

  loadUser() {
    return JSON.parse(localStorage.getItem('user')!);
  }

  loadMessages() {
    this.messages = [];
    let current_user_id: number = this.loadUser().id;
    this.msg_service.getMessages().subscribe(
      data => {
        console.log(data)
        data.forEach((message: any) => {
          console.log(message);
            if (message.recepteurs.find((user: any) => user.id == current_user_id))
              this.messages.push(message);
        });
        console.log(this.messages)
      }
    )

  }

}



