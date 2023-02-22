package com.catchmind.catchtable.dto;

import com.catchmind.catchtable.domain.BistroSave;

public record BistroSaveDto(
        Long saveIdx,
        ResAdminDto resAdminDto,
        ProfileDto profileDto,
        BistroDetailDto bistroDetailDto,
        Long colIdx

) {
    public static BistroSaveDto from(BistroSave entity){
        return new BistroSaveDto(
                entity.getSaveIdx(),
                ResAdminDto.from(entity.getResAdmin()),
                ProfileDto.from(entity.getProfile()),
                BistroDetailDto.from(entity.getBistroDetail()),
                entity.getColIdx()
        );
    }

    public static BistroSaveDto of(
            Long saveIdx,
            ResAdminDto reviewDto,
            ProfileDto profileDto,
            BistroDetailDto bistroDetailDto,
            Long colIdx

    ) {
        return new BistroSaveDto(saveIdx,reviewDto,profileDto,bistroDetailDto,colIdx);
    }

    public BistroSave toEntity() {
        return BistroSave.of(
                profileDto.toEntityIdx(),
                resAdminDto.toEntity(),
                bistroDetailDto.toEntityIdx()
        );
    }



}
