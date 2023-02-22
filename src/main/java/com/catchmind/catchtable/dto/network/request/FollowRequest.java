package com.catchmind.catchtable.dto.network.request;

import com.catchmind.catchtable.dto.FollowDto;
import com.catchmind.catchtable.dto.ProfileDto;

public record FollowRequest(
        Long following,
        Long follower
) {
    public static FollowRequest of(Long following, Long follower) {
        return new FollowRequest(following,follower);
    }

    public FollowDto toDto() {
        return FollowDto.of(
                ProfileDto.ofIdx(following),
                ProfileDto.ofIdx(follower)
        );
    }
}
