import { Routes, RouterModule } from '@angular/router';
import {LoginComponent} from "./components/login/login.component";
import {RegisterComponent} from "./components/register/register.component";
import {ProfileComponent} from "./components/profile/profile.component";
import {UrlPermission} from "./urlPermission/url.permission";
import {HomeComponent} from "./containers/home/home.component";


const appRoutes: Routes = [
  { path: 'profile', component: ProfileComponent, canActivate: [UrlPermission] },
  { path: 'home', component: HomeComponent, canActivate: [UrlPermission] },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },

  // otherwise redirect to profile
  { path: '**', redirectTo: '/login' }
];

export const routing = RouterModule.forRoot(appRoutes);
