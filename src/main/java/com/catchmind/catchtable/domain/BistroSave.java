package com.catchmind.catchtable.domain;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@ToString
@Entity
@Data
public class BistroSave {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long saveIdx;

    @ManyToOne(optional = false) @JoinColumn(name = "resaBisName")
    private ResAdmin resAdmin;

    @ManyToOne(optional = false) @JoinColumn(name = "prIdx")
    private Profile profile;
    @ManyToOne(optional = false)
    @JoinColumn(name = "bdIdx")
    private BistroDetail bistroDetail;

    private Long colIdx;

    protected BistroSave() {}

    @Builder
    public BistroSave(Long saveIdx, ResAdmin resAdmin, Profile profile, BistroDetail bistroDetail, Long colIdx) {
        this.saveIdx = saveIdx;
        this.resAdmin = resAdmin;
        this.profile = profile;
        this.bistroDetail = bistroDetail;
        this.colIdx = colIdx;
    }

    @Builder
    public BistroSave(Profile profile, ResAdmin resAdmin, BistroDetail bistroDetail) {
        this.resAdmin = resAdmin;
        this.profile = profile;
        this.bistroDetail = bistroDetail;
    }

    public static BistroSave of(Profile profile, ResAdmin resAdmin, BistroDetail bistroDetail) {
        return new BistroSave(profile, resAdmin, bistroDetail);

    }


}
