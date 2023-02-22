package com.catchmind.catchtable.dto.network.request;

import com.catchmind.catchtable.dto.ProfileDto;
import com.catchmind.catchtable.dto.SnsDto;

public record SnsRequest(
        Long prIdx,
        String snsAddr,
        String snsType
) {

    public SnsRequest of(Long prIdx,String snsAddr, String snsType) {
        return new SnsRequest(prIdx,snsAddr,snsType);
    }

    public SnsDto toDto() {
        return SnsDto.of(ProfileDto.ofIdx(prIdx), snsAddr, snsType);
    }
}
