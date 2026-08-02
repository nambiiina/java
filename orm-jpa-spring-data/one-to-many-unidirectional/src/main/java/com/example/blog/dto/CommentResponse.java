package com.example.blog.dto;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class CommentResponse {
    private Long id;
    private String text;
    private Instant createdAt;
}
