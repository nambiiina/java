package com.example.blog.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

@Entity
@Table(name = "comment")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@EntityListeners(AuditingEntityListener.class)
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Setter(AccessLevel.NONE)
    private Long id;

    @Lob
    @Column(nullable = false, columnDefinition = "TEXT")
    @NotBlank
    private String text;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    /**
     * No reference to the Post here.
     * The FK is managed only by the Post entity via @JoinColumn.
     */
}
