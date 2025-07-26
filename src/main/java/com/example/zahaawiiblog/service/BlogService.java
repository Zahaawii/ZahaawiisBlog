package com.example.zahaawiiblog.service;

import com.example.zahaawiiblog.entity.Blog;
import com.example.zahaawiiblog.repositories.BlogRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogService {

    private final BlogRepository blogRepository;

    public BlogService(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }


    public List<Blog> findAllByBlogId(long id) {
        return blogRepository.findByBlogId(id);
    }

    public void addNewBlogPost(Blog blog) {
        blogRepository.save(blog);
    }

    public List<Blog> getAllBlogs() {
        return (List<Blog>) blogRepository.findAll();
    }

}
