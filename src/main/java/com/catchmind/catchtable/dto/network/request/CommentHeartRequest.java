package com.catchmind.catchtable.dto.network.request;

import com.catchmind.catchtable.dto.CommentDto;
import com.catchmind.catchtable.dto.CommentHeartDto;
import com.catchmind.catchtable.dto.ProfileDto;
import com.catchmind.catchtable.dto.ReviewDto;

public record CommentHeartRequest(
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

    public CommentHeartDto toCommentHeartDto() {
        return CommentHeartDto.of(
                ReviewDto.ofIdx(revIdx),
                ProfileDto.ofIdx(prIdx),
                CommentDto.ofIdx(comIdx)
        );
    }
}
