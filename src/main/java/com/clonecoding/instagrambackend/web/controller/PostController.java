package com.clonecoding.instagrambackend.web.controller;

import com.clonecoding.instagrambackend.service.LikeService;
import com.clonecoding.instagrambackend.service.PostService;
import com.clonecoding.instagrambackend.web.dto.PostDto;
import com.clonecoding.instagrambackend.web.dto.PostRequestDto;
import com.clonecoding.instagrambackend.web.dto.PostsDto;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;
    private final LikeService likeService;

    @ApiOperation("새 포스트 생성")
    @PreAuthorize("isAuthenticated() and #postRequestDto.username == authentication.principal.username")
    @PostMapping
    public ResponseEntity<Void> createPost(@RequestBody PostRequestDto postRequestDto) {
        postService.createPost(postRequestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @ApiOperation("포스트 삭제")
    @PreAuthorize("isAuthenticated() and #postRequestDto.username == authentication.principal.username")
    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation("특정 유저가 올린 모든 포스트 가져오기")
    @GetMapping("/{username}")
    public ResponseEntity<PostsDto> getPostsByUsername(@PathVariable String username) {
        return new ResponseEntity<>(postService.getPostsByUsername(username), HttpStatus.OK);
    }

    @ApiOperation("피드")
    @GetMapping("/feeds")
    public ResponseEntity<PostsDto> getFeeds() {
        return new ResponseEntity<>(postService.getFeeds(), HttpStatus.OK);
    }

    @ApiOperation("포스트 좋아요")
    @PostMapping("/{postId}/like")
    public ResponseEntity<String> likePost(@PathVariable Long postId) {
        likeService.likePost(postId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation("포스트 좋아요 취소")
    @DeleteMapping("/{postId}/like")
    public ResponseEntity<String> unlikePost(@PathVariable Long postId) {
        likeService.unlikePost(postId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
