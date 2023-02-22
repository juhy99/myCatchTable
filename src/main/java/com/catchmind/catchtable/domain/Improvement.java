package com.catchmind.catchtable.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
@Entity
public class Improvement extends AuditingFields {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long impIdx;
    private String impTitle;
    private String impContent;
    @ManyToOne(optional = false)
    @JoinColumn(name = "pr_idx")
    private Profile profile;
    private String impAnswer;

    private boolean impStatus;

    protected Improvement(){}

    public Improvement(Long impIdx, String impTitle, String impContent, Profile profile, String impAnswer, boolean impStatus) {
        this.impIdx = impIdx;
        this.impTitle = impTitle;
        this.impContent = impContent;
        this.profile = profile;
        this.impAnswer = impAnswer;
        this.impStatus = impStatus;
    }

    public Improvement(String impTitle, String impContent, Profile profile, String impAnswer) {
        this.impTitle = impTitle;
        this.impContent = impContent;
        this.profile = profile;
        this.impAnswer = impAnswer;
    }

    public static Improvement of(String impTitle, String impContent, Profile profile, String impAnswer) {
        return new Improvement(impTitle, impContent, profile, impAnswer);
    }
}
