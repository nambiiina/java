package com.example.test.controller;

import com.example.test.model.Comment;
import com.example.test.repository.CommentRepository;
import com.example.test.repository.PostRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comments")
@AllArgsConstructor
public class CommentController {

    private PostRepository postRepository;
    private CommentRepository commentRepository;

    @PostMapping("/posts/{postId}")
    public ResponseEntity<Comment> create(@PathVariable Long postId, @RequestBody Comment requestComment) {
        Comment comment = postRepository.findById(postId).map(post -> {
           post.getComments().add(requestComment);
           return commentRepository.save(requestComment);
        }).orElseThrow(() -> new RuntimeException("Not found post " + postId));
        return new ResponseEntity<>(comment, HttpStatus.CREATED);
    }
}
