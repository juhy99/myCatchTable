package com.catchmind.catchtable.dto;

import com.catchmind.catchtable.domain.Sns;

public record SnsDto(
        Long snsIdx,
        ProfileDto profileDto,
        String snsAddr,
        String snsType
) {
    public static SnsDto from(Sns sns) {
        return new SnsDto(sns.getSnsIdx(), ProfileDto.from(sns.getProfile()), sns.getSnsAddr(), sns.getSnsType());
    }
    public static SnsDto of(ProfileDto profileDto, String snsAddr, String snsType){
        return new SnsDto(0L,profileDto,snsAddr,snsType);
    }
    public Sns toEntity(){
        return Sns.of(
                profileDto.toEntityIdx(),
                snsAddr,
                snsType
        );
    }
}
