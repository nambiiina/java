package com.example.test.controller;

import com.example.test.model.Comment;
import com.example.test.model.Post;
import com.example.test.model.dto.PostDto;
import com.example.test.repository.PostRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/posts")
@AllArgsConstructor
public class PostController {
    private PostRepository postRepository;

    @GetMapping
    public List<Post> getAll() {
        return postRepository.findAll();
    }

    @GetMapping("/{postId}")
    public Post findById(@PathVariable Long postId) {
        Optional<Post> post = postRepository.findById(postId);
        return post.get();
    }

    @PostMapping
    public ResponseEntity<Post> create(@RequestBody Post requestPost) {
        Post post = postRepository.save(requestPost);
        return new ResponseEntity<>(post, HttpStatus.CREATED);
    }
}
