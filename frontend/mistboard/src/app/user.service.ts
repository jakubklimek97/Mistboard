import { Injectable } from '@angular/core';
import {User} from "./pojo/user";
import {Role} from "./pojo/role";

@Injectable({
  providedIn: 'root'
})
export class UserService {
  constructor() { }
  getCurrentUser(): User {
    let user: User = {
      id: 0,
    email: 'example@ex.org',
    role: Role.ADMINISTRATOR
    };
    return user;
  }



}
