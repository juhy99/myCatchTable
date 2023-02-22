package com.catchmind.catchtable.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Sns {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long snsIdx;
    @ManyToOne
    @JoinColumn(name="pr_idx")
    private Profile profile;
    private String snsAddr;
    private String snsType;

    protected Sns() {}

    public Sns(Profile profile, String snsAddr, String snsType) {
        this.profile = profile;
        this.snsAddr = snsAddr;
        this.snsType = snsType;
    }
    public static Sns of(Profile profile, String snsAddr, String snsType) {
        return new Sns(profile, snsAddr, snsType);
    }

}
