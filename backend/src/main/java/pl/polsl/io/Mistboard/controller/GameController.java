package pl.polsl.io.Mistboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.polsl.io.Mistboard.pojo.Description;
import pl.polsl.io.Mistboard.pojo.Game;
import pl.polsl.io.Mistboard.pojo.User;
import pl.polsl.io.Mistboard.repository.DescriptionRepository;
import pl.polsl.io.Mistboard.repository.GameRepository;
import pl.polsl.io.Mistboard.repository.UserRepository;

import java.util.Optional;

@Controller
@RequestMapping("/game")
public class GameController {
    @Autowired
    private GameRepository gameRepository;


    //temporary
    @Autowired
    private DescriptionRepository descriptionRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping(path="")
    public @ResponseBody Iterable<Game> getAllGames(){
        return gameRepository.findAll();
    }
    @PutMapping(path = "")
    public @ResponseBody Integer createNewGame(){
        Description desc = new Description();

        Game game = new Game();
        desc.setGame(game);

        gameRepository.save(game);
        descriptionRepository.save(desc);
        return game.getId();
    }

    @GetMapping(path = "/{id}")
    @ResponseBody
    public Optional<Game> getGameById(@PathVariable(value = "id") Integer id){
        Optional<Game> game = gameRepository.findById(id);
        return game;
    }

    @GetMapping(path = "/add/")
    public @ResponseBody Iterable<Game> addAndReturn(){
      Game game = new Game();
      game.setTitle("Hello World");
      gameRepository.save(game);
      return gameRepository.findAll();
    }

    @PostMapping(path = "/{id}")
    public @ResponseBody Game editGame(@RequestBody Game modifiedGame, @PathVariable(name="id") Integer id){
        Optional<Game> game = gameRepository.findById(id);

        if(game.isPresent()){
            if(game.get().getId() == id){
                game.get().setAuthor(userRepository.findById(1).get());
                game.get().setTitle(modifiedGame.getTitle());
                game.get().getDescription().setOs(modifiedGame.getDescription().getOs());
                game.get().getDescription().setProductionYear(modifiedGame.getDescription().getProductionYear());
                gameRepository.save(game.get());
                return(game.get());
            }

        }
        return modifiedGame;
    }

}
