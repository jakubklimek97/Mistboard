import { Component, OnInit } from '@angular/core';
import {AppService} from "../app.service";
import {log} from "util";
import {UserService} from "../user.service";
import {ActivatedRoute} from "@angular/router";
import {Game} from "../pojo/game";

@Component({
  selector: 'app-edit-game',
  templateUrl: './edit-game.component.html',
  styleUrls: ['./edit-game.component.css']
})
export class EditGameComponent implements OnInit {
  appService: AppService;
  userService: UserService;
  route: ActivatedRoute;
  checkboxWindows: boolean = false;
  checkboxLinux: boolean = true;
  checkboxMacos: boolean = false;
  title: string = '';
  description: string = '';
  productionYear: number;
  selectedFiles: string[];
  id: number;

  titleOk: boolean = false;
  descriptionOk: boolean = false;
  yearOk: boolean = false;
  constructor(appService: AppService, userService: UserService, route: ActivatedRoute) {
    this.route = route;
    this.appService = appService;
    this.userService = userService;
    this.id = +this.route.snapshot.paramMap.get('id');
    if(this.id !== 0) {
      this.fetchGame();
    }
  }

  ngOnInit() {
  }
  titleChanged(text: string) {
    if (text.length > 0) {
      this.titleOk = true;
    } else {
      this.titleOk = false;
    }
  }
  descriptionChanged(text: string) {
    if (text.length > 0) {
      this.descriptionOk = true;
    } else {
      this.descriptionOk = false;
    }
  }
  yearChanged() {
    this.yearOk = true;
  }
  addGame(): boolean {
    return true;
  }
  fetchGame() {
    let game: Game = this.userService.getCurrentUser().createdGames[this.id - 1];
    this.productionYear = game.description.productionYear;
    this.title = game.title;
    this.titleChanged(this.title);
    this.yearChanged();
  }
}
