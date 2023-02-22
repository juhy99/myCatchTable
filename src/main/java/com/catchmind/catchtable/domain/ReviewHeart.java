package com.catchmind.catchtable.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class ReviewHeart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long heIdx;
    @ManyToOne(optional = false)
    @JoinColumn(name="pr_idx")
    private Profile profile;

    @ManyToOne(optional = false)
    @JoinColumn(name="rev_idx")
    private Review review;

    protected ReviewHeart() {}

    public ReviewHeart(Profile profile, Review review) {
        this.profile = profile;
        this.review = review;
    }
    public static ReviewHeart of(Profile profile, Review review) {
        return new ReviewHeart(profile,review);
    }
}
