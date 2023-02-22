package com.catchmind.catchtable.dto;

import com.catchmind.catchtable.domain.Profile;
import com.catchmind.catchtable.domain.type.MemberRole;

public record ProfileDto(
        Long prIdx,
        String prNick,
        String prName,
        String prIntro,
        String prRegion,
        String prHp,
        String prUserpw,
        String prGender,
        String prBirth,
        String prMemo,

        int prReview,
        int prNoshow,
        boolean prBlock,
        int prPoint,
        MemberRole role

) {
    public static ProfileDto of(String prNick, String prName, String prIntro, String prRegion, String prHp, String prUserpw, String prGender, String prBirth){
        return new ProfileDto(null, prNick, prName, prIntro, prRegion, prHp, prUserpw, prGender, prBirth, null, 0, 0,false, 0, null);
    }

    public static ProfileDto ofIdx(Long prIdx){
        return new ProfileDto(prIdx,null,null,null,null,null,null,null,null,null,0,0,false,0,null);
    }

    public static ProfileDto of(Long prIdx, String prNick, String prName, String prIntro, String prRegion, String prHp, String prUserpw, String prGender, String prBirth, String prMemo, int prReview, int prNoshow, boolean prBlock, int prPoint){
        return new ProfileDto(prIdx, prNick, prName, prIntro, prRegion, prHp, prUserpw, prGender, prBirth, prMemo,  prReview, prNoshow, prBlock, prPoint,null);
    }

    public static ProfileDto from(Profile entity){
        return new ProfileDto(
                entity.getPrIdx(),
                entity.getPrNick(),
                entity.getPrName(),
                entity.getPrIntro(),
                entity.getPrRegion(),
                entity.getPrHp(),
                entity.getPrUserpw(),
                entity.getPrGender(),
                entity.getPrBirth(),
                entity.getPrMemo(),
                entity.getPrReview(),
                entity.getPrNoshow(),
                entity.isPrBlock(),
                entity.getPrPoint(),
                entity.getRole()
        );
    }

    public Profile toEntity(){
        return Profile.of(prNick, prName, prIntro, prRegion, prHp, prUserpw, prGender, prBirth, prMemo, prReview, prNoshow, prBlock,prPoint,role);
    }

    public Profile toEntityIdx() {
        return Profile.ofIdx(
                prIdx
        );
    }
}
