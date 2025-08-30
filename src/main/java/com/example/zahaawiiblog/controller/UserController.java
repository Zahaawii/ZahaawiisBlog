package com.example.zahaawiiblog.controller;


import com.example.zahaawiiblog.securityFeature.Entity.UserInfo;
import com.example.zahaawiiblog.securityFeature.Entity.AuthRequest;
import com.example.zahaawiiblog.securityFeature.service.JwtService;
import com.example.zahaawiiblog.securityFeature.service.UserInfoService;
import com.example.zahaawiiblog.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/users")
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private UserInfoService service;
    private JwtService jwtService;
    private AuthenticationManager authenticationManager;

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome this endpoint is not secure";
    }


    @GetMapping("/getallusers")
    public ResponseEntity<List<UserInfo>> getAllUsers() {
        List<UserInfo> getAllUserInfos = userService.getAllUsers();
        return new ResponseEntity<>(getAllUserInfos, HttpStatus.OK);
    }

    @GetMapping("/getuserbyid/{userid}")
    public ResponseEntity<UserInfo> getUserById(@PathVariable int userid) {
        UserInfo findUserInfo = userService.getUserByUserId(userid);
        return new ResponseEntity<>(findUserInfo, HttpStatus.OK);
    }

    @PostMapping("/createuser")
    public ResponseEntity<?> createUser(@RequestBody UserInfo userInfo) {
        service.addUser(userInfo);
        return new ResponseEntity<>(userInfo, HttpStatus.CREATED);
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
            return jwtService.generateToken(authRequest.getUsername());
        } else {
            throw new UsernameNotFoundException("Invalid user request!");
        }
    }

}
