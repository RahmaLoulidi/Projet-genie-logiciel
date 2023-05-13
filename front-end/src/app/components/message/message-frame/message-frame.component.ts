import {Component, Input} from '@angular/core';
import {MessageService} from "../message.service";

@Component({
  selector: 'app-message-frame',
  templateUrl: './message-frame.component.html',
  styleUrls: ['./message-frame.component.css']
})
export class MessageFrameComponent {
  receivedMessage: boolean = false;
  @Input() message: any;

  constructor(private msg_service: MessageService) {
  }
  setSeen() {
    console.log("seen",this.message.id);

    this.msg_service.setSeen(this.message.id).subscribe(
      data => {
        console.log(data);

      }
    )
  }

  ngOnInit(): void {
    let user = this.loadUser();
    console.log('from' , this.message)
    console.log(this.receivedMessage , this.message.seen)
    this.receivedMessage = this.message.emetteur.id != user.id;
  }

  loadUser() {
    return JSON.parse(localStorage.getItem('user')!);
  }

}
