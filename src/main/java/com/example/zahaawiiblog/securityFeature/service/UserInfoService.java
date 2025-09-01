package com.example.zahaawiiblog.securityFeature.service;


import com.example.zahaawiiblog.securityFeature.Entity.UserInfo;
import com.example.zahaawiiblog.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import java.util.Optional;

@Service
public class UserInfoService implements UserDetailsService {


    private final UserRepository repository;
    private final PasswordEncoder encoder;

    @Autowired
    public UserInfoService(UserRepository repository, PasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws
            UsernameNotFoundException {
        UserInfo userInfo = repository.findByName(username).orElseThrow(() -> new UsernameNotFoundException("Not found: " + username));
        return User.withUsername(userInfo.getName())
                .password(userInfo.getPassword())
                .authorities(new SimpleGrantedAuthority("ROLE_USER"))
                .build();
    }

    public String addUser(UserInfo userInfo) {
        userInfo.setPassword(encoder.encode(userInfo.getPassword()));
        repository.save(userInfo);
        return "User added succesfully";
    }
}
