package com.catchmind.catchtable.repository;

import com.catchmind.catchtable.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findAllByReview_revIdx(Long Id);
    Comment findByComIdx(Long comIdx);
}
