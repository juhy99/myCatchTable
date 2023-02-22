package com.catchmind.catchtable.dto.network.request;

import com.catchmind.catchtable.dto.ResAdminDto;

public record ResAdminRequest(
        String resaBisName
) {

    public static ResAdminRequest of(String resaBisName){
        return new ResAdminRequest(resaBisName);
    }

    public ResAdminDto toDto() {
        return ResAdminDto.of(
                resaBisName
        );
    }
}
