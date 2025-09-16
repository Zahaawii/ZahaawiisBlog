package com.example.zahaawiiblog.commentsFeature.DTO;

import java.sql.Date;

public record CommentsDTO(Long commentId,String comment, Long blogId, Long userId, Date date) {
}
