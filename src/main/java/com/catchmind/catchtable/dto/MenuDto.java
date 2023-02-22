package com.catchmind.catchtable.dto;


import com.catchmind.catchtable.domain.Menu;

import java.time.LocalDateTime;

public record MenuDto(
        Long meIdx,
        ResAdminDto resAdminDto,
        String meName,
        String meContent,
        String mePrice,
        LocalDateTime regDate,
        LocalDateTime updateDate
) {

    public static MenuDto of(Long meIdx, ResAdminDto resAdminDto, String meName, String meContent, String mePrice, LocalDateTime regDate, LocalDateTime updateDate){
        return new MenuDto( meIdx,resAdminDto,meName,meContent,mePrice,regDate,updateDate);
    }

    public static MenuDto from(Menu entity){
        return new MenuDto(
                entity.getMeIdx(),
                ResAdminDto.from(entity.getResAdmin()),
                entity.getMeName(),
                entity.getMeContent(),
                entity.getMePrice(),
                entity.getRegDate(),
                entity.getUpdateDate()
        );
    }



}
