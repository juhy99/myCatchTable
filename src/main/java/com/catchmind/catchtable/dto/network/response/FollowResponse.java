package com.catchmind.catchtable.dto.network.response;

public record FollowResponse(
        Long prIdx,
        String prNick,
        String prIntro,
        boolean isFollow
) {

}
