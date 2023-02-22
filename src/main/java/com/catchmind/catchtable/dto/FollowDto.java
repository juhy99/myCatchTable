package com.catchmind.catchtable.dto;

import com.catchmind.catchtable.domain.Follow;

public record FollowDto(
        Long foIdx,
        ProfileDto following,
        ProfileDto follower
) {
    public static FollowDto from(Follow follow) {
        return new FollowDto(
                follow.getFoIdx(),
                ProfileDto.from(follow.getFollowing()),
                ProfileDto.from(follow.getFollower())
        );
    }

    public static FollowDto of(ProfileDto following, ProfileDto follower) {
        return new FollowDto(null, following,follower);
    }

    public Follow toEntity() {
        return Follow.of(
            following.toEntityIdx(), follower.toEntityIdx()
        );
    }
}
