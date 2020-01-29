package pl.polsl.io.Mistboard.service;

import org.springframework.beans.factory.annotation.Autowired;
import pl.polsl.io.Mistboard.pojo.Game;
import pl.polsl.io.Mistboard.pojo.User;
import pl.polsl.io.Mistboard.repository.GameRepository;

import java.util.List;

public class GameServiceImplementation implements GameService {

    @Autowired
    GameRepository gameRepository;

    @Override
    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    @Override
    public List<Game> getAllGames(User user) {
        return gameRepository.findByAuthor(user);
    }

    @Override
    public void updateGame(Game game) {
        gameRepository.save(game);
    }

    @Override
    public Game addGame(Game game) {
        return gameRepository.save(game);
    }

    @Override
    public Game getGame(int id) {
        return gameRepository.findById(id);
    }
}
