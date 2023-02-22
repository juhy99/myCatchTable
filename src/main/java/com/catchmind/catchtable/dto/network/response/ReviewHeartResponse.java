package com.catchmind.catchtable.dto.network.response;

public record ReviewHeartResponse(
        Long prIdx,
        Long revIdx,
        boolean isLike,
        Long heartNum
) {
}
