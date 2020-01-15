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
    const user: User = {
      id: 0,
    email: 'example@ex.org',
    role: Role.ADMINISTRATOR,
      createdGames: [
        {
          id: 1,
          title: 'EuroTrakSymulator',
          description: {id: 1, os: OperatingSystem.WINDOWS, productionYear: 2005}
          },
        {
          id: 2,
          title: 'TonyHołkProSkejter',
          description: {id: 2, os: OperatingSystem.WINDOWS, productionYear: 2003}
        },
        {
          id: 3,
          title: 'krejzis',
          description: {id: 3, os: OperatingSystem.WINDOWS, productionYear: 2005}
        },
        {
          id: 4,
          title: 'TonyHołkProSkejter3',
          description: {id: 4, os: OperatingSystem.WINDOWS, productionYear: 2003}
        },
        {
          id: 5,
          title: 'łarkraft',
          description: {id: 5, os: OperatingSystem.WINDOWS, productionYear: 2005}
        },
        {
          id: 6,
          title: 'TonyHołkProSkejter5',
          description: {id: 6, os: OperatingSystem.WINDOWS, productionYear: 2003}
        }
        ]
    };
    return user;
  }



}
