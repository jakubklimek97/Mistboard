import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {Router, RouterModule, Routes} from '@angular/router';
import {GamesPageComponent} from './games-page/games-page.component';
import {LoginComponent} from './login/login.component';
import {RegistrationPageComponent} from "./registration-page/registration-page.component";
import {UserControlPageComponent} from "./user-control-page/user-control-page.component";

const routes: Routes = [
  { path: 'games', component: GamesPageComponent},
  { path: 'login', component: LoginComponent},
  {path: 'register', component: RegistrationPageComponent},
  {path: 'user', component: UserControlPageComponent}
]

@NgModule({
  declarations: [],
  imports: [
    RouterModule.forRoot(routes)
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
