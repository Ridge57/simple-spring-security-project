package com.example.demo.service;

import com.example.demo.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;


public class UserService implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    @Cacheable(value = "users",key = "#username")
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("loadUserByUsername : recherche du user {}",username);
        com.example.demo.model.User foundUser = userRepository.getUsers()
                .stream()
                .filter(user->user.getUsername().equals(username))
                .findFirst()
                .orElseThrow(()->new RuntimeException("Le user n'a pas été trouvé"));
        logger.info("loadUserByUsername : user {} trouvé !",username);
        return new User(foundUser.getUsername(), foundUser.getPassword(), List.of());
    }
}
