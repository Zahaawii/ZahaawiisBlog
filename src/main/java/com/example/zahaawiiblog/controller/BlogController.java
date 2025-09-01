package com.example.zahaawiiblog.controller;


import com.example.zahaawiiblog.entity.Blog;
import com.example.zahaawiiblog.service.BlogService;
import com.example.zahaawiiblog.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/blog")
@RestController
@CrossOrigin(origins = "*")
public class BlogController {

    private final BlogService blogService;
    private final UserService userService;

    public BlogController(BlogService blogService, UserService userService) {
        this.blogService = blogService;
        this.userService = userService;
    }

    @GetMapping("/getbyid/{id}")
    public ResponseEntity<List<Blog>> getAll(@PathVariable int id) {
        List<Blog> findAllBlogPost = blogService.findAllByBlogId(id);
        System.out.println(findAllBlogPost);
        return new ResponseEntity<>(findAllBlogPost, HttpStatus.OK);
    }

    @GetMapping("/getallblogpost")
    public ResponseEntity<List<Blog>> getAll() {
        return new ResponseEntity<>(blogService.getAllBlogs(), HttpStatus.OK);
    }

    @DeleteMapping("/deletepost/{id}")
    public ResponseEntity<?> removeBlog(@PathVariable int id) {
        if(blogService.findAllByBlogId(id).isEmpty()) {
            return new ResponseEntity<>("Blog post does not exsist", HttpStatus.BAD_REQUEST);
        }
        blogService.removeBlogPost(id);
        return new ResponseEntity<>("Blog post with id " + id + " was deleted", HttpStatus.OK);
    }

    @PostMapping("/saveblogpost")
    public ResponseEntity<?> addBlog(@RequestBody Blog newBlogPost) {
        blogService.addNewBlogPost(newBlogPost);
        return new ResponseEntity<>(newBlogPost, HttpStatus.CREATED);
    }
}
