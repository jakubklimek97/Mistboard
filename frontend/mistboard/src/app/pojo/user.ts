import {Role} from "./role";
import {Game} from "./game";

export class User {
     public  id: number;
       public email: string;
     public role: string;
     createdGames: Game[];
     public password: string;
}
