package com.example.zahaawiiblog.controller;


import com.example.zahaawiiblog.entity.User;
import com.example.zahaawiiblog.securityFeature.Entity.AuthRequest;
import com.example.zahaawiiblog.securityFeature.service.JwtService;
import com.example.zahaawiiblog.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/users/")
@RestController
public class UserController {

    private final UserService userService;
    private JwtService jwtService;
    private AuthenticationManager authenticationManager;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/getallusers")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> getAllUsers = userService.getAllUsers();
        return new ResponseEntity<>(getAllUsers, HttpStatus.OK);
    }

    @GetMapping("/getuserbyid/{userid}")
    public ResponseEntity<User> getUserById(@PathVariable int userid) {
        User findUser = userService.getUserByUserId(userid);
        return new ResponseEntity<>(findUser, HttpStatus.OK);
    }

    @PostMapping("/createuser")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        userService.createNewUser(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteuser/{userId}")
    public ResponseEntity<?> deleteUserById(@PathVariable int userId) {
        if(userService.deleteUserById(userId) == -1) {
            return new ResponseEntity<>("User does not exist", HttpStatus.BAD_REQUEST);
        }
        userService.deleteUserById(userId);
        return new ResponseEntity<>("User with id " + userId + " has been deleted", HttpStatus.OK);
    }

    @PostMapping("/generateToken")
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));

        if(authentication.isAuthenticated()) {
            return new jwtService.generateToken(authRequest.getUsername());
        } else {
            throw new UsernameNotFoundException("Invalid user request!");
        }
    }

}
