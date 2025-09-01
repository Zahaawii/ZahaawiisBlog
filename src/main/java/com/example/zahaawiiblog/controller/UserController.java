package com.example.zahaawiiblog.controller;


import com.example.zahaawiiblog.securityFeature.DTO.AuthResponse;
import com.example.zahaawiiblog.securityFeature.Entity.UserInfo;
import com.example.zahaawiiblog.securityFeature.Entity.AuthRequest;
import com.example.zahaawiiblog.securityFeature.service.JwtService;
import com.example.zahaawiiblog.securityFeature.service.UserInfoService;
import com.example.zahaawiiblog.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/users")
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class UserController {

    private final UserService userService;
    private final UserInfoService service;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

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
    public ResponseEntity<String> createUser(@RequestBody UserInfo userInfo) {
        String result = service.addUser(userInfo);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }


    @DeleteMapping("/deleteuser/{userId}")
    public ResponseEntity<?> deleteUserById(@PathVariable int userId) {
        if(userService.deleteUserById(userId) == -1) {
            return new ResponseEntity<>("User does not exist", HttpStatus.BAD_REQUEST);
        }
        userService.deleteUserById(userId);
        return new ResponseEntity<>("User with id " + userId + " has been deleted", HttpStatus.OK);
    }

    @PostMapping("/auth/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));

        String token = jwtService.generateToken(authRequest.getUsername());
        long ttlSeconds = 15 * 60;

        return ResponseEntity.ok(new AuthResponse(token, ttlSeconds));
    }

}
