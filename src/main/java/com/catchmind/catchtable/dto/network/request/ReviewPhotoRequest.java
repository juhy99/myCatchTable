package com.catchmind.catchtable.dto.network.request;

import com.catchmind.catchtable.dto.ReviewDto;
import com.catchmind.catchtable.dto.ReviewPhotoDto;

public record ReviewPhotoRequest(
        String orgNm,
        String savedNm,
        String savedPath,
        Long revIdx
) {
    public ReviewPhotoRequest of(
            String orgNm,
            String savedNm,
            String savedPath,
            Long revIdx
    ) {
        return new ReviewPhotoRequest(orgNm,savedNm,savedPath,revIdx);
    }

    public ReviewPhotoDto toDto() {
        return ReviewPhotoDto.of(
                orgNm,
                savedNm,
                savedPath,
                ReviewDto.ofIdx(revIdx));
    }
}
