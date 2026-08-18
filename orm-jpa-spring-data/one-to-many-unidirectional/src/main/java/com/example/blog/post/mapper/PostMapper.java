package com.example.blog.post.mapper;

import com.example.blog.post.comment.mapper.CommentMapper;
import com.example.blog.post.dto.PostRequest;
import com.example.blog.post.dto.PostResponse;
import com.example.blog.post.entity.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(
        componentModel = "spring",
        uses = CommentMapper.class // MapStruct utilisera CommentMapper pour les éléments de la collection
)
public interface PostMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "comments", ignore = true) // Les comments sont ajoutés séparément via le service
    Post toEntity(PostRequest dto);

    /**
     * Conversion Entity -> Response
     * MapStruct convertira automatiquement Set<Comment> -> List<CommentResponse>
     * grâce à CommentMapper.toResponse() et au paramètre 'uses'.
     */
    PostResponse toResponse(Post entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "comments", ignore = true)
    void updateEntityFromDto(PostRequest dto, @MappingTarget Post entity);
}
