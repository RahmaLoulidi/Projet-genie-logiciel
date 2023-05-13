import {Component} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';
import {User} from '../../models/user';
import {UserService} from './user.service';
import {AuthService} from '../../services/auth.service';
import {FormGroup} from '@angular/forms';
import {HeaderService} from '../../services/header.service';
import {PanneService} from '../panne/panne.service';
import {SideBarChatComponent} from '../side-bar-chat/side-bar-chat.component';
import {SideBarService} from '../side-navbar/side-bar.service'
import Swal from 'sweetalert2';


@Component({
  selector: 'app-acceuil',
  templateUrl: './acceuil.component.html',
  styleUrls: ['./acceuil.component.css']
})
export class AcceuilComponent {

  user: User = new User();
  u: User = new User();
  mode: string = "register";
  loginForm!: FormGroup;
  errorMessage: string = '';
  token!: any;
  role!: any;
  departement!: any;

  constructor(private httpClient: HttpClient, private router: Router,
              private userService: UserService, private authService: AuthService, private header: HeaderService,
              private panneService: PanneService, private sideBarService: SideBarService) {
  }

  onSubmit() {

    this.errorMessage = "";
    /*if (this.mode === 'register') {
       this.authService.registerUser(this.user)
       .subscribe(
         (response) => {
           console.log("registration successful !!");
         }
       );
       this.mode = 'authentificate';
     } else {*/
    this.authService.login(this.user)
      .subscribe({
        next: (response: any) => {

          //this.token = response.token;
          //localStorage.setItem('token', response.token);

          // get attribut from response
          this.role = response.role;
          this.departement = response.departementID;
          this.u.id = response.id;
          console.log(this.role);
          this.header.ngOnInit(response);
          this.sideBarService.ngOnInit(response.role)
          if (this.role == "TECHNICIEN") {
            this.authService.setUsername(this.user.username);
            this.authService.setId(this.user.id);
            this.authService.setRole(this.role);
            //this.authService.setToken(this.token);
            this.authService.setLogged("true");
            this.authService.setRoleLocal("TECHNICIEN");
            // save to local storage
            let user = {
              id: this.u.id,
              username: this.user.username,
              role: this.role
            }
            localStorage.setItem('user', JSON.stringify(user));

            this.router.navigate(['/panne'], {state: {u: response}});
          }else if (this.role == "FOURNISSEUR") {
              this.authService.setUsername(this.user.username);
              this.authService.setId(this.user.id);
              this.authService.setRole(this.role);
              //this.authService.setToken(this.token);
              this.authService.setLogged("true");
              this.authService.setRoleLocal("FOURNISSEUR");
  // save to local storage
              let user = {
                id: this.u.id,
                username: this.user.username,
                role: this.role
              }
              localStorage.setItem('user', JSON.stringify(user));

              this.router.navigate(['/appelOffreFournisseur'], {state: {u: response}});
          } else if (this.role == "ENSEIGNANT") {
            this.authService.setUsername(this.user.username);
            this.authService.setId(this.user.id);
            this.authService.setRole(this.role);
            //this.authService.setToken(this.token);
            this.authService.setLogged("true");
            this.authService.setRoleLocal("ENSEIGNANT");
            this.header.ngOnInit(this.user);
            let user = {
              id: this.u.id,
              username: this.user.username,
              role: this.role
            }
            localStorage.setItem('user', JSON.stringify(user));
            this.router.navigate(['/besoin'], {state: {u: response}});
          } else if (this.role == "RESPONSABLE") {
            this.authService.setUsername(this.user.username);
            this.authService.setRole(this.role);
            //this.authService.setToken(this.token);
            this.authService.setLogged("true");
            this.authService.setRoleLocal("RESPONSABLE");
            this.header.ngOnInit(this.user);
            let user = {
              id: this.u.id,
              username: this.user.username,
              role: this.role
            }
            localStorage.setItem('user', JSON.stringify(user));
            this.router.navigate(['/appelOffre'], {state: {u: response}});
          } else if (this.role == "CHEF_DEPARTEMENT") {
            this.authService.setUsername(this.user.username);
            this.authService.setRole(this.role);
            //this.authService.setToken(this.token);
            this.authService.setLogged("true");
            this.header.ngOnInit(this.user);
            let user = {
              id: this.u.id,
              username: this.user.username,
              role: this.role,
              departement : this.departement
            }
            localStorage.setItem('user', JSON.stringify(user));
            this.authService.setRoleLocal("CHEF_DEPARTEMENT");
            this.router.navigate(['/utilisateurs'], {state: {u: this.user}});

          } else this.router.navigate(['/error']);
        }, error: (err) => {
          console.log(err);
          Swal.fire('Erreur', 'Erreur d\'authentification', 'error');
          const errorCode = err.status;
          if (errorCode === 403) {
            this.errorMessage = "Login ou mot de passe incorrect";
            console.log("Login ou mot de passe incorrect");
        }}});


    //}
  }


  Onregister() {

    this.user.username = this.user.nomSociete;
    this.header.ngOnInit(this.user);
    this.userService.addUser(this.user).subscribe({
      next: (response: any) => {
        Swal.fire('Succès', 'Inscription réussie', 'success');
        window.location.reload();
        this.ngOnInit();

    
      }

    });
  

    //redirect to appel d'offre
  }
  ngOnInit(): void {
  }


  save() {
    // alert(JSON.stringify(signUpRequest) );
    this.onSubmit();
    //console.log(this.user);
    // return this.httpClient.post<User>('http://localhost:8080/registration/signup', );
    //this.userService.addUser(this.user);
  }

}