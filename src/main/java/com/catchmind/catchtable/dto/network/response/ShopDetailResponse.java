package com.catchmind.catchtable.dto.network.response;

import com.catchmind.catchtable.dto.*;

import java.util.List;

public record ShopDetailResponse(
        BistroDetailDto bistroDetailDto,
        PhotoDto shopPhoto,
        List<BisNoticeDto> bisNoticeDto,
        List<FacilityDto> facilityDto,
        List<MenuDto> menuDto,
        String revAvg,
        Long revCnt,
        List<ReviewAndPhotoResponse> reviewAndPhotoResponse
) {
}
