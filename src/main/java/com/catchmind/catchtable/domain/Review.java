package com.catchmind.catchtable.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@DynamicInsert
@ToString(callSuper = true)
@Table(name = "review")
public class Review extends AuditingFields{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="rev_idx")
    private Long revIdx;
    @ManyToOne(optional = false)
    @JoinColumn(name = "pr_idx")
    private Profile profile;
    private Long revLike;
    private Long revComm;
    private String revContent;
    private double revScore;
    @ManyToOne(optional = false)
    @JoinColumn(name = "resa_bis_name")
    private ResAdmin resAdmin;

    @ManyToOne(optional = false)
    @JoinColumn(name = "res_idx")
    private Reserve reserve;

    protected Review(){}

    public Review(Long id, Profile profile, Long revLike, String revContent, double revScore, ResAdmin resAdmin, Reserve reserve) {
        this.revIdx = id;
        this.profile = profile;
        this.revLike = revLike;
        this.revContent = revContent;
        this.revScore = revScore;
        this.resAdmin = resAdmin;
        this.reserve = reserve;
    }

    public Review(Profile profile, String revContent, double revScore, ResAdmin resAdmin, Reserve reserve) {
        this.profile = profile;
        this.revContent = revContent;
        this.revScore = revScore;
        this.resAdmin = resAdmin;
        this.reserve = reserve;
    }

    public Review(Long id) {
        this.revIdx = id;
    }
    public Review(Long id, Long revLike) {
        this.revIdx = id;
        this.revLike = revLike;
    }

    public static Review of(Profile profile,String revContent, double revScore, ResAdmin resAdmin, Reserve reserve) {
        return new Review(profile,revContent,revScore,resAdmin,reserve);
    }
    public static Review ofIdx(Long revIdx) {
        return new Review(revIdx);
    }
    public static Review ofLike(Long revIdx, Long revLike) {
        return new Review(revIdx, revLike);
    }
}
