package com.catchmind.catchtable.dto;

public record BookmarkDto(
        BistroInfoDto bistroInfoDto,Boolean isSaved
) {
    public static BookmarkDto of(BistroInfoDto bistroInfoDto, Boolean isSaved) {
        return new BookmarkDto(bistroInfoDto, isSaved);
    }

    public static BookmarkDto from(BistroInfoDto bistroInfoDto, Boolean isSaved) {
        return new BookmarkDto(
                bistroInfoDto
                , isSaved
//                bistroInfo.getBisConvenience(),
        );
    }
}
