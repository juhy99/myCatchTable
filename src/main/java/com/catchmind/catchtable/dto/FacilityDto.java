package com.catchmind.catchtable.dto;

import com.catchmind.catchtable.domain.Facility;

import java.time.LocalDateTime;

public record FacilityDto(
        Long facIdx,
        ResAdminDto resAdminDto,
        BistroInfoDto bistroInfoDto,
        boolean facParking,
        boolean facValet,
        boolean facCorkage,
        boolean facNokid,
        boolean facAnimal,
        boolean facHandi,
        LocalDateTime regDate
) {
    public static FacilityDto of( Long facIdx, ResAdminDto resAdminDto, BistroInfoDto bistroInfoDto, boolean facParking, boolean facValet, boolean facCorkage, boolean facNokid, boolean facAnimal, boolean facHandi, LocalDateTime regDate){
        return new FacilityDto(facIdx,resAdminDto,bistroInfoDto,facParking,facValet,facCorkage,facNokid,facAnimal,facHandi,  regDate);
    }

    public static FacilityDto from(Facility entity){
        return new FacilityDto(
                entity.getFacIdx(),
                ResAdminDto.from(entity.getResAdmin()),
                BistroInfoDto.from(entity.getBistroInfo()),
                entity.isFacParking(),
                entity.isFacValet(),
                entity.isFacCorkage(),
                entity.isFacNokid(),
                entity.isFacAnimal(),
                entity.isFacHandi(),
                entity.getRegDate()
        );
    }



}
