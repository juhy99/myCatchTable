package com.catchmind.catchtable.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class CommentHeart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long heIdx;
    @ManyToOne
    @JoinColumn(name="pr_idx")
    private Profile profile;

    @ManyToOne
    @JoinColumn(name="rev_idx")
    private Review review;

    @ManyToOne
    @JoinColumn(name="com_idx")
    private Comment comment;

    protected CommentHeart() {}

    public CommentHeart(Profile profile, Review review, Comment comment) {
        this.profile = profile;
        this.comment = comment;
        this.review = review;
    }

    public static CommentHeart of(Profile profile, Review review, Comment comment){
        return new CommentHeart(profile,review,comment);
    }
}

