import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';


import { AppComponent } from './app.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { AuthService } from "./services/auth.service";
import {HttpModule} from "@angular/http";
import {AccountService} from "./services/account.service";
import { ProfileComponent } from './components/profile/profile.component';
import {routing} from "./app.routing";
import {FacebookModule} from "ngx-facebook";
import {UrlPermission} from "./urlPermission/url.permission";
import {HomeComponent} from "./containers/home/home.component";
import {PollsService} from "./services/polls.service";
import {MaterialModule} from "./modules/material.module";
import {PollsContainerComponent} from "./containers/polls-container/polls-container.component";
import {PollComponent} from "./components/poll/poll.component";
import {NewPollComponent} from "./components/new-poll/new-poll.component";


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    NewPollComponent,
    ProfileComponent,
    PollComponent,
    HomeComponent,
    PollsContainerComponent
  ],
  imports: [
    ReactiveFormsModule,
    MaterialModule,
    BrowserModule,HttpModule,FormsModule,routing, FacebookModule.forRoot(),
  ],
  providers: [AuthService,AccountService,UrlPermission, PollsService],
  bootstrap: [AppComponent]
})
export class AppModule { }
