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
  public isSignedIn(): boolean{
    return this.isLogged;
  }
}
