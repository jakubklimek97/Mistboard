package pl.polsl.io.Mistboard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import pl.polsl.io.Mistboard.pojo.Password;
import pl.polsl.io.Mistboard.pojo.User;
import pl.polsl.io.Mistboard.repository.PasswordRepository;

import java.util.Optional;

@Service
public class PasswordServiceImplementation implements passwordService {

    @Autowired
    PasswordRepository passwordRepository;
    @Override
    public String getPassword(User user) {
        Optional<Password> passwObj = passwordRepository.findById(user.getId());
        if(passwObj.isPresent()) {
            return passwObj.get().getPassword();
        }
        else{
            return "";
        }
    }

    @Override
    public boolean setPassword(User user, String password) {
        Optional<Password> passwObj = passwordRepository.findById(user.getId());
        if(passwObj.isPresent()) {
            Password passw  = passwObj.get();
            passw.setPassword(password);
            return true;
        }
        else{
            Password passw = new Password(user);
            passw.setPassword(password);
            passwordRepository.save(passw);
            return true;
        }
    }

    @Override
    public boolean checkPassword(String password) {
        return true;
    }
}
