package com.catchmind.catchtable.dto.network.request;

import com.catchmind.catchtable.dto.BistroDetailDto;
import com.catchmind.catchtable.dto.BistroSaveDto;
import com.catchmind.catchtable.dto.ProfileDto;
import com.catchmind.catchtable.dto.ResAdminDto;

public record BistroSaveRequest(
        Long saveIdx,
        String resaBisName,
        Long prIdx,
        Long bdIdx,
        Long colIdx
) {
    public BistroSaveDto toDto() {
        return BistroSaveDto.of(
                saveIdx,
                ResAdminDto.ofName(resaBisName),
                ProfileDto.ofIdx(prIdx),
                BistroDetailDto.ofIdx(bdIdx),
                colIdx
        );
    }
}



//save_idx	'내가 저장한 식당' 번호	int
//resa_bis_name	식당 이름	varchar
//pr_idx	회원 번호	int
//bd_idx	식당 번호	int
//col_idx	컬렉션 번호	int