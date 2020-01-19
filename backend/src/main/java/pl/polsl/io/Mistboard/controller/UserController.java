package pl.polsl.io.Mistboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.polsl.io.Mistboard.pojo.Password;
import pl.polsl.io.Mistboard.pojo.Role;
import pl.polsl.io.Mistboard.pojo.User;
import pl.polsl.io.Mistboard.repository.UserRepository;
import pl.polsl.io.Mistboard.service.passwordService;
import pl.polsl.io.Mistboard.service.userService;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    userService userServiceImplementation;

    @Autowired
    passwordService passwordServiceImplementation;

    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping(path = "")
    public @ResponseBody String getCurrentUserEmail(){

        return new String("Hello world");
    }
    @PutMapping(path = "")
    public @ResponseBody boolean updateUser(@RequestBody User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if(user.getId() == 0 ){
            User newUser = userServiceImplementation.updateUser(user);
        }
        else{
            User modifiedUser = userServiceImplementation.updateUser(user);
        }
        return true;
    }
    @GetMapping(path = "/all")
    public @ResponseBody List<User> getAllUsers(Authentication authentication){
        UserDetails userDetails = (UserDetails)authentication.getPrincipal();
        System.out.println(userDetails.getAuthorities());
        List<User> userList = userServiceImplementation.getAllUsers();
        return userList;
    }
    @PutMapping(path = "/email")
    public @ResponseBody boolean isEmailAvailable(@RequestBody String email){
        List<User> users = userServiceImplementation.getUserByEmail(email);
        return !users.isEmpty();
    }


}
