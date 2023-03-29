package com.catchmind.catchtable.dto.network.response;

import com.catchmind.catchtable.dto.ReviewPhotoDto;

import java.time.LocalDateTime;

public record ReviewAndPhotoResponse(
        String prNick,
        LocalDateTime regDate,
        double revScore,
        String revContent,
        ReviewPhotoDto reviewPhotoDto
) {
}
