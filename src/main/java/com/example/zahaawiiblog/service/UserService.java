package com.example.zahaawiiblog.service;

import com.example.zahaawiiblog.entity.User;
import com.example.zahaawiiblog.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserByUserId(long id) {
        return userRepository.findUserByUserId(id);
    }

    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    public void createNewUser(User user) {
        userRepository.save(user);
    }
}
