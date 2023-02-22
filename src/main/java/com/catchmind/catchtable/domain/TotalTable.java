package com.catchmind.catchtable.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@ToString(callSuper = true)
@Entity
public class TotalTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long totTableId;
    @Setter @ManyToOne(optional = false) @JoinColumn(name="resaBisName") private ResAdmin resAdmin;
    @Setter private int totCapacity;
    @Setter private int totTable;

    protected TotalTable() {}

    private TotalTable(ResAdmin resAdmin, int totCapacity, int totTable){
        this.resAdmin = resAdmin;
        this.totCapacity = totCapacity;
        this.totTable = totTable;
    }

    public TotalTable(Long totTableId){
        this.totTableId = totTableId;
    }


    public static TotalTable of(ResAdmin resAdmin, int totCapacity, int totTable){
        return new TotalTable(resAdmin,totCapacity,totTable);
    }

    public static TotalTable of(Long totTableId){
        return new TotalTable(totTableId);
    }
}
