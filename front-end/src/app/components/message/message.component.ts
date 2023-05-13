import {Component} from '@angular/core';
import {MessageService} from "./message.service";
import {UserService} from "../../models/user.service";
import {User} from "../../models/user";


@Component({
  selector: 'app-messages',
  templateUrl: './message.component.html',
  styleUrls: ['./message.component.css']
})
export class MessagesComponent {
  usernames: string = "";
  subject: string = "";
  message: string = "";
  messages: any[] = [];
  showSendMessage = false;
  showReceived: boolean = false;

  constructor(private msg_service: MessageService, private userService: UserService) {
  }

  toggleSendMessage() {
    this.showSendMessage = !this.showSendMessage;
  }

  ngOnInit(): void {
    this.loadMessages(this.showReceived);
  }

  setShowReceived() {
    this.showReceived = !this.showReceived;
    this.loadMessages(this.showReceived);
  }


  addMessage() {
    // split messages by comma
    if (this.usernames == "") {
      alert('Entrer un ou plusieurs destinataires séparés par une virgule');
      return;
    }
    let usernames = this.usernames.split(",");

    if (this.subject == "") {
      alert('Entrer un sujet');
      return;
    }
    if (this.message == "") {
      alert('Entrer un message');
      return;
    }
    console.log("message", this.message);


    usernames = usernames.map(email => email.trim());
    console.log(usernames, this.subject, this.message);

    // get user ids from usernames
    let ids: number[] = [];
    let users: User[] = [];
    // get users id that have the usernames
    this.userService.getAllUsers().subscribe(
      data => {
        users = data
        usernames.forEach(username => {
          let user = users.find((user: any) => user.username == username);
          if (user) {
            ids.push(user.id);
            console.log(user.id, "pushed to ids");
          } else {
            alert('Utilisateur ' + username + ' n\'existe pas');
            throw new Error('Utilisateur ' + username + ' n\'existe pas');
          }


        });
        console.log("ids", ids);
        let current_user_id: number = this.loadUser().id;
        if (ids.length != 0) {

          this.msg_service.addMessage({
            subject: this.subject, contenu: this.message, emetteur: current_user_id, recepteurs: ids
          }).subscribe(
            data => {
              console.log(data);
              alert('Message envoyé');
              this.loadMessages()
            }
          )
        }

      }
    )


  }

  loadMessages(received: boolean = false) {
    this.messages = [];
    let current_user_id: number = this.loadUser().id;
    this.msg_service.getMessages().subscribe(
      data => {
        console.log(data)
        data.forEach((message: any) => {
          console.log(message);
          if (received) {
            if (message.recepteurs.find((user: any) => user.id == current_user_id))
              this.messages.push(message);

          } else if (message.emetteur.id == current_user_id)
            this.messages.push(message);


        });
        console.log(this.messages)
      }
    )

  }

  loadUser() {
    return JSON.parse(localStorage.getItem('user')!);
  }
}
