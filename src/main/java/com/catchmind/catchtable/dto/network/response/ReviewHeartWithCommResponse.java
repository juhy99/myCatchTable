package com.catchmind.catchtable.dto.network.response;

public record ReviewHeartWithCommResponse(
        Long prIdx,
        Long revIdx,
        boolean isLike,
        Long heartNum,
        Long revComm
) {
}
