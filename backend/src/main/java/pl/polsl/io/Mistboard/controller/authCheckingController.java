package pl.polsl.io.Mistboard.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.polsl.io.Mistboard.pojo.User;
@CrossOrigin
@RestController
@RequestMapping("/auth")
public class authCheckingController {
    @GetMapping(path = "")
    public @ResponseBody  User checkAuthentication(Authentication authentication){
        UserDetails userDetails = (UserDetails)authentication.getPrincipal();
        User reply = new User();
        reply.setEmail(userDetails.getUsername());
        reply.setRole(userDetails.getAuthorities().toArray()[0].toString());
        return reply;
    }
}
