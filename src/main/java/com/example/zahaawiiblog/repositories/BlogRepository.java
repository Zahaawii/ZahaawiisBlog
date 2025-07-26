package com.example.zahaawiiblog.repositories;

import com.example.zahaawiiblog.entity.Blog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends CrudRepository<Blog, Long> {
    List<Blog> findByBlogId(Long blogId);
}
