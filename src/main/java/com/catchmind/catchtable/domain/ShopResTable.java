package com.catchmind.catchtable.domain;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@ToString(callSuper = true)
@Entity
public class ShopResTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long shopResId;
    @Setter @ManyToOne(optional = false) @JoinColumn(name="totTableId") TotalTable totalTable;
    @Setter private boolean shopResStatus;
    @Setter private String shopResMonth;
    @Setter private String shopResDay;
    @Setter private String shopResTime;

    protected ShopResTable() {}

    private ShopResTable(Long shopResId,TotalTable totalTable, boolean shopResStatus, String shopResMonth,String shopResDay, String shopResTime){
        this.shopResId = shopResId;
        this.totalTable = totalTable;
        this.shopResStatus = shopResStatus;
        this.shopResMonth = shopResMonth;
        this.shopResDay = shopResDay;
        this.shopResTime = shopResTime;
    }


    public static ShopResTable of(Long shopResId,TotalTable totalTable, boolean shopResStatus, String shopResMonth,String shopResDay, String shopResTime){
        return new ShopResTable(shopResId,totalTable, shopResStatus, shopResMonth,shopResDay, shopResTime);
    }
}
