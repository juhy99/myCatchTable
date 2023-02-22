package com.catchmind.catchtable.dto;

import com.catchmind.catchtable.domain.Pending;

public record PendingDto(
        Long penIdx,
        String penBisName,
        String penName,
        String penHp,
        String penRegion,
        Long penBook,
        String penFoodtype

) {
    public static PendingDto of(
            String penBisName,
            String penName,
            String penHp,
            String penRegion,
            Long penBook,
            String penFoodtype
    ) {
        return new PendingDto(0L, penBisName, penName, penHp, penRegion, penBook, penFoodtype);
    }

    public static PendingDto from(Pending pending){
        return new PendingDto(
                pending.getPenIdx(),
                pending.getPenBisName(),
                pending.getPenName(),
                pending.getPenHp(),
                pending.getPenRegion(),
                pending.getPenBook(),
                pending.getPenFoodtype()
        );
    }

    public Pending toPendingEntity(){
        return Pending.of(penBisName, penName, penHp, penRegion, penBook, penFoodtype);
    }
}
