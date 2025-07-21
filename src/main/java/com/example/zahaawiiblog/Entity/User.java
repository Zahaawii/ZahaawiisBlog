package com.example.zahaawiiblog.Entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long userId;

    private String name;

    private String email;

    private String password;

    private Date createdDate;
}
