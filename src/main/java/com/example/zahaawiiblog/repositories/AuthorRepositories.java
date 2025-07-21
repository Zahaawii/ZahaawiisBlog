package com.example.zahaawiiblog.repositories;

import com.example.zahaawiiblog.Entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepositories extends CrudRepository<User, Long> {
}
