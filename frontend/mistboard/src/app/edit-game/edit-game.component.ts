import {Component, OnInit} from '@angular/core';
import {AppService} from '../app.service';
import {UserService} from '../user.service';
import {ActivatedRoute} from '@angular/router';
import {Game} from '../pojo/game';
import {OperatingSystem} from "../pojo/operatingSystem";
import {HttpClient, HttpHeaders} from "@angular/common/http";

@Component({
  selector: 'app-edit-game',
  templateUrl: './edit-game.component.html',
  styleUrls: ['./edit-game.component.css']
})
export class EditGameComponent implements OnInit {
  appService: AppService;
  userService: UserService;
  route: ActivatedRoute;
  checkboxWindows = false;
  checkboxLinux = true;
  checkboxMacos = false;
  title = '';
  description = '';
  productionYear: number;
  selectedFiles: string[];
  id: number;
  game: Game;

  titleOk = false;
  descriptionOk = false;
  yearOk = true;
  constructor(appService: AppService, userService: UserService, route: ActivatedRoute, private http: HttpClient) {
    this.route = route;
    this.appService = appService;
    this.userService = userService;
    this.id = +this.route.snapshot.paramMap.get('id');
    if (this.id !== 0) {
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
    if (this.titleOk && this.descriptionOk && this.yearOk) {
      let newOs: OperatingSystem = this.checkboxWindows ? OperatingSystem.WINDOWS :
        (this.checkboxLinux ? OperatingSystem.LINUX : OperatingSystem.MACOS);
      if(this.id === 0) {
        let game: Game = {
          id: 0,
          title: this.title,
          description: {
            productionYear: this.productionYear,
            os: newOs,
            id: null,
            textDescription: this.description
          }
        };
        const header: HttpHeaders = this.appService.getHeaders();
        this.http.post<Game>('http://localhost:4200/api/game/edit/0', game, {headers: header}).subscribe((response: Game) => {
          if (response != null) {
            const newUrl = '/editGame/' + response.id;
            this.appService.navigateTo(newUrl);
          }
        });
      } else {
        this.game.title = this.title;
        this.game.description.os = newOs;
        this.game.description.productionYear = this.productionYear;
        this.game.description.textDescription = this.description;
        const header: HttpHeaders = this.appService.getHeaders();
        this.http.post<Game>('http://localhost:4200/api/game/edit/' + this.id, this.game,
          {headers: header}).subscribe((response: Game) => {
          if (response != null) {
            const newUrl = '/editGame/' + response.id;
            this.appService.navigateTo(newUrl);
          }
        });
      }
    }
    return true;
  }
  fetchGame() {
    this.http.get<Game>('http://localhost:4200/api/game/' + this.id,
      {headers: this.appService.getHeaders()}).subscribe(
      (response: Game) => {
        this.productionYear = response.description.productionYear;
        this.title = response.title;
        switch (response.description.os) {
          case OperatingSystem.WINDOWS: this.checkboxWindows = true; break;
          case OperatingSystem.LINUX: this.checkboxLinux = true; break;
          case OperatingSystem.MACOS: this.checkboxMacos = true; break;
        }
        this.description = response.description.textDescription;
        this.titleChanged(this.title);
        this.yearChanged();
        this.descriptionChanged(this.description);
        this.game = response;
      }
    );
  }
}
