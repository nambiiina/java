package com.example.test.controller;

import com.example.test.model.Comment;
import com.example.test.repository.CommentRepository;
import com.example.test.repository.PostRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comments")
@AllArgsConstructor
public class CommentController {

    private CommentRepository commentRepository;

    @GetMapping("/posts/{postId}")
    public int getAllCommentsByPostId(@PathVariable (value = "postId") Long postId, Pageable pageable) {
        Page<Comment> comments = commentRepository.findByPostId(postId, pageable);
        return comments.getSize();
    }
}
