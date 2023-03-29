package com.catchmind.catchtable.repository;

import com.catchmind.catchtable.domain.Review;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findAllByProfile_PrIdx(Long prIdx, Sort sort);
    List<Review> findAllByResAdmin_ResaBisName(String resaBisNamem, Sort sort);
    Long countByResAdmin_ResaBisName(String resaBisName);
    Review findByRevIdx(Long revIdx);
    List<Review> findTop6By();
}
