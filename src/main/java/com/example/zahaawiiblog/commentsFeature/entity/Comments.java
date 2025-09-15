package com.example.zahaawiiblog.commentsFeature.entity;

import com.example.zahaawiiblog.entity.Blog;
import com.example.zahaawiiblog.securityFeature.Entity.UserInfo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "comments")
public class Comments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "comment_id", nullable = false)
    private Blog blog;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private UserInfo user;
}
