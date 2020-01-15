import { Injectable } from '@angular/core';
import {User} from "./pojo/user";
import {UserService} from "./user.service";
import {Role} from "./pojo/role";
import {Router} from "@angular/router";

@Injectable({
  providedIn: 'root'
})
export class AppService {
  private isLogged: boolean;
  private userName: string;
  private currentUser: User;
  private userService: UserService;
  constructor(userService: UserService, private router: Router) {
      this.isLogged = false;
      this.userName = 'User';
      this.userService = userService;
  }

  getUsername(): string {
      return this.isLogged ? this.userName : 'Guest';
  }
  getRole(): Role {
    return this.currentUser.role;
  }
  public isSignedIn(): boolean {
    return this.isLogged;
  }
  public signIn(login: string, passw: string): boolean{
    if(login === 'janusz' && passw === 'janusz'){
      this.isLogged = true;
      this.userName = 'Janusz';
      this.currentUser =  this.userService.getCurrentUser();
      return true;
    }
    return false;
  }
  public logout() {
    this.userName = 'User';
    this.isLogged = false;
    this.router.navigateByUrl('');
  }
}
