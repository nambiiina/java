package com.example.blog.post.comment.mapper;

import com.example.blog.post.comment.dto.CommentRequest;
import com.example.blog.post.comment.dto.CommentResponse;
import com.example.blog.post.comment.entity.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Comment toEntity(CommentRequest commentRequest);

    CommentResponse toResponse(Comment entity);
}
