package com.example.blog.controller;

import com.example.blog.dto.CommentRequest;
import com.example.blog.dto.PostRequest;
import com.example.blog.dto.PostResponse;
import com.example.blog.entity.Post;
import com.example.blog.mapper.PostMapper;
import com.example.blog.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    private final PostMapper postMapper;

    @PostMapping
    public ResponseEntity<PostResponse> create(@Valid @RequestBody PostRequest request) {
        Post post = postService.createPost(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(postMapper.toResponse(post));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostResponse> getById(@PathVariable Long id) {
        Post post = postService.findByIdWithComments(id);
        return ResponseEntity.ok(postMapper.toResponse(post));
    }

    @GetMapping
    public ResponseEntity<List<PostResponse>> getAll() {
        List<PostResponse> responses = postService.findAll().stream()
                .map(postMapper::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @PostMapping("/{postId}/comments")
    public ResponseEntity<PostResponse> addComment(
            @PathVariable Long postId,
            @Valid @RequestBody CommentRequest request) {
        Post post = postService.addComment(postId, request);
        return ResponseEntity.ok(postMapper.toResponse(post));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody PostRequest request) {
        Post post = postService.updatePost(id, request);
        return ResponseEntity.ok(postMapper.toResponse(post));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        postService.deletePost(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{postId}/comments/{commentId}")
    public ResponseEntity<Void> removeComment(
            @PathVariable Long postId,
            @PathVariable Long commentId) {
        postService.removeComment(postId, commentId);
        return ResponseEntity.noContent().build();
    }
}
