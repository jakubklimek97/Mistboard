package pl.polsl.io.Mistboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.polsl.io.Mistboard.exception.ForbiddenException;
import pl.polsl.io.Mistboard.pojo.Description;
import pl.polsl.io.Mistboard.pojo.Game;
import pl.polsl.io.Mistboard.pojo.User;
import pl.polsl.io.Mistboard.repository.DescriptionRepository;
import pl.polsl.io.Mistboard.repository.GameRepository;
import pl.polsl.io.Mistboard.repository.UserRepository;
import pl.polsl.io.Mistboard.service.GameService;
import pl.polsl.io.Mistboard.service.GameServiceImplementation;
import pl.polsl.io.Mistboard.service.UserServiceImplementation;
import pl.polsl.io.Mistboard.service.userService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/game")
public class GameController {
    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private userService userServiceImplementation;
    @Autowired
    private GameService gameServiceImplementation;
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
    public Game getGameById(@PathVariable(value = "id") Integer id){
        Game game = gameServiceImplementation.getGame(id);
        return game;
    }

    @GetMapping(path = "/add/")
    public @ResponseBody Iterable<Game> addAndReturn(){
      Game game = new Game();
      game.setTitle("Hello World");
      gameRepository.save(game);
      return gameRepository.findAll();
    }

    @PostMapping(path = "/edit/{id}")
    public @ResponseBody Game editGame(@RequestBody Game modifiedGame,
                                       @PathVariable(name="id") Integer id,
                                       Authentication authentication){
        UserDetails userDetails = (UserDetails)authentication.getPrincipal();
        List <User> userList = userServiceImplementation.getUserByEmail(userDetails.getUsername());
        User user = userList.get(0);
        if(id == 0){
            Game newGame = new Game();
            newGame.setTitle(modifiedGame.getTitle());
            newGame.setAuthor(user);
            newGame.setDescription(new Description());
            newGame.getDescription().setGame(newGame);
            newGame.getDescription().setOs(modifiedGame.getDescription().getOs());
            newGame.getDescription().setProductionYear(modifiedGame.getDescription().getProductionYear());
            return gameServiceImplementation.addGame(newGame);
        }
        else {

            Game game = gameServiceImplementation.getGame(id);

            if (game != null) {

                if(user == game.getAuthor()){

                    if (game.getId() == id) {
                        game.setTitle(modifiedGame.getTitle());
                        game.getDescription().setOs(modifiedGame.getDescription().getOs());
                        game.getDescription().setProductionYear(modifiedGame.getDescription().getProductionYear());
                        gameRepository.save(game);
                        return (game);
                    }

                }
                else{
                    throw new ForbiddenException();
                }
            }
        }
        return modifiedGame;
    }

    @GetMapping(path = "/user")
    public @ResponseBody
    List<Game> getAllUserGames(Authentication authentication){
        UserDetails userDetails = (UserDetails)authentication.getPrincipal();
        List <User> userList = userServiceImplementation.getUserByEmail(userDetails.getUsername());
        User user = userList.get(0);
        List<Game> userGames = gameRepository.findByAuthor(user);
        return userGames;

    }


}
