import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AppComponent } from './app.component';
import { HeaderComponent } from './components/header/header.component';
import { HomeComponent } from './components/home/home.component';
import { FooterComponent } from './components/footer/footer.component';
import { SignInComponent } from './components/sign-in/sign-in.component';
import { SignUpComponent } from './components/sign-up/sign-up.component';
import { SideNavbarComponent } from './components/side-navbar/side-navbar.component';
import { SideBarChatComponent } from './components/side-bar-chat/side-bar-chat.component';
import { UIElementsComponent } from './components/ui-elements/ui-elements.component';
import { BesoinComponent } from './components/besoin/besoin.component';
import { AcceuilComponent } from './components/acceuil/acceuil.component';
import { PanneComponent } from './components/panne/panne.component';
import { ConstatComponent } from './components/constat/constat.component';
import { PanneEnsComponent } from './components/panne-ens/panne-ens.component';
import { RessourceComponent } from './components/ressource/ressource.component';
import { BesoinLivrableComponent } from './components/besoin-livrable/besoin-livrable.component';
import { AppelOffreComponent } from './components/appel-offre/appel-offre.component';
import { AppelOffreDetailsComponent } from './components/appel-offre-details/appel-offre-details.component';
import { UtilisateursComponent } from './components/utilisateurs/utilisateurs.component';
import { AppelOffreFournisseurComponent } from './components/appel-offre-fournisseur/appel-offre-fournisseur.component';
import { AddPropositionFournisseurComponent } from './components/add-proposition-fournisseur/add-proposition-fournisseur.component';
import { MessagesComponent } from './components/message/message.component';
import { ErrorComponent } from './components/error/error.component';
import { AuthGuard } from './models/auth-guard';
import { ConstatRespoComponent } from './components/constat-respo/constat-respo.component';
import { PropositionResponsableComponent } from './components/proposition-responsable/proposition-responsable.component';
import { HasRoleGuard } from './models/has-role-guard';
import { BesoinChefDepComponent } from './components/besoin-chef-dep/besoin-chef-dep.component';

import { BesoinAffComponent } from './components/besoin-aff/besoin-aff.component';
import { BesoinRespoComponent } from './components/besoin-respo/besoin-respo.component';
import { PropositionFournisseurComponent } from './components/proposition-fournisseur/proposition-fournisseur.component';
import { PropositionFournisseurDetailsComponent } from './components/proposition-fournisseur-details/proposition-fournisseur-details.component';
import { AffectationComponent } from './components/affectation/affectation.component';
import { AffectationEnsComponent } from './components/affectation-ens/affectation-ens.component';






const routes: Routes = [
  { path: '', component: AcceuilComponent },
  { path: 'home', component: HomeComponent , canActivate: [AuthGuard]},
  {path:'besoin', component: BesoinComponent, canActivate: [AuthGuard]},
  {path:'besoinDep', component: BesoinChefDepComponent, canActivate: [AuthGuard,HasRoleGuard],data:{role:['CHEF_DEPARTEMENT']}},

  {path:'besoinAff', component: BesoinAffComponent, canActivate: [AuthGuard,HasRoleGuard],data:{role:['CHEF_DEPARTEMENT']}},
  {path:'besoinResp', component: BesoinRespoComponent, canActivate: [AuthGuard,HasRoleGuard],data:{role:['RESPONSABLE']}},
  { path : 'header', component: HeaderComponent, canActivate: [AuthGuard]},
  { path : 'side-navbar', component: SideNavbarComponent , canActivate: [AuthGuard]},
  { path : 'side-bar-chat', component: SideBarChatComponent, canActivate: [AuthGuard]},
  {path : 'ui-elements' , component : UIElementsComponent, canActivate: [AuthGuard]},
  { path: 'sign-in', component: SignInComponent },
  { path: 'sign-up', component: SignUpComponent },
  { path: 'messages', component: MessagesComponent , canActivate: [AuthGuard]},

  { path:'panne', component: PanneComponent, canActivate: [AuthGuard,HasRoleGuard],data:{role:['TECHNICIEN']}},
  { path:'constatRespo', component: ConstatRespoComponent, canActivate: [AuthGuard,HasRoleGuard],data:{role:['RESPONSABLE']}},
  { path:'constat', component: ConstatComponent, canActivate: [AuthGuard,HasRoleGuard],data:{role:['TECHNICIEN']}},
  { path: 'constat/:idPanne', component: ConstatComponent , canActivate: [AuthGuard,HasRoleGuard],data:{role:['TECHNICIEN']}},
  { path: 'panne/:idPanne', component: PanneComponent, canActivate: [AuthGuard,HasRoleGuard],data:{role:['TECHNICIEN']} },
  { path: 'panneEns', component: PanneEnsComponent , canActivate: [AuthGuard,HasRoleGuard] ,data:{role:['ENSEIGNANT']}},
  { path: 'panneEns/:idPanne', component: PanneEnsComponent , canActivate: [AuthGuard,HasRoleGuard],data:{role:['ENSEIGNANT']}},
  { path: 'livraison', component: BesoinLivrableComponent, canActivate: [AuthGuard,HasRoleGuard],data:{role:['RESPONSABLE']}},
  { path: 'ressource', component: RessourceComponent , canActivate: [AuthGuard]},
  { path: 'ressource/:codeBarre', component: RessourceComponent, canActivate: [AuthGuard]},
  { path:'appelOffre', component: AppelOffreComponent , canActivate: [AuthGuard]},
  { path: 'appelOffre/:id', component: AppelOffreDetailsComponent , canActivate: [AuthGuard]},
  { path: 'utilisateurs', component: UtilisateursComponent , canActivate: [AuthGuard,HasRoleGuard],data:{role:['RESPONSABLE','CHEF_DEPARTEMENT']}},
  { path:'appelOffreFournisseur', component: AppelOffreFournisseurComponent , canActivate: [AuthGuard,HasRoleGuard], data:{role:['FOURNISSEUR']}},
  { path:'proposition/appelOffre/:id', component: AddPropositionFournisseurComponent , canActivate: [AuthGuard,HasRoleGuard], data:{role:['FOURNISSEUR']}},
  { path: 'proposition', component: PropositionResponsableComponent , canActivate: [AuthGuard,HasRoleGuard],data:{role:['RESPONSABLE']}},
  { path: 'proposition/fournisseur', component: PropositionFournisseurComponent, data:{role:['FOURNISSEUR']} },
  { path: 'proposition/fournisseur/details/:id', component: PropositionFournisseurDetailsComponent , data:{role:['FOURNISSEUR']}},
  { path: 'affectation', component: AffectationComponent , canActivate: [AuthGuard,HasRoleGuard],data:{role:['RESPONSABLE']} },
  {path: 'affectationEns', component: AffectationEnsComponent , canActivate: [AuthGuard,HasRoleGuard],data:{role:['ENSEIGNANT','CHEF_DEPARTEMENT']}},
  { path:'error', component: ErrorComponent  , pathMatch: 'full'},
  //{path: '**', redirectTo: 'error', pathMatch: 'full'}



];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
