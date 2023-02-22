package com.catchmind.catchtable.dto;

import com.catchmind.catchtable.domain.BistroDetail;

public record BistroDetailDto(
        ResAdminDto resAdminDto,
        Long bdIdx,
        String bdNotice,
        String bdPark,
        String bdAddr,
        String bdHp,
        String bdIntro,
        String bdCaution,
        String bdHour,
        String bdHoliday,
        String bdHome,
        BistroInfoDto bistroInfoDto
) {
    public static BistroDetailDto of(ResAdminDto resAdminDto, Long bdIdx, String bdNotice, String bdPark, String bdAddr, String bdHp, String bdIntro, String bdCaution, String bdHour, String bdHoliday, String bdHome, BistroInfoDto bistroInfoDto) {
        return new BistroDetailDto(resAdminDto, bdIdx, bdNotice, bdPark, bdAddr, bdHp, bdIntro, bdCaution, bdHour, bdHoliday, bdHome, bistroInfoDto);
    }
    public static BistroDetailDto ofIdx(Long bdIdx) {
        return new BistroDetailDto(null,bdIdx,null,null,null,null,null,null,null,null,null,null);
    }

    public static BistroDetailDto from(BistroDetail bistroDetail) {
        return new BistroDetailDto(
                ResAdminDto.from(bistroDetail.getResAdmin()),
//                bistroDetail.getResAdmin().getResaBisName(),
                bistroDetail.getBdIdx(),
                bistroDetail.getBdNotice(),
                bistroDetail.getBdPark(),
                bistroDetail.getBdAddr(),
                bistroDetail.getBdHp(),
                bistroDetail.getBdIntro(),
                bistroDetail.getBdCaution(),
                bistroDetail.getBdHour(),
                bistroDetail.getBdHoliday(),
                bistroDetail.getBdHomepage(),
                BistroInfoDto.from(bistroDetail.getBistroInfo())

        );
    }

    public BistroDetail toEntityIdx() {
        return BistroDetail.ofIdx(bdIdx);
    }

}
