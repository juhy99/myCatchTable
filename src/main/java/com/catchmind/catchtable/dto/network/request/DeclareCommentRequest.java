package com.catchmind.catchtable.dto.network.request;

import com.catchmind.catchtable.dto.CommentDto;
import com.catchmind.catchtable.dto.DeclareCommentDto;
import com.catchmind.catchtable.dto.ProfileDto;
import com.catchmind.catchtable.dto.ReviewDto;

public record DeclareCommentRequest(
        Long revIdx,
        Long comIdx,
        String decNick,
        Long prIdx,
        String decTitle,
        String decContent
) {

   public DeclareCommentRequest of(
           Long revIdx,
           Long comIdx,
           String decNick,
           Long prIdx,
           String decTitle,
           String decContent
   ){
       return new DeclareCommentRequest(revIdx, comIdx, decNick, prIdx, decTitle,decContent);
   }

   public  DeclareCommentDto toDto(){
       return DeclareCommentDto.of(ReviewDto.ofIdx(revIdx), CommentDto.ofIdx(comIdx), decNick, ProfileDto.ofIdx(prIdx), decTitle, decContent);
   }
}
