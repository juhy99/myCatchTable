package com.catchmind.catchtable.dto.network.request;


import com.catchmind.catchtable.dto.*;

public record PaymentRequest(
        String total_amount,
        String resaBisName,
        String prName,
        String totalPrice,
        String resHp,
        String resRequest,
        String resPerson,
        String resTime,
        String resMonth,
        String resDay,
        Long bisIdx,
        Long bdIdx,
        Long prIdx
) {
    public static PaymentRequest of(
            String resaBisName,
            String prName,
            String resHp,
            String resRequest,
            String resPerson,
            String resTime,
            String resMonth,
            String resDay,
            Long bisIdx,
            Long bdIdx,
            Long prIdx

    ) {
        return new PaymentRequest(null, resaBisName, prName, null, resHp, resRequest, resPerson, resTime, resMonth, resDay, bisIdx, bdIdx, prIdx);
    }

    public ReserveDto toDto() {
        return ReserveDto.ofKaKao(ResAdminDto.of(resaBisName), prName, resHp, resRequest, Long.valueOf(resPerson), resTime, resMonth, resDay, BistroInfoDto.ofIdx(bisIdx), BistroDetailDto.ofIdx(bdIdx), ProfileDto.ofIdx(prIdx));
    }
}
