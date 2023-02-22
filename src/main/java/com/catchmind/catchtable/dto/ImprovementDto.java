package com.catchmind.catchtable.dto;

import com.catchmind.catchtable.domain.Improvement;

import java.time.LocalDateTime;

public record ImprovementDto(
        Long impIdx,
        String impTitle,
        String impContent,
        ProfileDto profileDto,
        String impAnswer,
        boolean impStatus,
        LocalDateTime regDate,
        LocalDateTime updateDate
) {
    public static ImprovementDto from(Improvement improvement){
        return new ImprovementDto(
                improvement.getImpIdx(),
                improvement.getImpTitle(),
                improvement.getImpContent(),
                ProfileDto.from(improvement.getProfile()),
                improvement.getImpAnswer(),
                improvement.isImpStatus(),
                improvement.getRegDate(),
                improvement.getUpdateDate()
                );
}

    public static ImprovementDto of(
            String impTitle,
            String impContent,
            ProfileDto profileDto,
            String impAnswer){
        return new ImprovementDto(0L, impTitle, impContent, profileDto, impAnswer, false, null, null );
    }

    public Improvement toImprovementEntity(){
        return Improvement.of(
                impTitle,
                impContent,
                profileDto.toEntityIdx(),
                impAnswer
        );
    }
}
