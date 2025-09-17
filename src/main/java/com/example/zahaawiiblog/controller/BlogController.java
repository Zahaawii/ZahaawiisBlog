package com.example.zahaawiiblog.controller;


import com.example.zahaawiiblog.DTO.BlogDTO;
import com.example.zahaawiiblog.DTO.CreateBlogDto;
import com.example.zahaawiiblog.entity.Blog;
import com.example.zahaawiiblog.securityFeature.Entity.UserInfo;
import com.example.zahaawiiblog.service.BlogService;
import com.example.zahaawiiblog.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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
    public Optional<Blog> getAll(@PathVariable Long id) {
        return new ResponseEntity<>(blogService.findById(id), HttpStatus.OK).getBody();
    }

    @GetMapping("/getbyusername/{username}")
    public ResponseEntity<List<Blog>> getAllByUsernamne(@PathVariable String username) {
        List<Blog> findAllBlogPostByUsername = blogService.findAllByUsername(username);
        return new ResponseEntity<>(findAllBlogPostByUsername, HttpStatus.OK);
    }

    @GetMapping("/test/{id}")
    public ResponseEntity<List<Blog>> getAll(@PathVariable long id) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/getallblogpost")
    public ResponseEntity<List<BlogDTO>> getAll() {
        return new ResponseEntity<>(blogService.getAllBlogs(), HttpStatus.OK);
    }

    @DeleteMapping("/deletepost/{id}")
    public ResponseEntity<?> removeBlog(@PathVariable int id) {
        if(blogService.findById(id).isEmpty()) {
            return new ResponseEntity<>("Blog post does not exist", HttpStatus.BAD_REQUEST);
        }
        blogService.removeBlogPost(id);
        return new ResponseEntity<>("Blog post with id " + id + " was deleted", HttpStatus.OK);
    }

    @PostMapping("/saveblogpost")
    public ResponseEntity<?> addBlog(@RequestBody CreateBlogDto newBlogPost) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth == null || !auth.isAuthenticated()) {
            return new ResponseEntity<>("Not able to create blog post, user not found", HttpStatus.CONFLICT);
        }
        Optional<UserInfo> user = userService.findUserByUsername(auth.getName());
        if(user.isPresent()) {
            BlogDTO dto = blogService.addNewBlogPost(newBlogPost, user.get());
            return new ResponseEntity<>(dto, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("User not found", HttpStatus.BAD_REQUEST);
        }
    }
}
