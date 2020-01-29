import {Injectable} from '@angular/core';
import {User} from './pojo/user';
import {UserService} from './user.service';
import {Role} from './pojo/role';
import {Router} from '@angular/router';
import {LoginData} from './pojo/loginData';
import {HttpClient, HttpErrorResponse, HttpHeaders} from '@angular/common/http';
import {log} from "util";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class AppService {
  private isLogged: boolean;
  private userName: string;
  private currentUser: User;
  private userService: UserService;
  private loginData: LoginData;
  private authString: string;
  private wrongCredentials: boolean;
  constructor(userService: UserService, private router: Router,
              private http: HttpClient) {
      this.isLogged = false;
      this.userName = 'User';
      this.userService = userService;
      this.authString = '';
      this.wrongCredentials = false;
  }
  AreCredientalsWrong(): boolean{
    return this.wrongCredentials;
  }
  newLogin() {
    this.wrongCredentials = false;
  }
  getUsername(): string {
      return this.isLogged ? this.userName : 'Guest';
  }
  getRole(): Role {
    return Role.ADMINISTRATOR;
  }
  public isSignedIn(): boolean {
    return this.isLogged;
  }
  public signIn(login: string, passw: string): boolean {
    if (login === 'janusz' && passw === 'janusz') {
      this.isLogged = true;
      this.userName = 'Janusz';
      this.currentUser =  this.userService.getCurrentUser();
      return true;
    }
    return false;
  }
  public authenticate(loginData: LoginData) {
    this.loginData = loginData;
    const header = new HttpHeaders(loginData ? {
      Authorization: 'Basic ' +
        btoa(loginData.email + ':' + loginData.password)
    } : {}).set('X-Requested-With', 'XMLHttpRequest');
    this.http.get<User>
    ('http://localhost:4200/api/auth', {headers: header}).subscribe(
      (response: User) => {
        this.isLogged = true;
        this.currentUser = response;
        this.userName = this.currentUser.email;
        this.wrongCredentials = false;
      },
      (error: HttpErrorResponse) => {
        if (error.status === 401) {
          this.wrongCredentials = true;
        }
      }
    );
  }
  public getHeaders(): HttpHeaders {
    console.log('headers' + this.loginData);
    const header = new HttpHeaders(this.loginData ? {
      Authorization: 'Basic ' +
        btoa(this.loginData.email + ':' + this.loginData.password)
    } : {}).set('X-Requested-With', 'XMLHttpRequest');
    return header;
  }
  public logout() {
    this.userName = 'Zaloguj';
    this.isLogged = false;
    this.loginData = null;
    this.http.get('http://localhost:8080/logout', {headers: this.getHeaders()});
    this.router.navigateByUrl('/login');
  }
  public navigateTo(url: string) {
    this.router.navigateByUrl(url);
  }
}
