package com.clonecoding.instagrambackend.service;

import com.clonecoding.instagrambackend.domain.*;
import com.clonecoding.instagrambackend.mapper.CommentMapper;
import com.clonecoding.instagrambackend.web.dto.CommentDto;
import com.clonecoding.instagrambackend.web.dto.CommentRequestDto;
import com.clonecoding.instagrambackend.web.dto.PostDto;
import com.clonecoding.instagrambackend.web.dto.PostsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CommentMapper commentMapper;

    @Transactional
    public CommentDto createComment(CommentRequestDto commentRequestDto) {
        User user =  userRepository.findByUsername(commentRequestDto.getUsername())
                .orElseThrow(() -> new RuntimeException("Error : user is not found"));

        if (commentRequestDto.isNested()) {
            return createNestedComment(user, commentRequestDto);
        }
        else {
            return createPostComment(user, commentRequestDto);
        }
    }

    private CommentDto createPostComment(User user, CommentRequestDto commentRequestDto) {
        Post post =  postRepository.findById(commentRequestDto.getPostId())
                .orElseThrow(() -> new RuntimeException("Error : post is not found"));
        Comment comment = commentMapper.toEntity(commentRequestDto, user, post, null);
        commentRepository.save(comment);
        return commentMapper.postCommentToDto(comment, commentRepository);
    }

    private CommentDto createNestedComment(User user, CommentRequestDto commentRequestDto) {
        Comment parent =  commentRepository.findById(commentRequestDto.getParentId())
                .orElseThrow(() -> new RuntimeException("Error : comment is not found"));
        Comment comment = commentMapper.toEntity(commentRequestDto, user, null, parent);
        commentRepository.save(comment);
        return commentMapper.nestedCommentToDto(comment, commentRepository);
    }

    @Transactional
    public void deleteComment(Long id) {
        Comment comment =  commentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Error : comment is not found"));
        commentRepository.delete(comment);
    }

    @Transactional
    public List<CommentDto> getPostComments(Long post_id) {
        Post post = postRepository.findById(post_id)
                .orElseThrow(() -> new RuntimeException("Error : post is not found"));
        return commentRepository.findByPost(post)
                .stream()
                .map(comment -> commentMapper.postCommentToDto(comment, commentRepository))
                .collect(Collectors.toList());
    }

    @Transactional
    public List<CommentDto> getNestedComments(Long comment_id) {
        Comment parent = commentRepository.findById(comment_id)
                .orElseThrow(() -> new RuntimeException("Error : comment is not found"));
        return commentRepository.findByParent(parent)
                .stream()
                .map(comment -> commentMapper.nestedCommentToDto(comment, commentRepository))
                .collect(Collectors.toList());
    }
}
