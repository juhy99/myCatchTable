package com.catchmind.catchtable.dto.network.response;

import com.catchmind.catchtable.dto.SnsDto;

import java.util.List;

public record ProfileResponse(
        String prName,
        String prNick,
        String prRegion,
        String prIntro,
        Long followingNum,
        Long followerNum,
        List<SnsDto> snsList,
        double avgScore
) {

}
