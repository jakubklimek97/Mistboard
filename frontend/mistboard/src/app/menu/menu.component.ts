import { Component, OnInit } from '@angular/core';
import {AppService} from "../app.service";
import {UserService} from "../user.service";
import {Role} from "../pojo/role";

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {
  private appService: AppService;
  private userService: UserService;
  constructor(appService: AppService, userService: UserService) {
    this.appService = appService;
    this.userService = userService;
  }

  ngOnInit() {
  }
  logout() {
    this.appService.logout();
  }
}
