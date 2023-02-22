package com.catchmind.catchtable.dto;

import com.catchmind.catchtable.domain.TotalTable;

public record TotalTableDto(
        Long totTableId,
        ResAdminDto resAdminDto,
        int totCapacity,
        int totTable
) {
    public static TotalTableDto of(Long totTableId, ResAdminDto resAdminDto, int totCapacity, int totTable){
        return new TotalTableDto(totTableId,resAdminDto,totCapacity,totTable);
    };

    public static TotalTableDto from(TotalTable totalTable){
        return new TotalTableDto(
                totalTable.getTotTableId(),
                ResAdminDto.from(totalTable.getResAdmin()),
                totalTable.getTotCapacity(),
                totalTable.getTotTable()
        );
    }

    public TotalTable toEntity() {
        return TotalTable.of(
            totTableId
        );
    }
}
