package com.catchmind.catchtable.repository;

import com.catchmind.catchtable.domain.CommentHeart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource
public interface CommentHeartRepository extends JpaRepository<CommentHeart, Long> {
    List<CommentHeart> findAllByReview_revIdx(Long Id);
    List<CommentHeart> findAllByProfile_PrIdx(Long prIdx);
    Optional<CommentHeart> deleteByProfile_PrIdxAndComment_comIdx(Long prIdx, Long comIdx);
}
