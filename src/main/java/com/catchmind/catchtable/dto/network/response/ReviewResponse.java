package com.catchmind.catchtable.dto.network.response;

import com.catchmind.catchtable.dto.ProfileDto;
import com.catchmind.catchtable.dto.ResAdminDto;
import com.catchmind.catchtable.dto.ReviewPhotoDto;

import java.time.LocalDateTime;
import java.util.List;

public record ReviewResponse(
        Long revIdx,
        ProfileDto profileDto,
        String revContent,
        Double revScore,
        ResAdminDto resAdminDto,
        List<ReviewPhotoDto> photo,
        Long resIdx,
        LocalDateTime regDate,
        LocalDateTime updateDate,
        boolean isReview        // 리뷰 본인 작성 여부
) {
}
