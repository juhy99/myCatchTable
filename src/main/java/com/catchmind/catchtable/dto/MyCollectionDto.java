package com.catchmind.catchtable.dto;

import com.catchmind.catchtable.domain.MyCollection;

import java.time.LocalDateTime;

public record MyCollectionDto(
        Long colIdx,
        String colTitle,
        String colContent,
        LocalDateTime regDate,
        LocalDateTime updateDate,
        boolean colLock,
        ProfileDto profileDto,
        String bisNames
) {
    public static MyCollectionDto from(MyCollection entity){
        return new MyCollectionDto(
                entity.getColIdx(),
                entity.getColTitle(),
                entity.getColContent(),
                entity.getRegDate(),
                entity.getUpdateDate(),
                entity.isColLock(),
                ProfileDto.from(entity.getProfile()),
                entity.getBisNames()
        );
    }
    public static MyCollectionDto of(String colTitle, String colContent, boolean colLock, ProfileDto profileDto) {
        return new MyCollectionDto(null,colTitle,colContent,null,null,colLock,profileDto,null);
    }
    public static MyCollectionDto ofBisIdx(String colTitle, String colContent, boolean colLock, ProfileDto profileDto,String bisNames) {
        return new MyCollectionDto(null,colTitle,colContent,null,null,colLock,profileDto,bisNames);
    }
    public MyCollection toEntity() {
        return MyCollection.of(
                colTitle,
                colContent,
                colLock,
                profileDto.toEntityIdx()
        );
    }

}