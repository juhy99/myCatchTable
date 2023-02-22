package com.catchmind.catchtable.dto;

import com.catchmind.catchtable.domain.Review;

import java.time.LocalDateTime;

public record ReviewDto(
        Long revIdx,
        ProfileDto profileDto,
        Long revLike,
        Long revComm,
        String revContent,
        double revScore,
        ResAdminDto resAdminDto,
        LocalDateTime regDate,
        LocalDateTime updateDate,
        ReserveDto reserveDto

) {

    public static ReviewDto from(Review review) {
        return new ReviewDto(
                review.getRevIdx(),
                ProfileDto.from(review.getProfile()),
                review.getRevLike(),
                review.getRevComm(),
                review.getRevContent(),
                review.getRevScore(),
                ResAdminDto.from(review.getResAdmin()),
                review.getRegDate(),
                review.getUpdateDate(),
                ReserveDto.from(review.getReserve())
        );
    }

    public static ReviewDto of(
            ProfileDto profileDto,
            String revContent,
            double revScore,
            ResAdminDto resAdminDto,
            ReserveDto reserveDto
    ) {
        return new ReviewDto(0L, profileDto, 0L, 0L, revContent, revScore, resAdminDto, null, null, reserveDto);
    }

    public static ReviewDto ofIdx(
            Long revIdx
    ) {
        return new ReviewDto(revIdx, null, 0L, 0L, null, 0, null, null, null, null);
    }

    public static ReviewDto ofLike(
            Long revIdx,
            Long revLike
    ) {
        return new ReviewDto(revIdx, null, revLike, 0L, null, 0, null, null, null, null);
    }


    public Review toEntity() {
        return Review.of(
                profileDto.toEntityIdx(),
                revContent,
                revScore,
                resAdminDto.toEntity(),
                reserveDto.toEntity()
        );

    }

    public Review toEntityIdx() {
        return Review.ofIdx(
                revIdx
        );
    }

    public Review toEntityLike() {
        return Review.ofLike(
                revIdx,
                revLike
        );

    }

}
