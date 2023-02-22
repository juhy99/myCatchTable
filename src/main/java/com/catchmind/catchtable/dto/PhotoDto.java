package com.catchmind.catchtable.dto;

import com.catchmind.catchtable.domain.Photo;

public record PhotoDto(
        Long phIdx,
        ResAdminDto resAdminDto,
        String orgNm,
        String savedNm,
        String savedPath,
        String type

) {
    public static PhotoDto of( Long phIdx, ResAdminDto resAdminDto, String orgNm, String savedNm, String savedPath, String type){
        return new PhotoDto(phIdx,resAdminDto,orgNm,savedNm,savedPath,type);
    }

    public static PhotoDto from(Photo entity){
        return new PhotoDto(
                entity.getPhIdx(),
                ResAdminDto.from(entity.getResAdmin()),
                entity.getOrgNm(),
                entity.getSavedNm(),
                entity.getSavedPath(),
                entity.getType()

        );
    }
    public Photo toEntity() {
        return Photo.of1(
                savedPath
        );
    }
}
