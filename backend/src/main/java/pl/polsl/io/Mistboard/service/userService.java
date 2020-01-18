package pl.polsl.io.Mistboard.service;

import pl.polsl.io.Mistboard.pojo.User;

import java.util.List;

public interface userService {
    boolean addUser(User user);
    List<User> getAllUsers();
    User getUserById(int id);
    List<User> getUserByEmail(String email);
    User updateUser(User user);
    String getCurrentUserEmail();
}
