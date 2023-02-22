package com.catchmind.catchtable.dto.network.request;

import com.catchmind.catchtable.dto.ProfileDto;
import com.catchmind.catchtable.dto.ReviewDto;
import com.catchmind.catchtable.dto.ReviewHeartDto;

public record ReviewHeartRequest(
        Long prIdx,
        Long revIdx,
        Long revLike
) {
    public ReviewHeartDto toDto() {
        return ReviewHeartDto.of(
                ProfileDto.ofIdx(prIdx),
                ReviewDto.ofLike(revIdx,revLike)
        );
    }
}
