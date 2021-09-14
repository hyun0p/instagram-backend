package com.clonecoding.instagrambackend.domain;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Entity
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String url;
    @NotNull
    private String type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "postID")
    private Post post;

    public void setPost(Post post) {
        this.post = post;
    }

    private Image() {}

    @Builder
    public Image(String url, String type) {
        this.url = url;
        this.type = type;
    }
}
