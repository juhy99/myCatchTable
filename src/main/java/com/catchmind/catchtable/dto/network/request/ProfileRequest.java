package com.catchmind.catchtable.dto.network.request;

import com.catchmind.catchtable.domain.type.MemberRole;
import com.catchmind.catchtable.dto.ProfileDto;

public record ProfileRequest(
        String prName,
        String prHp,
        String prUserpw,
        String prNick,
        String prGender,
        String prBirth,
        String prRegion,
        String prIntro,
        MemberRole role
) {
//    public static ProfileRequest of(String prName, String prHp, String prUserpw, String prNick, String prGender, String prBirth, String prRegion, String prIntro, MemberRole role){
//        return new ProfileRequest(prName, prHp, prUserpw, prNick, prGender, prBirth, prRegion, prIntro, role);
//    }
    public static ProfileRequest of(String prName, String prHp, String prUserpw, String prNick, String prGender, String prBirth, String prRegion, String prIntro, MemberRole role){
    return new ProfileRequest(prName, prHp, prUserpw, prNick, prGender, prBirth, prRegion, prIntro, role);
}

//    public ProfileDto toDto() {
//        return ProfileDto.of(
//                prName,
//                prHp,
//                prUserpw,
//                prNick,
//                prGender,
//                prBirth,
//                prRegion,
//                prIntro
//
//        );
//    }
    public ProfileDto toDto() {
    return ProfileDto.of(
            prNick,
            prName,
            prIntro,
            prRegion,
            prHp,
            prUserpw,
            prGender,
            prBirth

    );
}
}
