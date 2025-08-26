package com.example.zahaawiiblog.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "blog")

public class Blog {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long blogId;

    private String subject;

    @Lob
    private String body;

    private String category;

    private Date publishDate;

    @OneToOne(cascade =  CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @Override
    public String toString() {
        return "Blog{" +
                "blogId=" + blogId +
                ", subject='" + subject + '\'' +
                ", body='" + body + '\'' +
                ", category='" + category + '\'' +
                ", publishDate=" + publishDate +
                ", user=" + user +
                '}';
    }
}
