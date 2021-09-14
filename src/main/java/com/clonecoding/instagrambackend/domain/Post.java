package com.clonecoding.instagrambackend.domain;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@EntityListeners(AuditingEntityListener.class)
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String text;
    @CreatedDate
    private LocalDateTime created_at;
    private Long views = 0L;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userID")
    private User user;

    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Image> images;

//    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY)
//    private List<comment> comments;

    private Post() {}

    @Builder
    public Post(String text, User user, List<Image> images) {
        this.text = text;
        this.user = user;
        this.images = images;
    }

    public void updateViews() {
        this.views++;
    }
}
