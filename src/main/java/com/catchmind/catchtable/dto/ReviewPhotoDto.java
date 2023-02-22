package com.catchmind.catchtable.dto;

import com.catchmind.catchtable.domain.ReviewPhoto;

public record ReviewPhotoDto(
        Long phIdx,
        String orgNm,
        String savedNm,
        String savedPath,
        ReviewDto reviewDto
) {
    public static ReviewPhotoDto from(
            ReviewPhoto photo
    ) {
        return new ReviewPhotoDto(photo.getPhIdx(), photo.getOrgNm(), photo.getSavedNm(), photo.getSavedPath(), ReviewDto.from(photo.getReview()));
    }
    public static ReviewPhotoDto of(
            String orgNm,
            String savedNm,
            String savedPath,
            ReviewDto reviewDto
    ) {
        return new ReviewPhotoDto(0L, orgNm, savedNm, savedPath, reviewDto);
    }

    public ReviewPhoto toEntity() {
        return ReviewPhoto.of(
                orgNm,
                savedNm,
                savedPath,
                reviewDto.toEntityIdx()
        );
    }
}
