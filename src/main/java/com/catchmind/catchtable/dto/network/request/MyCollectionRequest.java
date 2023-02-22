package com.catchmind.catchtable.dto.network.request;

import com.catchmind.catchtable.dto.MyCollectionDto;
import com.catchmind.catchtable.dto.ProfileDto;

public record MyCollectionRequest(
        Long colIdx,
        String colTitle,
        String colContent,
        boolean colLock,
        Long prIdx,
        String bisNames
) {
    public MyCollectionDto toDto() {
        return MyCollectionDto.ofBisIdx(
                colTitle,
                colContent,
                colLock,
                ProfileDto.ofIdx(prIdx),
                bisNames
        );
    }

    public MyCollectionRequest of(
            Long colIdx,
            String colTitle,
            String colContent,
            boolean colLock,
            Long prIdx,
            String bisNames
    ){
        return new MyCollectionRequest(colIdx, colTitle, colContent, colLock, prIdx, bisNames);
    }


}
