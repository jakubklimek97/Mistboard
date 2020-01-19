import { Component, OnInit } from '@angular/core';
import {AppService} from "../app.service";
import {logger} from "codelyzer/util/logger";
import {Router} from "@angular/router";
import {LoginData} from "../pojo/loginData";
import {log} from "util";

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
    this.appService.newLogin();
  }

  ngOnInit() {
  }

  public signIn() {
     /*if(this.appService.signIn(this.login, this.password)){
       this.router.navigateByUrl('/');
     }*/
     log("KLIK ZALOGUJ");
     const loginData: LoginData = {
       email: this.login,
       password: this.password
     };
     this.appService.authenticate(loginData);
  }


}
