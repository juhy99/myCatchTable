package com.catchmind.catchtable.repository;

import com.catchmind.catchtable.domain.DeclareReview;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeclareReviewRepository extends JpaRepository<DeclareReview, Long> {
    List<DeclareReview> findAllByProfile_PrIdx(Long prIdx, Sort derIdx);
}
