import {Component} from '@angular/core';
import {AcceuilComponent} from '../acceuil/acceuil.component';
import {SideBarService} from './side-bar.service';

@Component({
  selector: 'app-side-navbar',
  templateUrl: './side-navbar.component.html',
  styleUrls: ['./side-navbar.component.css']
})
export class SideNavbarComponent {
  role = "";
  user: {
    id: number;
    username: string;
    role: string;
  } = {
    id: 0,
    username: '',
    role: ''
  }

  constructor(private sideBer: SideBarService) {
  }

  ngOnInit() {
    this.user = this.loadUser();
    this.role = this.user.role;
  }

  // save role in local storage
  loadUser() {
    return JSON.parse(localStorage.getItem('user')!);
  }


}
