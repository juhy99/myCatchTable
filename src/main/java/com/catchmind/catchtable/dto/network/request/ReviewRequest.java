package com.catchmind.catchtable.dto.network.request;

import com.catchmind.catchtable.dto.ProfileDto;
import com.catchmind.catchtable.dto.ResAdminDto;
import com.catchmind.catchtable.dto.ReserveDto;
import com.catchmind.catchtable.dto.ReviewDto;

public record ReviewRequest(
        Long prIdx,
        String revContent,
        double revScore,
        String resaBisName,
        Long resIdx
) {

    public ReviewRequest of(Long prIdx,
                            String revContent,
                            double revScore,
                            String resaBisName,
                            Long resIdx) {
        return new ReviewRequest(prIdx,revContent,revScore,resaBisName,resIdx);
    }

    public ReviewDto toDto() {
        return ReviewDto.of(ProfileDto.ofIdx(prIdx), revContent, revScore, ResAdminDto.of(resaBisName), ReserveDto.of(resIdx));
    }
}
