package com.example.zahaawiiblog.logginFeature.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Logging {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private String username;

    private String description;

    /*
    Action ID is what action that has occurred.
    ID 1: User created a blog post
    ID 2: User tried to create a blog post
    ID 3: User deleted a blog post
    ID 4: User tried to delete a blog post
    ID 5 : User accessed homepage
    ID 6: User accessed a profile
     */
    private Long actionId;

    private LocalDateTime localDateTime;
}
