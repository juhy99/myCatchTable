package com.catchmind.catchtable.controller;

import com.catchmind.catchtable.domain.Follow;
import com.catchmind.catchtable.dto.*;
import com.catchmind.catchtable.dto.network.request.CommentHeartRequest;
import com.catchmind.catchtable.dto.network.request.FollowRequest;
import com.catchmind.catchtable.dto.network.request.ReviewHeartRequest;
import com.catchmind.catchtable.dto.network.response.*;
import com.catchmind.catchtable.dto.security.CatchPrincipal;
import com.catchmind.catchtable.service.PaginationService;
import com.catchmind.catchtable.service.TimeLineService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("timeline")
@RequiredArgsConstructor
@Slf4j
public class TimelineController {
    private final TimeLineService timeLineService;
    private final PaginationService paginationService;

    // 전체 리뷰 페이지
    @GetMapping("")
    public String timeline(ModelMap map, @AuthenticationPrincipal CatchPrincipal catchPrincipal,
                           @PageableDefault(size = 10, sort = "revIdx", direction = Sort.Direction.DESC) Pageable pageable) {
        if (catchPrincipal == null) {
            Page<ReviewResponse> reviews = timeLineService.getReviews(pageable);
            List<Integer> barNumbers = paginationService.getPaginationBarNumber(pageable.getPageNumber(), reviews.getTotalPages());
            map.addAttribute("reviews", reviews);
            map.addAttribute("prIdx", 0);
            map.addAttribute("paginationBarNumbers", barNumbers);
        } else {
            Long prIdx = catchPrincipal.prIdx();
            Page<ReviewResponse> reviews = timeLineService.getReviews(pageable, prIdx);
            List<Integer> barNumbers = paginationService.getPaginationBarNumber(pageable.getPageNumber(), reviews.getTotalPages());
            map.addAttribute("reviews", reviews);
            map.addAttribute("prIdx", prIdx);
            map.addAttribute("paginationBarNumbers", barNumbers);
        }
        return "timeline/timeline-main";
    }

    // 프로필페이지
    @GetMapping("/profile/{timeLineIdx}")
    public String profile(@PathVariable Long timeLineIdx, @AuthenticationPrincipal CatchPrincipal catchPrincipal, ModelMap map,
                          @PageableDefault(size = 10, sort = "revIdx", direction = Sort.Direction.DESC) Pageable pageable) {
        if (catchPrincipal == null) {
            TimeLineResponse profile = timeLineService.getHeader(timeLineIdx);
            boolean isFollow = false;
            map.addAttribute("isFollow", isFollow);
            map.addAttribute("timeLineIdx", timeLineIdx);
            map.addAttribute("profile", profile);
            map.addAttribute("prIdx", 0);
        } else {
            Long prIdx = catchPrincipal.prIdx();
            Page<ReviewResponse> response = timeLineService.getReview(prIdx, timeLineIdx, pageable);
            boolean isFollow = false;
            List<FollowDto> loginFollowing = timeLineService.getFollowerList(prIdx);
            TimeLineResponse profile = timeLineService.getHeader(timeLineIdx);
            for (FollowDto follow : loginFollowing) {
                System.out.println("following" + follow.following());
                System.out.println("follower" + follow.follower());
                if (timeLineIdx == follow.following().prIdx()) {
                    isFollow = true;
                }
            }
            System.out.println(isFollow);

            map.addAttribute("profile", profile);
            map.addAttribute("reviews", response);
            map.addAttribute("timeLineIdx", timeLineIdx);
            map.addAttribute("prIdx", prIdx);
            map.addAttribute("isFollow", isFollow);
        }

        return "timeline/profile";
    }

    // 공개된 컬렉션 페이지
    @GetMapping("/collection/{timeLineIdx}")
    public String collection(@PathVariable Long timeLineIdx, ModelMap map) {
        List<MyCollectionDto> response = timeLineService.getCollection(timeLineIdx);
        String prNick = timeLineService.getHeader(timeLineIdx).prNick();
        map.addAttribute("collections", response);
        map.addAttribute("prNick", prNick);
        map.addAttribute("timelineIdx", timeLineIdx);
        return "timeline/profile-collection";
    }

    //  공개된 컬렉션 상세 페이지
    @GetMapping("/collection/{timeLineIdx}/detail/{colIdx}")
    public String collection(@PathVariable Long timeLineIdx, @PathVariable Long colIdx,
                             @PageableDefault(size = 10, sort = "colIdx", direction = Sort.Direction.DESC) Pageable pageable, ModelMap map) {
        List<BistroSaveDto> responses = timeLineService.getCollectionDetail(colIdx);
        System.out.println(responses);
        MyCollectionDto myCollectionDto = timeLineService.collection(colIdx);
        String prNick = timeLineService.getHeader(timeLineIdx).prNick();
        System.out.println(responses);
        map.addAttribute("prNick", prNick);
        map.addAttribute("collection", myCollectionDto);
        map.addAttribute("timelineIdx", timeLineIdx);
        map.addAttribute("savedBistro", responses);
        return "timeline/timeline-collectionDetail";
    }


    // 프로필 리뷰 페이지
    @GetMapping("/review/{timeLineIdx}")
    public String review(@PathVariable Long timeLineIdx, @AuthenticationPrincipal CatchPrincipal catchPrincipal, ModelMap map,
                         @PageableDefault(size = 10, sort = "colIdx", direction = Sort.Direction.DESC) Pageable pageable) {
        if(catchPrincipal == null) {
            Page<ReviewResponse> response = timeLineService.getReview(null, timeLineIdx, pageable);
            List<Integer> barNumbers = paginationService.getPaginationBarNumber(pageable.getPageNumber(), response.getTotalPages());
            TimeLineResponse profile = timeLineService.getHeader(timeLineIdx);
            map.addAttribute("profile", profile);
            map.addAttribute("reviews", response);
            map.addAttribute("paginationBarNumbers", barNumbers);
            map.addAttribute("prIdx", 0);
            map.addAttribute("timeLineIdx", timeLineIdx);
        } else {
            Long prIdx = catchPrincipal.prIdx();
            Page<ReviewResponse> response = timeLineService.getReview(prIdx, timeLineIdx, pageable);
            List<Integer> barNumbers = paginationService.getPaginationBarNumber(pageable.getPageNumber(), response.getTotalPages());
            TimeLineResponse profile = timeLineService.getHeader(timeLineIdx);
            map.addAttribute("profile", profile);
            map.addAttribute("reviews", response);
            map.addAttribute("paginationBarNumbers", barNumbers);
            map.addAttribute("prIdx", prIdx);
            map.addAttribute("timeLineIdx", timeLineIdx);

        }

        return "timeline/profile-review";
    }

    // 팔로잉 리스트
    @GetMapping("/following/{timeLineIdx}")
    public String following(@PathVariable Long timeLineIdx, @AuthenticationPrincipal CatchPrincipal catchPrincipal, ModelMap map) {
        // 로그인 안한 회원
        if (catchPrincipal == null) {
            TimeLineResponse profile = timeLineService.getHeader(timeLineIdx);
            List<FollowDto> followingDtos = timeLineService.getFollowerList(timeLineIdx);
            boolean isFollow = false;
            List<FollowResponse> followingList = new ArrayList<>();
            for (FollowDto follow : followingDtos) {
                FollowResponse response = new FollowResponse(follow.following().prIdx(), follow.following().prNick(), follow.following().prIntro(), isFollow);
                followingList.add(response);
            }
            map.addAttribute("timeLineIdx", timeLineIdx);
            map.addAttribute("profile", profile);
            map.addAttribute("prIdx", 0);
            map.addAttribute("followings", followingList);
            // 로그인한 유저
        } else {
            Long prIdx = catchPrincipal.prIdx();
            TimeLineResponse profile = timeLineService.getHeader(timeLineIdx);
            List<FollowDto> followingDtos = timeLineService.getFollowerList(timeLineIdx);
            List<FollowDto> loginDtos = timeLineService.getFollowerList(prIdx);
            List<FollowResponse> followingList = new ArrayList<>();
            boolean isFollow = false;
            for (FollowDto follow : followingDtos) {
                for (FollowDto login : loginDtos) {
                    if (follow.following().prIdx() == login.following().prIdx()) {
                        isFollow = true;
                        break;
                    }
                }
                FollowResponse response = new FollowResponse(follow.following().prIdx(), follow.following().prNick(), follow.following().prIntro(), isFollow);
                followingList.add(response);
            }

            System.out.println("비교 후 리스트 : " + followingList);

            map.addAttribute("prIdx", prIdx);
            map.addAttribute("profile", profile);
            map.addAttribute("timeLineIdx", timeLineIdx);
            map.addAttribute("followings", followingList);
        }

        return "timeline/followingList";
    }

    // 팔로워 리스트
    @GetMapping("/follower/{timeLineIdx}")
    public String follower(@PathVariable Long timeLineIdx, @AuthenticationPrincipal CatchPrincipal catchPrincipal, ModelMap map) {
        if (catchPrincipal == null) {
            TimeLineResponse profile = timeLineService.getHeader(timeLineIdx);
            List<FollowDto> followerDtos = timeLineService.getFollowingList(timeLineIdx);
            boolean isFollow = false;
            List<FollowResponse> followerList = new ArrayList<>();
            for (FollowDto follow : followerDtos) {
                FollowResponse response = new FollowResponse(follow.follower().prIdx(), follow.follower().prNick(), follow.follower().prIntro(), isFollow);
                followerList.add(response);
            }
            map.addAttribute("timeLineIdx", timeLineIdx);
            map.addAttribute("profile", profile);
            map.addAttribute("prIdx", 0);
            map.addAttribute("followers", followerList);

        } else {
            Long prIdx = catchPrincipal.prIdx();
            TimeLineResponse profile = timeLineService.getHeader(timeLineIdx);

            List<FollowDto> followerDtos = timeLineService.getFollowingList(timeLineIdx);
            List<FollowDto> loginDtos = timeLineService.getFollowerList(prIdx);
            List<FollowResponse> followerList = new ArrayList<>();
            boolean isFollow = false;
            for (FollowDto follow : followerDtos) {
                for (FollowDto login : loginDtos) {
                    if (follow.follower().prIdx() == login.following().prIdx()) {
                        isFollow = true;
                        break;
                    }
                }
                FollowResponse response = new FollowResponse(follow.follower().prIdx(), follow.follower().prNick(), follow.follower().prIntro(), isFollow);
                followerList.add(response);
            }
            System.out.println("검사후 팔로워 리스트 : " + followerList);


            map.addAttribute("prIdx", prIdx);
            map.addAttribute("timeLineIdx", timeLineIdx);
            map.addAttribute("followers", followerList);
            map.addAttribute("profile", profile);
        }

        return "timeline/followerList";
    }

    // 팔로우 기능
    @PostMapping(path = "/following")
    @ResponseBody
    public String follow(@RequestBody FollowRequest request) {
        System.out.println(request);
        Follow response = timeLineService.follow(request);
        if (response != null) {
            return "OK";
        }
        return "NO";
    }

    // 언팔로우 기능
    @PostMapping(path = "/unfollowing")
    @ResponseBody
    public String unfollow(@RequestBody FollowRequest request) {
        System.out.println(request);
        Optional<Follow> response = timeLineService.unfollow(request);
        if (response != null) {
            return "OK";
        }
        return "NO";
    }

    // 좋아요 및 댓글 갯수 리스트
    @GetMapping("/review/total")
    @ResponseBody
    public List<ReviewHeartWithCommResponse> heartWithComm(@AuthenticationPrincipal CatchPrincipal catchPrincipal) {
        List<ReviewHeartWithCommResponse> totalList = new ArrayList<>();
        if (catchPrincipal == null) {
            List<ReviewDto> reviews = timeLineService.reviews();

            for (ReviewDto review : reviews) {
                boolean isLike = false;
                ReviewHeartWithCommResponse response = new ReviewHeartWithCommResponse(null, review.revIdx(), isLike, review.revLike(), review.revComm());
                totalList.add(response);
            }
        } else {
            Long prIdx = catchPrincipal.prIdx();
            List<ReviewDto> reviews = timeLineService.reviews();
            List<ReviewHeartDto> hearts = timeLineService.getReviewHeart(prIdx);        // 좋아요 여부 판단을 위한 코드

            for (ReviewDto review : reviews) {
                boolean isLike = false;
                for (ReviewHeartDto heart : hearts) {
                    if (review.revIdx().equals(heart.reviewDto().revIdx())) {
                        isLike = true;
                        break;
                    }
                }
                ReviewHeartWithCommResponse response = new ReviewHeartWithCommResponse(prIdx, review.revIdx(), isLike, review.revLike(), review.revComm());
                totalList.add(response);
            }
            log.info("좋아요 여부 검사 후 전체 리뷰 리스트 : " + totalList);

        }
        return totalList;
    }


    // 전체 댓글 리스트, 좋아요 여부 판단
    @GetMapping("/review/comment")
    @ResponseBody
    public List<CommentResponse> commentList(@AuthenticationPrincipal CatchPrincipal catchPrincipal) {
        List<CommentResponse> comList = new ArrayList<>();
        if (catchPrincipal == null) {
            List<CommentDto> comments = timeLineService.getComments();
            for (CommentDto com : comments) {
                CommentResponse response = new CommentResponse(com.comIdx(),
                        com.profileDto().prNick(), com.profileDto().prIdx(), com.comContent(), com.reviewDto().revIdx(), com.regDate(), false);
                comList.add(response);
            }
        } else {
            Long prIdx = catchPrincipal.prIdx();
            List<CommentDto> comments = timeLineService.getComments();

            for (CommentDto com : comments) {
                boolean isComm = com.profileDto().prIdx().equals(prIdx);
                CommentResponse response = new CommentResponse(com.comIdx(),
                        com.profileDto().prNick(), com.profileDto().prIdx(), com.comContent(), com.reviewDto().revIdx(), com.regDate(), isComm);
                comList.add(response);
            }
            log.info("댓글 작성 유무 및 좋아요 판별 댓글 리스트 " + comList);

        }

        return comList;
    }


    // 좋아요 기능
    @PostMapping(path = "/new/heart")
    @ResponseBody
    public Long newHeart(@RequestBody ReviewHeartRequest request) {
        System.out.println("❤️" + request.revLike());
        Long response = timeLineService.newHeart(request);
        System.out.println(response);
        return response;
    }

    //좋아요 삭제
    @PostMapping(path = "/del/heart")
    @ResponseBody
    public Long deleteHeart(@RequestBody ReviewHeartRequest request) {
        System.out.println("💙" + request.revLike());
        System.out.println(request);
        Long response = timeLineService.delHeart(request);
        return response;
    }

    // 댓글 등록
    @PostMapping(path = "/new/comment")
    @ResponseBody
    public Long newComment(@RequestBody CommentHeartRequest request) {
        System.out.println(request);
        Long response = timeLineService.newComment(request);
        log.info("새로운 댓글 번호 : " + response);
        return response;
    }

    // 댓글 삭제
    @GetMapping("/del/comment/{comIdx}/{revIdx}")
    @ResponseBody
    public Long delComm(@PathVariable Long comIdx, @PathVariable Long revIdx) {
        System.out.println("삭제" + comIdx);
        System.out.println("삭제" + revIdx);
        Long com = timeLineService.delComment(comIdx, revIdx);
        return com;
    }

    // 리뷰삭제
    @GetMapping("/del/review/{revIdx}/{resIdx}/{prIdx}")
    @ResponseBody
    public String delReview(@PathVariable Long revIdx, @PathVariable Long resIdx, @PathVariable Long prIdx) {
        timeLineService.delReview(revIdx, resIdx, prIdx);
        return "OK";
    }

    // 새로운 댓글
    @GetMapping(path = "/review/get/comment/{comIdx}")
    @ResponseBody
    public CommentResponse getComment(@PathVariable Long comIdx) {
        System.out.println(comIdx);
        CommentDto response = timeLineService.getComment(comIdx);
        CommentResponse newCom = new CommentResponse(response.comIdx(), response.profileDto().prNick(), response.profileDto().prIdx(),
                response.comContent(), response.reviewDto().revIdx(), response.regDate(), true);
        log.info(String.valueOf(newCom));
        return newCom;
    }

}
