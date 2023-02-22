package com.catchmind.catchtable.dto.network.request;

import com.catchmind.catchtable.dto.AskDto;
import com.catchmind.catchtable.dto.ProfileDto;

public record AskRequest(

        String askTitle,
        String askContent,
        Long prIdx,
        String askAnswer

) {

    public AskRequest of(
            String askTitle,
            String askContent,
            Long prIdx,
            String askAnswer
    ){
        return new AskRequest(askTitle, askContent, prIdx, askAnswer);
    }

    public AskDto toDto(){
        return AskDto.of(askTitle, askContent, ProfileDto.ofIdx(prIdx), askAnswer);
    }

}
