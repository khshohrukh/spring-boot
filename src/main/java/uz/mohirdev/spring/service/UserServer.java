package uz.mohirdev.spring.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.mohirdev.spring.entity.User;
import uz.mohirdev.spring.repository.UserRepository;

@Service
public class UserServer {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserServer(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User save(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public boolean existsByLogin(String login){
        return userRepository.existsByLogin(login);
    }
}
