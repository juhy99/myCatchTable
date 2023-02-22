package com.catchmind.catchtable.dto;

import com.catchmind.catchtable.domain.CommentHeart;

public record CommentHeartDto(
        Long heIdx,
        ProfileDto profileDto,
        ReviewDto reviewDto,
        CommentDto commentDto
) {
    public static CommentHeartDto from(CommentHeart commentHeart) {
        return new CommentHeartDto(
                commentHeart.getHeIdx(),
                ProfileDto.from(commentHeart.getProfile()),
                ReviewDto.from(commentHeart.getReview()),
                CommentDto.from(commentHeart.getComment())
        );
    }

    public static CommentHeartDto of(
            ReviewDto reviewDto,
            ProfileDto profileDto,
            CommentDto commentDto
    ) {
        return new CommentHeartDto(null, profileDto, reviewDto, commentDto);
    }

    public CommentHeart toEntity() {
        return CommentHeart.of(
                profileDto.toEntityIdx(),
                reviewDto.toEntityIdx(),
                commentDto.toEntityIdx()
        );
    }
}

