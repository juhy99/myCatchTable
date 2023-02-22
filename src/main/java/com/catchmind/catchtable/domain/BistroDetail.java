package com.catchmind.catchtable.domain;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

//@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@ToString(callSuper = true)
public class BistroDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bdIdx;
    private String bdNotice;
    private String bdPark;
    private String bdAddr;
    private String bdHp;
    private String bdIntro;
    private String bdCaution;
    private String bdHour;
    private String bdHoliday;
    private String bdHomepage;
    @ManyToOne(optional = false)
    @JoinColumn(name = "resa_bis_name")
    private ResAdmin resAdmin;
    @ManyToOne(optional = false)
    @JoinColumn(name = "bis_idx")
    private BistroInfo bistroInfo;

    protected BistroDetail(){}

    @Builder
    public BistroDetail(Long bdIdx, String bdNotice, String bdPark, String bdAddr, String bdHp, String bdIntro, String bdCaution, String bdHour, String bdHoliday, String bdHome, ResAdmin resAdmin) {
        this.bdIdx = bdIdx;
        this.bdNotice = bdNotice;
        this.bdPark = bdPark;
        this.bdAddr = bdAddr;
        this.bdHp = bdHp;
        this.bdIntro = bdIntro;
        this.bdCaution = bdCaution;
        this.bdHour = bdHour;
        this.bdHoliday = bdHoliday;
        this.bdHomepage = bdHome;
        this.resAdmin = resAdmin;
    }
    public BistroDetail(Long bdIdx) {
        this.bdIdx = bdIdx;
    }

    public static BistroDetail of(Long bdIdx, String bdNotice, String bdPark, String bdAddr, String bdHp, String bdIntro, String bdCaution, String bdHour, String bdHoliday, String bdHome, ResAdmin resAdmin) {
        return new BistroDetail(bdIdx, bdNotice, bdPark,bdAddr,bdHp,bdIntro,bdCaution,bdHour,bdHoliday,bdHome,resAdmin);
    }

    public static BistroDetail ofIdx(Long bdIdx) {
        return new BistroDetail(bdIdx);
    }
}
