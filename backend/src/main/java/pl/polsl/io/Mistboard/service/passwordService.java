package pl.polsl.io.Mistboard.service;

import pl.polsl.io.Mistboard.pojo.User;

public interface passwordService {
    String getPassword(User user);
    boolean setPassword(User user, String password);
    boolean checkPassword(String password);
}
