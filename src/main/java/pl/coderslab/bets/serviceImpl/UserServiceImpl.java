package pl.coderslab.bets.serviceImpl;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.coderslab.bets.entity.User;
import pl.coderslab.bets.repository.UserRepository;
import pl.coderslab.bets.service.UserService;

import java.math.BigDecimal;
import java.util.Arrays;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    @Override
    public void saveUser(User user) {

//        user.setWallet(BigDecimal.valueOf(10));

//        user.setCryptSalt(BCrypt.gensalt());
//        user.setPassword(BCrypt.hashpw(user.getPassword(), user.getCryptSalt()));

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }
}
