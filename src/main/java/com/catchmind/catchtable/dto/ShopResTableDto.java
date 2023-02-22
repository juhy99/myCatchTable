package com.catchmind.catchtable.dto;

import com.catchmind.catchtable.domain.ShopResTable;

public record ShopResTableDto(
        Long shopResId,
        TotalTableDto totalTableDto,
        boolean shopResStatus,
        String shopResMonth,
        String shopResDay,
        String shopResTime
) {
    public static ShopResTableDto of(Long shopResId, TotalTableDto totalTableDto, boolean shopResStatus, String shopResMonth,String shopResDay,String shopResTime){
        return new ShopResTableDto(shopResId,totalTableDto,shopResStatus,shopResMonth,shopResDay,shopResTime);
    };

    public static ShopResTableDto from(ShopResTable shopResTable){
        return new ShopResTableDto(
                shopResTable.getShopResId(),
                TotalTableDto.from(shopResTable.getTotalTable()),
                shopResTable.isShopResStatus(),
                shopResTable.getShopResMonth(),
                shopResTable.getShopResDay(),
                shopResTable.getShopResTime()
        );
    }

    public ShopResTable toEntity(){
        return ShopResTable.of(shopResId,totalTableDto.toEntity(), shopResStatus, shopResMonth, shopResDay, shopResTime);
    }
}
