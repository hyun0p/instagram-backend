package com.clonecoding.instagrambackend.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findById(Long id);
    Optional<User> findByUsername(String name);
    List<User> findAll();

    @Query("SELECT count(p) from User u join u.posts p where u.id = ?1")
    Long countPosts(Long id);

    @Query("SELECT count(f) from User u join u.followers f where u.id = ?1")
    Long countFollowers(Long id);

    @Query("SELECT count(f) from User u join u.followings f where u.id = ?1")
    Long countFollowings(Long id);
}
