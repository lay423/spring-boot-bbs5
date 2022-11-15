package com.mustache.bbs3.repository;

import com.mustache.bbs3.domain.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByArticleId(Integer articleId);
}
