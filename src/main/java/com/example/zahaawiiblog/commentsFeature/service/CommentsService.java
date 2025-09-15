package com.example.zahaawiiblog.commentsFeature.service;

import com.example.zahaawiiblog.commentsFeature.repository.CommentsRepository;
import org.springframework.stereotype.Service;

@Service
public class CommentsService {

    private final CommentsRepository commentsRepository;

    public CommentsService(CommentsRepository commentsRepository) {
        this.commentsRepository = commentsRepository;
    }
}
