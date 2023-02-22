package com.catchmind.catchtable.dto;

import com.catchmind.catchtable.domain.Ask;

import java.time.LocalDateTime;

public record AskDto(
        Long askIdx,
        String askTitle,
        String askContent,
        ProfileDto profileDto,
        String askAnswer,
        boolean askStatus,
        LocalDateTime regDate,
        LocalDateTime updateDate
) {

    public static AskDto from(Ask ask){
        return new AskDto(
                ask.getAskIdx(),
                ask.getAskTitle(),
                ask.getAskContent(),
                ProfileDto.from(ask.getProfile()),
//                ask.getProfile().getPrIdx(),
                ask.getAskAnswer(),
                ask.isAskStatus(),
                ask.getRegDate(),
                ask.getUpdateDate()
        );
    }

    public static AskDto of(
            String askTitle,
            String askContent,
            ProfileDto profileDto,
            String askAnswer){
        return  new AskDto(0L, askTitle, askContent,profileDto, askAnswer, false, null, null );
    }



    public Ask toEntity(){
        return Ask.of(
                askTitle,
                askContent,
                profileDto.toEntityIdx(),
                askAnswer

        );
    }

    public Ask toEntity1(){
        return Ask.of1(
                askTitle,
                askContent
        );
    }
}
