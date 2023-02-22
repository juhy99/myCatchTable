package com.catchmind.catchtable.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@ToString(callSuper = true)
public class Comment extends AuditingField {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long comIdx;                    // 댓글 번호
    @ManyToOne(optional = false)
    @JoinColumn(name = "pr_idx")            // pr_idx로 찾기
    private Profile profile;                // 댓글 작성자 닉네임       // 회원 정보 테이블의 닉네임
    private String comContent;              // 댓글 내용
    @ManyToOne(optional = false)
    @JoinColumn(name = "rev_idx")
    private Review review;                  // 리뷰 번호
    private Long comLike;       // 댓글 좋아요 갯수

    protected Comment() {}

    public Comment(Profile profile, String comContent, Review review, Long comLike){
        this.comContent = comContent;
        this.profile = profile;
        this.review = review;
        this.comLike = comLike;
    }
    public Comment(Long comIdx){
        this.comIdx = comIdx;
    }

    public static Comment of(Profile profile, String comContent, Review review, Long comLike) {
        return new Comment(profile,comContent,review, comLike);
    }
    public static Comment ofIdx(Long comIdx) {
        return new Comment(comIdx);
    }
}
