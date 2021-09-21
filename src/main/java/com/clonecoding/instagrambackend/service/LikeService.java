package com.clonecoding.instagrambackend.service;

import com.clonecoding.instagrambackend.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class LikeService {
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final PostLikeRepository postLikeRepository;
    private final CommentLikeRepository commentLikeRepository;

    @Transactional
    public void likePost(Long postId) {
        User user = getCurrentUser();
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Error : post is not found"));
        PostLike postLike = new PostLike(user, post);
        postLikeRepository.save(postLike);
    }

    @Transactional
    public void unlikePost(Long postId) {
        User user = getCurrentUser();
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Error : post is not found"));
        PostLike postLike = postLikeRepository.findByUserAndPost(user, post)
                .orElseThrow(() -> new RuntimeException("Error : postLike is not found"));
        postLikeRepository.delete(postLike);
    }

    @Transactional
    public void likeComment(Long commentId) {
        User user = getCurrentUser();
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Error : comment is not found"));
        CommentLike commentLike = new CommentLike(user, comment);
        commentLikeRepository.save(commentLike);
    }

    @Transactional
    public void unlikeComment(Long commentId) {
        User user = getCurrentUser();
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Error : comment is not found"));
        CommentLike commentLike = commentLikeRepository.findByUserAndComment(user, comment)
                .orElseThrow(() -> new RuntimeException("Error : commentLike is not found"));
        commentLikeRepository.delete(commentLike);
    }

    private User getCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Error : User is not found"));
    }
}
