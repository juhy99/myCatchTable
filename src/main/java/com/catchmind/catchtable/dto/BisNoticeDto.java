package com.catchmind.catchtable.dto;

import com.catchmind.catchtable.domain.BisNotice;


public record BisNoticeDto(
        Long binIdx,
        String binTitle,
        String binContent,
        ResAdminDto resAdmindto
) {
    public static BisNoticeDto of(Long binIdx,String binTitle,String binContent,ResAdminDto resAdminDto){
        return new BisNoticeDto(binIdx,binTitle,binContent,resAdminDto);
    }

    public static BisNoticeDto from(BisNotice entity){
        return new BisNoticeDto(
                entity.getBinIdx(),
                entity.getBinTitle(),
                entity.getBinContent(),
                ResAdminDto.from(entity.getResAdmin())
        );
    }


}
