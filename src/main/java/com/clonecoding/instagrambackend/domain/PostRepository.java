package com.clonecoding.instagrambackend.domain;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByUser(User user);

//    @Query("SELECT * from Post p where user_id in :ids")
    List<Post> findByUserIdIn(List<Long> ids, Sort sort);
}
