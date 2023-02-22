package com.catchmind.catchtable.dto;

import com.catchmind.catchtable.domain.ResAdmin;

import java.time.LocalDateTime;

public record ResAdminDto(
        String resaUserid,
        String resaUserpw,
        String resaName,
        String resaHp,
        String resaRegion,
        String resaBisName,
        LocalDateTime regDate
) {
    public static ResAdminDto from(ResAdmin resAdmin){
        return new ResAdminDto(
                resAdmin.getResaUserid(),
                resAdmin.getResaUserpw(),
                resAdmin.getResaName(),
                resAdmin.getResaHp(),
                resAdmin.getResaRegion(),
                resAdmin.getResaBisName(),
                resAdmin.getRegDate()
        );
    }

    public static ResAdminDto of(String resaBisName) {
        return new ResAdminDto(null,null,null,null,null,resaBisName,null);
    }

    public ResAdmin toEntity() {
        return ResAdmin.of1(
                resaBisName
        );
    }

    public static ResAdminDto ofName(String resaBisName) {
        return new ResAdminDto(null,null,null,null,null,resaBisName,null);
    }

}
