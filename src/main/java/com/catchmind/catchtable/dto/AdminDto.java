package com.catchmind.catchtable.dto;

import com.catchmind.catchtable.domain.Admin;

import java.time.LocalDateTime;

public record AdminDto(
        Long adIdx,         // 관리자 번호
        String adUserid,    // 관리자 아이디
        String adUserpw,    // 관리자 비밀번호
        String adName,      // 관리자 이름
        String adHp,        // 관리자 전화번호
        String adEmail,     // 관리자 이메일
        LocalDateTime regDate   // 등록날짜
) {
    public static AdminDto from(Admin admin){
        return new AdminDto(
                admin.getAdIdx(),
                admin.getAdUserid(),
                admin.getAdUserpw(),
                admin.getAdName(),
                admin.getAdHp(),
                admin.getAdEmail(),
                admin.getRegDate()
        );
    }
}
