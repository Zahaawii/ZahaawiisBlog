package com.example.zahaawiiblog.commentsFeature.Controller;


import com.example.zahaawiiblog.commentsFeature.DTO.CommentsDTO;
import com.example.zahaawiiblog.commentsFeature.entity.Comments;
import com.example.zahaawiiblog.commentsFeature.service.CommentsService;
import com.example.zahaawiiblog.service.BlogService;
import com.example.zahaawiiblog.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/comments")
@CrossOrigin(origins = "*")
public class CommentsController {

    private final CommentsService commentsService;
    private final BlogService blogService;
    private final UserService userService;

    public CommentsController (CommentsService commentsService, BlogService blogService, UserService userService) {
        this.commentsService = commentsService;
        this.blogService = blogService;
        this.userService = userService;
    }

    @PostMapping("/addcomment")
    public ResponseEntity<CommentsDTO> addComment (@RequestBody CommentsDTO comment) {
        commentsService.add(comment);
        return new ResponseEntity<>(comment,HttpStatus.OK);
    }

    @GetMapping("/getcomment/{blogId}")
    public ResponseEntity<?> test(@PathVariable Long blogId) {
        List<CommentsDTO> getAllComments = commentsService.findCommentsByBlogId(blogId);
        return new ResponseEntity<>(getAllComments, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{commentid}")
    public ResponseEntity<?> deleteComment(@PathVariable Long commentid) {
        commentsService.deleteByCommentId(commentid);
        return new ResponseEntity<>("Comment deleted", HttpStatus.OK);
    }

}
