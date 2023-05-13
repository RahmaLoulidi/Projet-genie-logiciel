import {Component, ViewChild} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';
import {UtlisateursService} from './utlisateurs.service';
import {AuthService} from '../../services/auth.service';
import {UserService} from '../acceuil/user.service';
import {User} from '../../models/user';
import {MatPaginator} from "@angular/material/paginator";
import {MatTableDataSource} from "@angular/material/table";

declare var window: any;

@Component({
  selector: 'app-utilisateurs',
  templateUrl: './utilisateurs.component.html',
  styleUrls: ['./utilisateurs.component.css']
})

export class UtilisateursComponent {
  formModal: any;
  users: User [] = [];
  user: User = new User();
  u : User = new User()
  role: string = "";

  @ViewChild(MatPaginator) paginator: MatPaginator | undefined;

  pageIndex = 0;
  pageSize = 5;
  dataSource: MatTableDataSource<any> | undefined;

  constructor(private http: HttpClient, private router: Router, private utilisateurService: UtlisateursService, private auth_service: AuthService, private userService: UserService) {


    this.dataSource = new MatTableDataSource<any>(this.users);

    // connect the MatTableDataSource to the MatPaginator
    if (this.paginator) {
      this.dataSource.paginator = this.paginator;
    }

  }

  onPageChange(event: any): void {
    this.pageIndex = event.pageIndex;
    this.pageSize = event.pageSize;
  }

  loadUser() {
    return JSON.parse(localStorage.getItem('user') || '{}');
  }


  ngOnInit() {
    let userRole = this.loadUser().role;
    let departementId = this.loadUser().departement;



    this.utilisateurService.getUsers().subscribe((users: any) => {

      this.users = users;





      this.users = this.users.filter((user : any) => {
        console.log(user.roles[0]['name'], userRole);

        if (userRole == 'RESPONSABLE') {
          return user.roles[0]['name'] == 'CHEF_DEPARTEMENT' || user.roles[0]['name'] == 'FOURNISSEUR' || user.roles[0]['name'] == 'TECHNICIEN' || user.roles[0]['name'] == 'ENSEIGNANT'

        }if (userRole == 'CHEF_DEPARTEMENT') {

          return user.roles[0]['name'] == 'ENSEIGNANT'

        }else {
          return NaN
        }



      })

      console.log(this.users);
      console.log("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$",typeof(departementId));
      console.log("type de departement user", typeof(this.users[2].departement.id));
      console.log("type de departement", typeof(this.loadUser().departement));



    });

  }

  public closeModal(modalId: string): void {
    const modal = document.getElementById(modalId);
    if (modal) {
      modal.style.display = "none";
      document.body.classList.remove("modal-open");
      const modalBackdrop = document.getElementsByClassName("modal-backdrop")[0];
      if (modalBackdrop) {
        modalBackdrop.remove();
      }
    }
  }

  showDialog: boolean = false;

  openDialog() {
    this.showDialog = true;
  }

  closeDialog() {
    this.showDialog = false;
  }

  AddUser() {
    let userRole = this.loadUser().role;
    console.log(this.role);
    console.log(this.user);
    this.user.roles = [this.role];
    if (userRole == 'RESPONSABLE'){
      this.userService.addUser({...this.user, role: this.user.roles}).subscribe({
        next: (response: any) => {
          this.users.push(this.user)

          console.log(this.user);
          window.location.reload();
        }

      });
    }
    if (userRole == 'CHEF_DEPARTEMENT'){
      this.userService.addEnseignant({...this.user, role: this.user.roles}, this.loadUser().departement).subscribe({
        next: (response: any) => {
          this.users.push(this.user)

          console.log(this.user);
          window.location.reload(); 
        }
      });
    }
    this.ngOnInit();


  }

  onChange(event: any) {
    console.log("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
    console.log(event);

  }

  onRoleChange(event: any) {
    console.log("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");

    this.user.roles = event.target.value;
    console.log(this.user.roles);
  }


  openFormModal(mode: string) {
    console.log("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
    const container = document.getElementById('main-container');
    const button = document.createElement('button');
    button.type = 'button';
    button.style.display = 'none';
    button.setAttribute('data-toggle', 'modal');
    if (mode === 'add') {
      button.setAttribute('data-target', '#addUserModal');
    }
    // if (mode === 'addI') {
    //   button.setAttribute('data-target', '#addImprimanteModal');
    // }
    /*if (mode === 'edit') {
      this.editEmployee = employee;
      button.setAttribute('data-target', '#updateEmployeeModal');
    }
    if (mode === 'delete') {
      this.deleteEmployee = employee;
      button.setAttribute('data-target', '#deleteEmployeeModal');
    }*/
    container?.appendChild(button);

    button.click();
    // this.formModal = new window.bootstrap.Modal(
    //   document.getElementById('formModall')
    // );
    // this.formModal.show();
  }

  saveSomeThing() {
    // confirm or save something
    this.formModal.hide();
  }

  /* public onOpenModal(mode: string): void {
    console.log("Open Modal");
    const container = document.getElementById('main-container');
    if (container) {
      const button = document.createElement('button');
      button.type = 'button';
      button.style.display = 'none';
      button.setAttribute('data-toggle', 'modal');
      if (mode === 'edit') {
        button.setAttribute('data-target', '#updateAppelOffreModal');
      }
      container.appendChild(button);
      button.click();
    }
  } */

}
