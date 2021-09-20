package com.clonecoding.instagrambackend.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FollowRepository extends JpaRepository<Follow, Long> {
    Optional<Follow> findByFollowerAndFollowee(User follower, User followee);
    List<Follow> findByFollower(User Follower);
    List<Follow> findByFollowee(User Followee);
}
