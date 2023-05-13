import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './components/header/header.component';
import { HomeComponent } from './components/home/home.component';
import { FooterComponent } from './components/footer/footer.component';
import { SignInComponent } from './components/sign-in/sign-in.component';
import { SignUpComponent } from './components/sign-up/sign-up.component';
import { SideNavbarComponent } from './components/side-navbar/side-navbar.component';
import { SideBarChatComponent } from './components/side-bar-chat/side-bar-chat.component';
import { UIElementsComponent } from './components/ui-elements/ui-elements.component';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule,ReactiveFormsModule } from '@angular/forms';
import { UserService } from './components/sign-up/user.service';
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
import { PropositionFournisseurComponent } from './components/proposition-fournisseur/proposition-fournisseur.component';
import { AppelOffreFournisseurComponent } from './components/appel-offre-fournisseur/appel-offre-fournisseur.component';
import { AddPropositionFournisseurComponent } from './components/add-proposition-fournisseur/add-proposition-fournisseur.component';

import { ErrorComponent } from './components/error/error.component';
import { PropositionResponsableComponent } from './components/proposition-responsable/proposition-responsable.component';
import { MessagesComponent } from './components/message/message.component';
import { MessageFrameComponent } from './components/message/message-frame/message-frame.component';
import {MatPaginatorModule} from "@angular/material/paginator";
import { ConstatRespoComponent } from './components/constat-respo/constat-respo.component';
import { BesoinChefDepComponent } from './components/besoin-chef-dep/besoin-chef-dep.component';

import { BesoinAffComponent } from './components/besoin-aff/besoin-aff.component';
import { BesoinRespoComponent } from './components/besoin-respo/besoin-respo.component';
import { PropositionFournisseurDetailsComponent } from './components/proposition-fournisseur-details/proposition-fournisseur-details.component';
import { AffectationComponent } from './components/affectation/affectation.component';
import { AffectationEnsComponent } from './components/affectation-ens/affectation-ens.component';


@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    HomeComponent,
    FooterComponent,
    SignInComponent,
    SignUpComponent,
    SideNavbarComponent,
    SideBarChatComponent,
    UIElementsComponent,
    BesoinComponent,
    AcceuilComponent,
    PanneComponent,
    ConstatComponent,
    PanneEnsComponent,
    RessourceComponent,
    BesoinLivrableComponent,
    AppelOffreComponent,
    AppelOffreDetailsComponent,
    UtilisateursComponent,
    PropositionFournisseurComponent,
    AppelOffreFournisseurComponent,
    AddPropositionFournisseurComponent,

    ErrorComponent,

    PropositionResponsableComponent,
    MessagesComponent,
    MessageFrameComponent,
    ConstatRespoComponent,
    BesoinChefDepComponent,
    BesoinAffComponent,
    BesoinRespoComponent,
    
    PropositionFournisseurComponent,
    AppelOffreFournisseurComponent,
    AddPropositionFournisseurComponent,
    PropositionResponsableComponent,
    PropositionFournisseurDetailsComponent,
    AffectationComponent,
    AffectationEnsComponent

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    MatPaginatorModule

  ],
  providers: [UserService],
  bootstrap: [AppComponent]
})
export class AppModule { }
