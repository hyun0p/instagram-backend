package com.clonecoding.instagrambackend.web.controller;

import com.clonecoding.instagrambackend.service.CommentService;
import com.clonecoding.instagrambackend.web.dto.CommentDto;
import com.clonecoding.instagrambackend.web.dto.CommentRequestDto;
import com.clonecoding.instagrambackend.web.dto.PostRequestDto;
import com.clonecoding.instagrambackend.web.dto.PostsDto;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class CommentController {

    private final CommentService commentService;

    @ApiOperation("새 댓글 생성")
    @PostMapping("/comments")
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentRequestDto commentRequestDto) {
        return new ResponseEntity<>(commentService.createComment(commentRequestDto), HttpStatus.CREATED);
    }

    @ApiOperation("댓글 삭제")
    @DeleteMapping("/comments/{comment_id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long post_id) {
        commentService.deleteComment(post_id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation("포스트의 모든 댓글 불러오기")
    @GetMapping("/posts/{post_id}/comments")
    public ResponseEntity<List<CommentDto>> getPostComment(@PathVariable Long post_id) {
        return new ResponseEntity<>(commentService.getPostComments(post_id), HttpStatus.CREATED);
    }

    @ApiOperation("대댓글 불러오기")
    @GetMapping("/comments/{comment_id}/comments")
    public ResponseEntity<List<CommentDto>> getNestedComment(@PathVariable Long comment_id) {
        return new ResponseEntity<>(commentService.getNestedComments(comment_id), HttpStatus.CREATED);
    }
}
