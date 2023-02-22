package com.catchmind.catchtable.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Getter
@ToString(callSuper = true)
public class DeclareComment extends AuditingField {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long decIdx;                // 댓글 신고 번호

    @ManyToOne(optional = false)
    @JoinColumn(name = "rev_idx")
    private Review review;                  // 리뷰 번호(revIdx)         // 리뷰 테이블의 번호

    @ManyToOne(optional = false)
    @JoinColumn(name = "com_idx")
    private Comment comment;             // 댓글 번호(com_idx)        // 댓글 테이블의 번호

    private String decNick;             // 댓글 신고당한 사람(decNick)      // 리뷰 객체 통해서 가져오기

    @ManyToOne(optional = false)
    @JoinColumn(name = "pr_idx")        // pr_idx로 찾기
    private Profile profile;            // 댓글 신고한 사람(pr_nick)       // 회원테이블의 닉네임

    private String decTitle;

    private String decContent;          // 신고 사유                // 회원테이블의 닉네임

    public DeclareComment() {}

    public DeclareComment(Long decIdx, Review review, Comment comment, String decNick, Profile profile, String decTitle, String decContent) {
        this.decIdx = decIdx;
        this.review = review;
        this.comment = comment;
        this.decNick = decNick;
        this.profile = profile;
        this.decTitle = decTitle;
        this.decContent = decContent;
    }


    public DeclareComment(Review review, Comment comment, String decNick, Profile profile, String decTitle, String decContent) {
        this.review = review;
        this.comment = comment;
        this.decNick = decNick;
        this.profile = profile;
        this.decTitle = decTitle;
        this.decContent = decContent;
    }

    public static  DeclareComment of(Review review, Comment comment, String decNick, Profile profile, String decTitle, String decContent){
        return new DeclareComment(review, comment, decNick, profile, decTitle, decContent);
    }

}
