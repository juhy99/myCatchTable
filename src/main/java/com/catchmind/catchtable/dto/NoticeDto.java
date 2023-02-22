package com.catchmind.catchtable.dto;

import com.catchmind.catchtable.domain.Notice;

import java.time.LocalDateTime;

public record NoticeDto(
        Long noIdx,
        String noTitle,
        String noContent,
        LocalDateTime regDate,
        LocalDateTime updateDate
) {
    public static NoticeDto from(Notice notice){
        return new NoticeDto(
                notice.getNoIdx(),
                notice.getNoTitle(),
                notice.getNoContent(),
                notice.getRegDate(),
                notice.getUpdateDate()
        );
    }
}
