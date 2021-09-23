package com.clonecoding.instagrambackend.mapper;

import com.clonecoding.instagrambackend.domain.Comment;
import com.clonecoding.instagrambackend.domain.CommentRepository;
import com.clonecoding.instagrambackend.domain.Post;
import com.clonecoding.instagrambackend.domain.User;
import com.clonecoding.instagrambackend.web.dto.CommentDto;
import com.clonecoding.instagrambackend.web.dto.CommentRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    @Mapping(target = "username", source = "user.username")
    @Mapping(target = "postId", source = "post.id")
    @Mapping(target = "parentId", ignore = true)
    @Mapping(target = "childrenSize", expression = "java(commentRepository.countChildren(comment.getId())")
    @Mapping(target = "likes", expression = "java(commentRepository.countLikes(comment.getId())")
    CommentDto postCommentToDto(Comment comment, CommentRepository commentRepository);

    @Mapping(target = "username", source = "user.username")
    @Mapping(target = "postId", ignore = true)
    @Mapping(target = "parentId", source = "parent.id")
    @Mapping(target = "childrenSize", expression = "java(commentRepository.countChildren(comment.getId())")
    @Mapping(target = "likes", expression = "java(commentRepository.countLikes(comment.getId())")
    CommentDto nestedCommentToDto(Comment comment, CommentRepository commentRepository);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "created_at", ignore = true)
    @Mapping(target = "likes", ignore = true)
    @Mapping(target = "text", source = "commentRequestDto.text")
    @Mapping(target = "user", source = "user")
    @Mapping(target = "post", source = "post")
    @Mapping(target = "parent", source = "parent")
    Comment toEntity(CommentRequestDto commentRequestDto, User user, Post post, Comment parent);

}
