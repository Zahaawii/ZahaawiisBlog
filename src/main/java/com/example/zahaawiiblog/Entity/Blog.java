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

    @ManyToOne(cascade =  CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

}
