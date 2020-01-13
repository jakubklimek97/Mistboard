import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {Router, RouterModule, Routes} from '@angular/router';
import {GamesPageComponent} from './games-page/games-page.component';
import {LoginComponent} from './login/login.component';

const routes: Routes = [
  { path: 'games', component: GamesPageComponent},
  { path: 'login', component: LoginComponent}
]

@NgModule({
  declarations: [],
  imports: [
    RouterModule.forRoot(routes)
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
