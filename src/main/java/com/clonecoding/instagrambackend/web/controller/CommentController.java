package com.clonecoding.instagrambackend.web.controller;

import com.clonecoding.instagrambackend.service.CommentService;
import com.clonecoding.instagrambackend.service.LikeService;
import com.clonecoding.instagrambackend.web.dto.CommentDto;
import com.clonecoding.instagrambackend.web.dto.CommentRequestDto;
import com.clonecoding.instagrambackend.web.dto.PostRequestDto;
import com.clonecoding.instagrambackend.web.dto.PostsDto;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class CommentController {

    private final CommentService commentService;
    private final LikeService likeService;

    @ApiOperation("새 댓글 생성")
    @PreAuthorize("isAuthenticated() and #commentRequestDto.username == authentication.principal.username")
    @PostMapping("/comments")
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentRequestDto commentRequestDto) {
        return new ResponseEntity<>(commentService.createComment(commentRequestDto), HttpStatus.CREATED);
    }

    @ApiOperation("댓글 삭제")
    @PreAuthorize("isAuthenticated() and #commentRequestDto.username == authentication.principal.username")
    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long postId) {
        commentService.deleteComment(postId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation("포스트의 모든 댓글 불러오기")
    @GetMapping("/posts/{postId}/comments")
    public ResponseEntity<List<CommentDto>> getPostComment(@PathVariable Long postId) {
        return new ResponseEntity<>(commentService.getPostComments(postId), HttpStatus.CREATED);
    }

    @ApiOperation("대댓글 불러오기")
    @GetMapping("/comments/{commentId}/comments")
    public ResponseEntity<List<CommentDto>> getNestedComment(@PathVariable Long commentId) {
        return new ResponseEntity<>(commentService.getNestedComments(commentId), HttpStatus.CREATED);
    }

    @ApiOperation("댓글 좋아요")
    @PostMapping("/comments/{commentId}/like")
    public ResponseEntity<String> likeComment(@PathVariable Long commentId) {
        likeService.likeComment(commentId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation("댓글 좋아요 취소")
    @DeleteMapping("/comments/{commentId}/like")
    public ResponseEntity<String> unlikeComment(@PathVariable Long commentId) {
        likeService.unlikeComment(commentId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
