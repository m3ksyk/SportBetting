package pl.coderslab.bets.service;

import pl.coderslab.bets.entity.User;

public interface UserService {
    User findByUsername(String username);
    void saveUser(User user);

    void save(User user);

    User findById(long id);
}

