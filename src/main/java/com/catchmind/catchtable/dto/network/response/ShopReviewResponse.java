package com.catchmind.catchtable.dto.network.response;

public record ShopReviewResponse(
        String avgScore,
        int revCnt,
        Long score1,
        Long score2,
        Long score3,
        Long score4,
        Long score5,
        Long scoreSum

) {
}
