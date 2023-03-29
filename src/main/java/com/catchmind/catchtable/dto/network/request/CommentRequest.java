package com.catchmind.catchtable.dto.network.request;

import com.catchmind.catchtable.dto.CommentDto;
import com.catchmind.catchtable.dto.ProfileDto;
import com.catchmind.catchtable.dto.ReviewDto;

public record CommentRequest(
        Long revIdx,
        Long comIdx,
        Long prIdx,
        String comContent,
        Long comLike
) {
    public CommentDto toDto() {
        return CommentDto.of(
                comIdx,
                ProfileDto.ofIdx(prIdx),
                comContent,
                ReviewDto.ofIdx(revIdx),
                comLike
        );
    }

}
