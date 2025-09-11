package com.example.zahaawiiblog.controller;


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
    public ResponseEntity<?> addBlog(@RequestBody CreateBlogDto newBlogPost) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if(auth == null || !auth.isAuthenticated()) {
            return new ResponseEntity<>("Not able to create blog post, user not found", HttpStatus.CONFLICT);
        }
        Optional<UserInfo> user = userService.findUserByUsername(auth.getName());
        if(user.isPresent()) {
            Blog b = new Blog();
            b.setSubject(newBlogPost.subject());
            b.setBody(newBlogPost.body());
            b.setCategory(newBlogPost.category());
            b.setPublishDate(Date.valueOf(LocalDate.now()));
            b.setUserInfo(user.get());

            blogService.addNewBlogPost(b);
            return new ResponseEntity<>(newBlogPost, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("User not found", HttpStatus.BAD_REQUEST);
        }
    }
}
