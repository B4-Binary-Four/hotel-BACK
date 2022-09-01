package com.codingpals.hotel.service;

import com.codingpals.hotel.model.User;
import com.codingpals.hotel.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;


@Service
@AllArgsConstructor

public class UserService {
    private UserRepository userRepository ;

    @Transactional
    public User saveUser(User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        User found = findByUsername(user.getUsername());
        if (found == null) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            return userRepository.save(user);
        } else {
            found.setUsername(user.getUsername());
            found.setPassword(passwordEncoder.encode(user.getPassword()));
            found.setRole(user.getRole());
            found.setEnabled(user.isEnabled());
            return userRepository.save(found);
        }
    }

    public User findByUsername(String username){
        Optional<User> optUser = userRepository.findByUsername(username) ;
        return optUser.orElse(null);
    }
}
