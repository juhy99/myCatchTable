package com.catchmind.catchtable.dto.network.request;

import com.catchmind.catchtable.dto.DeclareReviewDto;
import com.catchmind.catchtable.dto.ProfileDto;
import com.catchmind.catchtable.dto.ReviewDto;

public record DeclareReviewRequest(
        Long revIdx,
        String derNick,
        Long prIdx,
        String derTitle,
        String derContent
) {

    public DeclareReviewRequest of(
            Long revIdx,
            String derNick,
            Long prIdx,
            String derTitle,
            String derContent
    ){
        return new DeclareReviewRequest(revIdx, derNick, prIdx, derTitle, derContent);
    }

    public DeclareReviewDto toDto(){
        return DeclareReviewDto.of(ReviewDto.ofIdx(revIdx), derNick, ProfileDto.ofIdx(prIdx), derTitle, derContent);
    }
}
