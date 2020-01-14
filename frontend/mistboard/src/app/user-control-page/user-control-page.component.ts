import { Component, OnInit } from '@angular/core';
import {log} from "util";

@Component({
  selector: 'app-user-control-page',
  templateUrl: './user-control-page.component.html',
  styleUrls: ['./user-control-page.component.css']
})
export class UserControlPageComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }
  changePassword() {
    log('Zmiana hasla');
  }
}
