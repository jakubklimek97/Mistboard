import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AppService {
  private isLogged: boolean;
  private userName: string;
  constructor() {
      this.isLogged = false;
      this.userName = 'User';
  }

  getUsername(): string {
      return this.isLogged ? this.userName : 'Guest';
  }
  public isSignedIn(): boolean {
    return this.isLogged;
  }
  public signIn(login: string, passw: string): boolean{
    if(login === 'janusz' && passw === 'janusz'){
      this.isLogged = true;
      this.userName = 'Janusz';
      return true;
    }
    return false;
  }
  public logout() {
    this.userName = 'User';
    this.isLogged = false;
  }
}
