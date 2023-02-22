package com.catchmind.catchtable.dto;

import com.catchmind.catchtable.domain.Comment;

import java.time.LocalDateTime;

public record CommentDto(
        Long comIdx,
        ProfileDto profileDto,
        String comContent,
        ReviewDto reviewDto,
        Long comLike,
        LocalDateTime regDate
) {
    public static CommentDto from(Comment comment){
        return new CommentDto(
                comment.getComIdx(),
                ProfileDto.from(comment.getProfile()),
                comment.getComContent(),
                ReviewDto.from(comment.getReview()),
                comment.getComLike(),
                comment.getRegDate()
        );
    }

    public static CommentDto of(Long comIdx,ProfileDto profileDto, String comContent, ReviewDto reviewDto, Long comLike) {
        return new CommentDto(comIdx,profileDto,comContent,reviewDto,comLike,null);
    }
    public static CommentDto ofIdx(Long comIdx) {
        return new CommentDto(comIdx,null,null,null,0L,null);
    }
    public Comment toEntity() {
        return Comment.of(
                profileDto.toEntityIdx(), comContent, reviewDto.toEntityIdx(), comLike
        );
    }
    public Comment toEntityIdx() {
        return Comment.ofIdx(
                comIdx
        );
    }
}