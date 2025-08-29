package com.example.zahaawiiblog.entity;


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
public class UserInfo {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long userId;

    private String name;

    private String email;

    private String password;

    private Date createdDate;

    private String roles;

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", createdDate=" + createdDate +
                '}';
    }
}
