package pl.coderslab.bets.service;

import pl.coderslab.bets.entity.User;

public interface UserService {
    public User findByUsername(String username);
    public void saveUser(User user);
}

