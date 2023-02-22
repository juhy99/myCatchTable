package com.catchmind.catchtable.domain;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
@ToString(callSuper = true)
@Table(name = "review_photo")
public class ReviewPhoto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long phIdx;
    String orgNm;
    String savedNm;
    String savedPath;

    @ManyToOne(optional = false)
     @JoinColumn(name="rev_idx")
    Review review;

    public ReviewPhoto(String orgNm, String savedNm, String savedPath, Review review) {
        this.orgNm = orgNm;
        this.savedNm = savedNm;
        this.savedPath = savedPath;
        this.review = review;
    }

    protected ReviewPhoto() {}

    public static ReviewPhoto of(String orgNm, String savedNm, String savedPath, Review review){
        return new ReviewPhoto(orgNm, savedNm, savedPath, review);
    }
}
