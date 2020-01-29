package pl.polsl.io.Mistboard.service;

import pl.polsl.io.Mistboard.pojo.Game;
import pl.polsl.io.Mistboard.pojo.User;

import java.util.List;

public interface GameService {
    List<Game> getAllGames();
    List<Game> getAllGames(User user);
    void updateGame(Game game);
    Game addGame(Game game);
    Game getGame(int id);
}
