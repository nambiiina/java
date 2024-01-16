package com.example.test.controller;

import com.example.test.model.Comment;
import com.example.test.model.Post;
import com.example.test.model.dto.PostDto;
import com.example.test.repository.PostRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/posts")
@AllArgsConstructor
public class PostController {
    private PostRepository postRepository;
    @GetMapping("/{postId}")
    public Post findById(@PathVariable Long postId) {
        Optional<Post> post = postRepository.findById(postId);
        Set<Comment> comments = post.get().getComments();
        return post.get();
        /*if (post.isPresent()) {
            Set<Comment> comments = post.get().getComments();
            return PostDto.builder().title(post.get().getTitle()).build();
        }
        return null;*/
    }
}
