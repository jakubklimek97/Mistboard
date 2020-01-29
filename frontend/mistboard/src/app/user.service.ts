import {Injectable} from '@angular/core';
import {User} from './pojo/user';
import {Role} from './pojo/role';
import {OperatingSystem} from './pojo/operatingSystem';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  constructor() { }
  getCurrentUser(): User {
    return null;
  }



}
