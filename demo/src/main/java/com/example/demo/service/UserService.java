package com.example.demo.service;

import com.example.demo.repository.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;


public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("loadUserByUsername");
        com.example.demo.model.User foundUser = userRepository.getUsers()
                .stream()
                .filter(user->user.getUsername().equals(username))
                .findFirst()
                .orElseThrow(()->new RuntimeException("Le user n'a pas été trouvé"));
        System.out.println(foundUser);

        return new User(foundUser.getUsername(), foundUser.getPassword(), List.of());
    }
}
