package com.example.blog.service;

import com.example.blog.dto.CommentRequest;
import com.example.blog.dto.PostRequest;
import com.example.blog.entity.Post;

import java.util.List;

public interface PostService {
    Post createPost(PostRequest request);
    Post findById(Long id);
    Post findByIdWithComments(Long id);
    Post addComment(Long postId, CommentRequest request);
    Post updatePost(Long id, PostRequest request);
    void deletePost(Long id);
    void removeComment(Long postId, Long commentId);
    List<Post> findAll();
}
