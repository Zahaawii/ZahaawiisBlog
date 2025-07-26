package com.example.zahaawiiblog.controller;


import com.example.zahaawiiblog.entity.Blog;
import com.example.zahaawiiblog.entity.User;
import com.example.zahaawiiblog.service.BlogService;
import com.example.zahaawiiblog.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BlogController {


    private final BlogService blogService;
    private final UserService userService;

    public BlogController(BlogService blogService, UserService userService) {
        this.blogService = blogService;
        this.userService = userService;
    }

    @GetMapping("/api/getById/{id}")
    public ResponseEntity<List<Blog>> getAll(@PathVariable int id) {
        List<Blog> findAllBlogPost = blogService.findAllByBlogId(id);
        System.out.println(findAllBlogPost);
        return new ResponseEntity<>(findAllBlogPost, HttpStatus.OK);
    }

    @GetMapping("/api/getAllBlogPost")
    public ResponseEntity<List<Blog>> getAll() {
        return new ResponseEntity<>(blogService.getAllBlogs(), HttpStatus.OK);
    }

    @PostMapping("/api/saveBlogPost")
    public ResponseEntity<?> addBlog(@RequestBody Blog newBlogPost) {
        blogService.addNewBlogPost(newBlogPost);
        return new ResponseEntity<>(newBlogPost, HttpStatus.CREATED);
    }


}
