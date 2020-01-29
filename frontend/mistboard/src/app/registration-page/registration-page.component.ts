import { Component, OnInit } from '@angular/core';
import {Subject} from 'rxjs';
import {debounceTime} from 'rxjs/operators';
import {log} from 'util';
import {User} from '../pojo/user';
import {HttpClient, HttpHeaders, HttpRequest} from '@angular/common/http';

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
  private email: string;
  private password: string;
  private passwordInputEmitter: Subject<string> = new Subject<string>();
  private passwordRepeat: string;
  private passwordRepeatInputEmitter: Subject<string> = new Subject<string>();
  private passwordRepeatEntered: boolean;
  private passwordRepeatOk: boolean;
  constructor(private http: HttpClient) { }
  ngOnInit() {
    this.email = '';
    this.emailOk = false;
    this.emailEntered = false;
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

  register() {
      const newUser: User = new User();

      newUser.email = this.email;
      newUser.password = this.password;
      newUser.id = 0;

      this.http.put<boolean>('http://localhost:4200/api/user', newUser).subscribe((response: boolean) => {
          if (response === true) {
            console.log('TRUE');
          } else {
            console.log('FALSE');
        }
      });
  }



}
