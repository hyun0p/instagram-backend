package com.clonecoding.instagrambackend.domain;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"user_id", "comment_id"})
})
public class CommentLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id")
    private Comment comment;

    private CommentLike() {}

    public CommentLike(User user,Comment comment) {
        this.user = user;
        this.comment = comment;
    }
}
