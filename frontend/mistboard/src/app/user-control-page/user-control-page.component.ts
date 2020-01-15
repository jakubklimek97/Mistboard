import { Component, OnInit } from '@angular/core';
import {log} from 'util';
import {UserService} from '../user.service';
import {User} from '../pojo/user';
import {Game} from '../pojo/game';

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
  constructor(userService: UserService) {
    this.userService = userService;
    this.currentUser = this.userService.getCurrentUser();
    this.currentGamePage = 1;
    this.lastGamePage = Math.ceil(this.currentUser.createdGames.length / 5);
  }

  ngOnInit() {
  }
  changePassword() {
    log('Zmiana hasla');
  }
  getGamesPage(page: number): Game[] {
    const startIndex: number = (page - 1) * 5;
    return this.currentUser.createdGames.slice(startIndex, startIndex + 5);
  }
  nextGamePage() {
    this.currentGamePage = this.currentGamePage + 1;
  }
  previousGamePage() {
    this.currentGamePage = this.currentGamePage - 1;
  }
}
