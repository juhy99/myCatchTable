package com.catchmind.catchtable.repository;

import com.catchmind.catchtable.domain.ReviewHeart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource
public interface ReviewHeartRepository extends JpaRepository<ReviewHeart,Long> {
    List<ReviewHeart> findAllByProfile_prIdx(Long prIdx);
    Long countByReview_revIdx(Long revIdx);
    Optional<ReviewHeart> deleteByProfile_PrIdxAndReview_RevIdx(Long prIdx, Long revIdx);
}
