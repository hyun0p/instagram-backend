package com.clonecoding.instagrambackend.domain;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"follower_id", "followee_id"})
})
public class Follow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "follower_id")
    private User follower;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "followee_id")
    private User followee;

    private Follow() {}

    public Follow(User follower, User followee) {
        this.follower = follower;
        this.followee = followee;
    }
}
