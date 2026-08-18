package com.example.blog.post.entity;

import com.example.blog.post.comment.entity.Comment;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "post", indexes = {@Index(name = "idx_post_title", columnList = "title", unique = true)})
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@EntityListeners(AuditingEntityListener.class)
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Setter(AccessLevel.NONE)
    private Long id;

    @Column(nullable = false, unique = true, length = 200)
    @NotBlank
    @Size(max = 200)
    private String title;

    @Lob
    @Column(nullable = false, columnDefinition = "TEXT")
    @NotBlank
    private String content;

    @Column(length = 500)
    @Size(max = 500)
    private String description;


    /**
     * Unidirectional with @JoinColumn
     * The foreign key "post_id" is created in the "comment" table.
     * No join table, no "post" reference on the Comment side.
     */
    // Default -> fetch = FetchType.LAZY but better to be explicit
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    @Builder.Default // Essential to preserve new HashSet<>() with @Builder
    private Set<Comment> comments = new HashSet<>();

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    @Column(nullable = false)
    private Instant updatedAt;
}
