package com.catchmind.catchtable.repository;

import com.catchmind.catchtable.domain.Follow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FollowRepository extends JpaRepository<Follow,Long> {
    // 팔로잉리스트
    List<Follow> findFollowingByFollowing_prIdx(Long prIdx);
    List<Follow> findByFollowing_prIdx(Long prIdx);
    List<Follow> findFollowerByFollower_prIdx(Long prIdx);
    List<Follow> findByFollower_prIdx(Long prIdx);
    Optional<Follow> deleteByFollower_prIdxAndFollowing_prIdx(Long follower, Long following);
    Long countByFollower_PrIdx(Long prIdx);
    Long countByFollowing_PrIdx(Long prIdx);
}
