package com.example.zahaawiiblog.securityFeature.Entity;


import com.example.zahaawiiblog.entity.Blog;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

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

    private String imgPath;

    @JsonBackReference
    @OneToMany(mappedBy = "userInfo", cascade = CascadeType.ALL)
    private List<Blog> authorPost;

}
