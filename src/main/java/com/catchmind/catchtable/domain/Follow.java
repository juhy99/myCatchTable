package com.catchmind.catchtable.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Follow extends AuditingField{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long foIdx;

    @ManyToOne
    @JoinColumn(name = "following")
    Profile following;

    @ManyToOne
    @JoinColumn(name = "follower")
    Profile follower;

    protected Follow() {}

    public Follow(Profile following, Profile follower) {
        this.following = following;
        this.follower = follower;
    }

    public static Follow of(Profile following, Profile follower) {
        return new Follow(following,follower);
    }
}
