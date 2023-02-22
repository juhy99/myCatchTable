package com.catchmind.catchtable.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
@Entity
public class DeclareReview extends AuditingField {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long derIdx;            // 리뷰 신고 번호

    @ManyToOne(optional = false)
    @JoinColumn(name = "rev_idx")
    private Review review;              // 리뷰 번호 FK
    private String derNick;         // 리뷰 신고당한 사람(derNick)       // 리뷰 객체 통해서 가져오기
    @ManyToOne(optional = false)
    @JoinColumn(name = "pr_idx")    // pr_idx로 찾기
    private Profile profile;        // 리뷰 신고한 회원 닉네임(prNick) FK
    private String derTitle;// 신고 제목
    private String derContent;      // 신고 사유

    protected DeclareReview() {}

    public DeclareReview(Long derIdx, Review review, Profile profile, String derNick, String derTitle, String derContent) {
        this.derIdx = derIdx;
        this.review = review;
        this.profile = profile;
        this.derNick = derNick;
        this.derTitle = derTitle;
        this.derContent = derContent;
    }

    public DeclareReview (Review review, String derNick, Profile profile,  String derTitle, String derContent){
        this.review = review;
        this.derNick = derNick;
        this.profile = profile;
        this.derTitle = derTitle;
        this.derContent = derContent;
    }

    public static DeclareReview of(Review review, String derNick, Profile profile, String derTitle, String derContent){
        return new DeclareReview(review, derNick, profile, derTitle, derContent);
    }


}
