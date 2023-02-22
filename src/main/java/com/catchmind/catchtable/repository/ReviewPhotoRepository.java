package com.catchmind.catchtable.repository;

import com.catchmind.catchtable.domain.ReviewPhoto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface ReviewPhotoRepository extends JpaRepository<ReviewPhoto,Long> {
    List<ReviewPhoto> findAllByReview_RevIdx(Long revIdx);
}
