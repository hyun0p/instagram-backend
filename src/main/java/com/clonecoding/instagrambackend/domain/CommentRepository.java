package com.clonecoding.instagrambackend.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPost(Post post);

    List<Comment> findByParent(Comment parent);

    @Query("SELECT count(l) from Comment c join c.likes l where c.id = ?1")
    Long countLikes(Long id);

    @Query("SELECT count(ch) from Comment c join c.children ch where c.id = ?1")
    Long countChildren(Long id);
}
