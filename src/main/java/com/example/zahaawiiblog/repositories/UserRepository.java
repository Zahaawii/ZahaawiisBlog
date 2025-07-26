package com.example.zahaawiiblog.repositories;

import com.example.zahaawiiblog.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findUserByUserId(Long id);
}
