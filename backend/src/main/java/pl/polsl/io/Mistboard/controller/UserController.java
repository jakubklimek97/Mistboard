package pl.polsl.io.Mistboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.polsl.io.Mistboard.pojo.Role;
import pl.polsl.io.Mistboard.pojo.User;
import pl.polsl.io.Mistboard.repository.UserRepository;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserRepository userRepository;
    @GetMapping(path="")
    public @ResponseBody
    Iterable<User> getAllGames(){
        Iterable<User> allUsers =  userRepository.findAll();
        return allUsers;
    }

    @PutMapping(path = "")
    public @ResponseBody Integer addExampleUser(){
        User newUser = new User();
        newUser.setEmail("test@example.com");
        newUser.setRole(Role.USER);
        userRepository.save(newUser);
        return newUser.getId();
    }
}
