import { Component, OnInit } from '@angular/core';
import {AppService} from "../app.service";
import {logger} from "codelyzer/util/logger";
import {Router} from "@angular/router";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  login: string;
  password: string;
  appService: AppService;
  router: Router;
  constructor(appService: AppService, router: Router) {
    this.appService = appService;
    this.router = router;
  }

  ngOnInit() {
  }

  public signIn() {
     if(this.appService.signIn(this.login, this.password)){
       this.router.navigateByUrl('/');
     }
  }


}
