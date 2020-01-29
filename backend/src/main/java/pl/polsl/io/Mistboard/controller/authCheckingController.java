package pl.polsl.io.Mistboard.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.polsl.io.Mistboard.pojo.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@CrossOrigin
@RestController
@RequestMapping("")
public class authCheckingController {
    @GetMapping(path = "/auth")
    public @ResponseBody  User checkAuthentication(Authentication authentication){
        UserDetails userDetails = (UserDetails)authentication.getPrincipal();
        User reply = new User();
        reply.setEmail(userDetails.getUsername());
        reply.setRole(userDetails.getAuthorities().toArray()[0].toString());
        return reply;
    }
    @RequestMapping(value="/logoff", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }
}
