package pl.polsl.io.Mistboard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.polsl.io.Mistboard.pojo.User;
import pl.polsl.io.Mistboard.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImplementation implements userService {
    @Autowired
    UserRepository userRepository;

    @Override
    public boolean addUser(User user) {
        if(user == null)
            return false;
        List<User> existingUsers;
        existingUsers = userRepository.findByEmailIgnoreCase(user.getEmail());
        if(existingUsers.size() > 0){
            return false;
        }
        else{
            User returned = userRepository.save(user);
            return true;
        }
    }

    @Override
    public List<User> getAllUsers() {
        Iterable<User> userIterator = userRepository.findAll();
        List<User> userList = new ArrayList<User>();
        userIterator.forEach(userList::add);
        return userList;
    }

    @Override
    public User getUserById(int id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            return user.get();
        }
        else {
            return null;
        }
    }

    @Override
    public List<User> getUserByEmail(String email) {
        return userRepository.findByEmailIgnoreCase(email);
    }

    @Override
    public User updateUser(User user) {
        if (user.getId() == 0) {
            User newUser = new User();
            newUser.setEmail(user.getEmail());
            newUser.setRole("ROLE_USER");
            newUser.setPassword(user.getPassword());
            return userRepository.save(newUser);
        } else {
            Optional<User> userInDb = userRepository.findById(user.getId());
            if (userInDb.isPresent()) {
                User presentUser = userInDb.get();
                //presentUser.setRole(user.getRole());
                presentUser.setEmail(user.getEmail());
                presentUser.setPassword(user.getPassword());
                return userRepository.save(presentUser);
            }
            return null;
        }
    }

    @Override
    public String getCurrentUserEmail() {
        return "Placeholder";
    }
}
