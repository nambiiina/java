package com.example.blog.mapper;

import com.example.blog.dto.PostRequest;
import com.example.blog.dto.PostResponse;
import com.example.blog.entity.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

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
}
