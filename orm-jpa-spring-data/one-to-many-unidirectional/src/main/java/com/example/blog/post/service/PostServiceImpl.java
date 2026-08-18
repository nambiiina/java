package com.example.blog.post.service;

import com.example.blog.post.comment.dto.CommentRequest;
import com.example.blog.post.comment.entity.Comment;
import com.example.blog.post.comment.mapper.CommentMapper;
import com.example.blog.post.dto.PostRequest;
import com.example.blog.post.entity.Post;
import com.example.blog.post.mapper.PostMapper;
import com.example.blog.post.repository.PostRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final PostMapper postMapper;
    private final CommentMapper commentMapper;

    @Override
    @Transactional
    public Post createPost(PostRequest request) {
        Post post = postMapper.toEntity(request);
        return postRepository.save(post);
    }

    @Override
    public Post findById(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Post with id " + id + " not found"));
    }

    @Override
    public Post findByIdWithComments(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Post with id " + id + " not found"));
    }

    @Override
    @Transactional
    public Post addComment(Long postId, CommentRequest request) {
        Post post = findById(postId);

        Comment comment = commentMapper.toEntity(request);
        post.getComments().add(comment); // CascadeType.ALL persiste le comment

        return postRepository.save(post);
    }

    @Override
    @Transactional
    public Post updatePost(Long id, PostRequest request) {
        Post post = findById(id);
        postMapper.updateEntityFromDto(request,post);
        return postRepository.save(post);
    }

    @Override
    @Transactional
    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void removeComment(Long postId, Long commentId) {
        Post post = findByIdWithComments(postId);
        post.getComments().removeIf(c -> c.getId().equals(commentId));
        postRepository.save(post);
    }

    @Override
    public List<Post> findAll() {
        return postRepository.findAll();
    }
}
