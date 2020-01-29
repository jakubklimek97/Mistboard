import { Component, OnInit } from '@angular/core';
import {log} from 'util';
import {UserService} from '../user.service';
import {User} from '../pojo/user';
import {Game} from '../pojo/game';
import {HttpClient} from "@angular/common/http";
import {AppService} from "../app.service";

@Component({
  selector: 'app-user-control-page',
  templateUrl: './user-control-page.component.html',
  styleUrls: ['./user-control-page.component.css']
})
export class UserControlPageComponent implements OnInit {
  userService: UserService;
  currentUser: User;
  currentGamePage: number;
  lastGamePage: number;
  createdGames: Game[];
  constructor(userService: UserService, private http: HttpClient, private appService: AppService) {
    this.userService = userService;
    this.currentUser = this.userService.getCurrentUser();
    this.currentGamePage = 1;
    this.lastGamePage = 1;
    this.createdGames = [];
    this.downloadUserGameList();
  }

  ngOnInit() {
  }
  changePassword() {
    log('Zmiana hasla');
  }
  getGamesPage(page: number): Game[] {
    const startIndex: number = (page - 1) * 5;
    return this.createdGames.slice(startIndex, startIndex + 5);
  }
  nextGamePage() {
    this.currentGamePage = this.currentGamePage + 1;
  }
  previousGamePage() {
    this.currentGamePage = this.currentGamePage - 1;
  }
  downloadUserGameList() {
    console.log( this.appService.getHeaders());
    this.createdGames = [];
    this.http.get<Game[]>(
      'http://localhost:4200/api/game/user',
      {headers: this.appService.getHeaders()}
    ).subscribe((response: Game[]) => {
      this.createdGames = response;
      this.currentGamePage = 1;
      this.lastGamePage = Math.ceil(this.createdGames.length / 5);
    });
    this.getGamesPage(1);
  }

}
