package com.example.blog.post.repository;

import com.example.blog.post.entity.Post;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    /**
     * Load post with comments in one request.
     * Alternative in JOIN FETCH
     * @param id
     * @return
     */
    @EntityGraph(attributePaths = "comments")
    Optional<Post> findById(Long id);
}
