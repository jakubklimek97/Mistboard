import { Component, OnInit } from '@angular/core';
import {AppService} from "../app.service";

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {
  appService: AppService;
  constructor(appService: AppService) {
    this.appService = appService;
  }

  ngOnInit() {
  }

}
