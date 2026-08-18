package com.example.blog.post.dto;

import com.example.blog.post.comment.dto.CommentResponse;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
@Builder
public class PostResponse {
    private Long id;
    private String title;
    private String content;
    private String description;
    private Instant createdAt;
    private Instant updatedAt;
    private List<CommentResponse> comments;
}
