package com.catchmind.catchtable.domain.type;

import lombok.Getter;

public enum ReservationType {

    PLANNED("방문예정", "예약"),
    DONE("방문완료", "예약"),
    CANCEL("방문취소", "취소");


    @Getter private final String description;
    @Getter private final String des;

    ReservationType(String description, String des) {
        this.description = description;
        this.des = des;
    }
}
