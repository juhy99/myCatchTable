package com.catchmind.catchtable.dto.network.response;

import com.catchmind.catchtable.dto.ReviewDto;

import java.util.List;

public record ProfileTimeLineResponse(
        List<ReviewDto> reviewList,
        int commNum,
        int heartNum
) {
}
