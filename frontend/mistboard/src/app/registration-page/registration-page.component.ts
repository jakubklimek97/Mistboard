import { Component, OnInit } from '@angular/core';
import {Subject} from "rxjs";
import {debounceTime} from "rxjs/operators";
import {log} from "util";

@Component({
  selector: 'app-registration-page',
  templateUrl: './registration-page.component.html',
  styleUrls: ['./registration-page.component.css']
})
export class RegistrationPageComponent implements OnInit {
  private loginOk: boolean;
  private loginEntered: boolean;
  private loginTaken: boolean;
  private emailOk: boolean;
  private emailEntered: boolean;
  private passwordOk: boolean;
  private password2Ok: boolean;
  private passwordEntered: boolean;
  private password2Entered: boolean;
  private password: string;
  private passwordInputEmitter: Subject<string> = new Subject<string>();
  private passwordRepeat: string;
  private passwordRepeatInputEmitter: Subject<string> = new Subject<string>();
  private passwordRepeatEntered: boolean;
  private passwordRepeatOk: boolean;
  constructor() { }
  ngOnInit() {
    log("Hello");
    this.loginOk = true;
    this.emailOk = false;
    this.emailEntered = false;
    this.loginEntered = false;
    this.passwordEntered = false;
    this.passwordOk = false;
    this.passwordInputEmitter.pipe(debounceTime(1000)).subscribe(val => {
      this.passwordEntered = true;
      this.password = val;
      if (this.password.length < 5) {
        this.passwordOk = false;
      } else {
        this.passwordOk = true;
      }
    });
    this.passwordRepeatInputEmitter.pipe(debounceTime(1000)).subscribe(val => {
      this.passwordRepeatEntered = true;
      this.passwordRepeat = val;
      this.passwordRepeatOk =  (this.password === this.passwordRepeat) ? true : false;
      log(this.passwordRepeatOk);
    });
  }
  passwordChanged(value: string) {
    this.passwordInputEmitter.next(value);
  }
  passwordRepeatChanged(value: string) {
    this.passwordRepeatInputEmitter.next(value);
  }

}
