package com.catchmind.catchtable.dto.network.response;

import java.time.LocalDateTime;

public record CommentResponse(
        Long comIdx,
        String prNick,
        Long prIdx,
        String comContent,
        Long revIdx,
        LocalDateTime regDate,
        boolean isComm
) {
}
