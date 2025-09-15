package com.example.zahaawiiblog.commentsFeature.service;

import com.example.zahaawiiblog.commentsFeature.DTO.CommentsDTO;
import com.example.zahaawiiblog.commentsFeature.entity.Comments;
import com.example.zahaawiiblog.commentsFeature.mapper.CommentsMapper;
import com.example.zahaawiiblog.commentsFeature.repository.CommentsRepository;
import com.example.zahaawiiblog.entity.Blog;
import com.example.zahaawiiblog.repositories.BlogRepository;
import com.example.zahaawiiblog.repositories.UserRepository;
import com.example.zahaawiiblog.securityFeature.Entity.UserInfo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentsService {

    private final CommentsRepository commentsRepository;
    private final BlogRepository blogRepository;
    private final UserRepository userRepository;

    public CommentsService(CommentsRepository commentsRepository, BlogRepository blogRepository, UserRepository userRepository) {
        this.commentsRepository = commentsRepository;
        this.blogRepository = blogRepository;
        this.userRepository = userRepository;
    }

    public CommentsDTO add(CommentsDTO comment) {
        Blog blog = blogRepository.findBlogByBlogId(comment.blogId());
        UserInfo user = userRepository.findUserByUserId(comment.userId());
        Comments c = new Comments();
        c.setUserComment(comment.comment());
        c.setBlog(blog);
        c.setUser(user);
        Comments saved = commentsRepository.save(c);
        return CommentsMapper.toDTO(saved);
    }

    public List<CommentsDTO> findCommentsByBlogId(Long id) {
        List<Comments> getAll = commentsRepository.findByBlog_BlogId(id);
        List<CommentsDTO> result = new ArrayList<>();

        for(Comments c : getAll) {
            result.add(CommentsMapper.toDTO(c));
        }
        return result;

        /*
          Chat siger man kan gøre det på denne måde har dog ikke lært det
          return getAll.stream()
                           .map(CommentMapper::toDTO)   // kalder din static mapper-metode
                           .toList();
         */
    }
}
