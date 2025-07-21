package com.example.zahaawiiblog.repositories;

import com.example.zahaawiiblog.Entity.Blog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogRepository extends CrudRepository<Blog, Long> {
}
