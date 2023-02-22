package com.catchmind.catchtable.service;

import com.catchmind.catchtable.domain.*;
import com.catchmind.catchtable.dto.*;
import com.catchmind.catchtable.dto.network.request.CommentHeartRequest;
import com.catchmind.catchtable.dto.network.request.FollowRequest;
import com.catchmind.catchtable.dto.network.request.ReviewHeartRequest;
import com.catchmind.catchtable.dto.network.response.ReviewResponse;
import com.catchmind.catchtable.dto.network.response.TimeLineResponse;
import com.catchmind.catchtable.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TimeLineService {
    private final ProfileRepository profileRepository;
    private final SnsRepository snsRepository;
    private final ReviewRepository reviewRepository;
    private final ReviewHeartRepository reviewHeartRepository;
    private final CommentHeartRepository commentHeartRepository;
    private final FollowRepository followRepository;
    private final CommentRepository commentRepository;
    private final MyCollectionRepository collectionRepository;
    private final ReviewPhotoRepository reviewPhotoRepository;
    private final ReserveRepository reserveRepository;
    private final BistroSaveRepository bistroSaveRepository;
    private final PhotoRepository photoRepository;

    // 타임라인 헤더
    @Transactional
    public TimeLineResponse getHeader(Long prIdx) {
        ProfileDto profileDto = profileRepository.findById(prIdx).map(ProfileDto::from).orElseThrow();
        List<SnsDto> snsList = snsRepository.findAllByProfile_PrIdx(prIdx).stream().map(SnsDto::from).toList();
        System.out.println("❌" + snsList);
        List<ReviewDto> reviewDtos = reviewRepository.findAllByProfile_PrIdx(prIdx, Sort.by(Sort.Direction.DESC, "revIdx")).stream().map(ReviewDto::from).toList();
        Long followingNum = followRepository.countByFollower_PrIdx(prIdx);      // 로그인한 유저의 팔로잉 수
        Long followerNum = followRepository.countByFollowing_PrIdx(prIdx);      // 로그인한 유저의 팔로워 수

        double totalScore = 0;
        for (int i = 0; i < reviewDtos.size(); i++) {
            double score = reviewDtos.get(i).revScore();
            totalScore += score;
        }

        String prName = profileDto.prName();
        String prNick = profileDto.prNick();
        String prRegion = profileDto.prRegion();
        String prIntro = profileDto.prIntro();
        double avgScore = 0;
        if (totalScore == 0) {
            avgScore = 0;
        } else {
            avgScore = totalScore / reviewDtos.size();
        }

        TimeLineResponse timeLineResponse = new TimeLineResponse(prName, prNick, prRegion, prIntro, followingNum, followerNum, snsList, Math.round(avgScore));

        return timeLineResponse;
    }


    // 전체 리뷰 페이지 (로그인 O)
    public Page<ReviewResponse> getReviews(Pageable pageable, Long prIdx) {
        List<ReviewDto> reviewDtos = reviewRepository.findAll(Sort.by(Sort.Direction.DESC, "revIdx"))
                .stream().map(ReviewDto::from).toList();
        List<ReviewDto> loginReview = reviewRepository.findAllByProfile_PrIdx(prIdx, Sort.by(Sort.Direction.DESC,"revIdx")).stream().map(ReviewDto::from).toList();
        List<ReviewResponse> reviewList = new ArrayList<>();
        List<ReviewPhotoDto> photoDtos = reviewPhotoRepository.findAll().stream().map(ReviewPhotoDto::from).toList();

        // 리뷰 별 사진 리스트
        for (int i = 0; i < reviewDtos.size(); i++) {
            List<ReviewPhotoDto> photoList = new ArrayList<>();
            for (int j = 0; j < photoDtos.size(); j++) {
                if (reviewDtos.get(i).revIdx() == photoDtos.get(j).reviewDto().revIdx()) {
                    String orgNm = photoDtos.get(j).orgNm();
                    String savedNm = photoDtos.get(j).savedNm();
                    String savedPath = photoDtos.get(j).savedPath();
                    ReviewDto reviewDto = photoDtos.get(j).reviewDto();
                    ReviewPhotoDto real = ReviewPhotoDto.of(orgNm, savedNm, savedPath, reviewDto);
                    photoList.add(real);
                }
            }
            boolean isReview = false;
            for (ReviewDto login : loginReview) {
                if(reviewDtos.get(i).profileDto().prIdx() == login.profileDto().prIdx()) {
                    isReview = true;
                }
            }
            if (photoList.isEmpty() || reviewDtos.get(i).updateDate() == null) {
                ReviewResponse response = new ReviewResponse(reviewDtos.get(i).revIdx(), reviewDtos.get(i).profileDto(), reviewDtos.get(i).revContent(), reviewDtos.get(i).revScore(),
                        reviewDtos.get(i).resAdminDto(), null, reviewDtos.get(i).reserveDto().resIdx(),
                        reviewDtos.get(i).regDate(), null, isReview);
                reviewList.add(response);
            } else {
                ReviewResponse response = new ReviewResponse(reviewDtos.get(i).revIdx(), reviewDtos.get(i).profileDto(), reviewDtos.get(i).revContent(), reviewDtos.get(i).revScore(),
                        reviewDtos.get(i).resAdminDto(), photoList, reviewDtos.get(i).reserveDto().resIdx(),
                        reviewDtos.get(i).regDate()
                        , reviewDtos.get(i).updateDate(), isReview);
                reviewList.add(response);
            }
        }
        System.out.println("검증 후 리뷰 리스트 : " + reviewList);
        final int start = (int) pageable.getOffset();
        final int end = Math.min((start + pageable.getPageSize()), reviewList.size());
        PageImpl<ReviewResponse> reviewResponsePage = new PageImpl<>(reviewList.subList(start, end), pageable, reviewList.size());
        return reviewResponsePage;
    }
    // 전체 리뷰 페이지 로그인(X)
    public Page<ReviewResponse> getReviews(Pageable pageable) {
        List<ReviewDto> reviewDtos = reviewRepository.findAll(Sort.by(Sort.Direction.DESC, "revIdx"))
                .stream().map(ReviewDto::from).toList();
        List<ReviewResponse> reviewList = new ArrayList<>();
        List<ReviewPhotoDto> photoDtos = reviewPhotoRepository.findAll().stream().map(ReviewPhotoDto::from).toList();

        // 리뷰 별 사진 리스트
        for (int i = 0; i < reviewDtos.size(); i++) {
            List<ReviewPhotoDto> photoList = new ArrayList<>();
            for (int j = 0; j < photoDtos.size(); j++) {
                if (reviewDtos.get(i).revIdx() == photoDtos.get(j).reviewDto().revIdx()) {
                    String orgNm = photoDtos.get(j).orgNm();
                    String savedNm = photoDtos.get(j).savedNm();
                    String savedPath = photoDtos.get(j).savedPath();
                    ReviewDto reviewDto = photoDtos.get(j).reviewDto();
                    ReviewPhotoDto real = ReviewPhotoDto.of(orgNm, savedNm, savedPath, reviewDto);
                    photoList.add(real);
                }
            }
            if (photoList.isEmpty() || reviewDtos.get(i).updateDate() == null) {
                ReviewResponse response = new ReviewResponse(reviewDtos.get(i).revIdx(), reviewDtos.get(i).profileDto(), reviewDtos.get(i).revContent(), reviewDtos.get(i).revScore(),
                        reviewDtos.get(i).resAdminDto(), null, reviewDtos.get(i).reserveDto().resIdx(),
                        reviewDtos.get(i).regDate(), null, false);
                reviewList.add(response);
            } else {
                ReviewResponse response = new ReviewResponse(reviewDtos.get(i).revIdx(), reviewDtos.get(i).profileDto(), reviewDtos.get(i).revContent(), reviewDtos.get(i).revScore(),
                        reviewDtos.get(i).resAdminDto(), photoList, reviewDtos.get(i).reserveDto().resIdx(),
                        reviewDtos.get(i).regDate()
                        , reviewDtos.get(i).updateDate(), false);
                reviewList.add(response);
            }
        }
        System.out.println("검증 후 리뷰 리스트 : " + reviewList);
        final int start = (int) pageable.getOffset();
        final int end = Math.min((start + pageable.getPageSize()), reviewList.size());
        PageImpl<ReviewResponse> reviewResponsePage = new PageImpl<>(reviewList.subList(start, end), pageable, reviewList.size());
        return reviewResponsePage;
    }


    // 개인리뷰 조회
    public Page<ReviewResponse> getReview(Long prIdx, Long timelineIdx, Pageable pageable) {
        List<ReviewResponse> reviewList = new ArrayList<>();
        List<ReviewDto> reviewDtos = reviewRepository.findAllByProfile_PrIdx(timelineIdx, Sort.by(Sort.Direction.DESC, "revIdx")).stream().map(ReviewDto::from).toList();
        List<ReviewDto> loginReview = reviewRepository.findAllByProfile_PrIdx(prIdx, Sort.by(Sort.Direction.DESC, "revIdx")).stream().map(ReviewDto::from).toList();
        List<ReviewPhotoDto> photoDtos = reviewPhotoRepository.findAll().stream().map(ReviewPhotoDto::from).toList();

        for (int i = 0; i < reviewDtos.size(); i++) {
            List<ReviewPhotoDto> photoList = new ArrayList<>();
            for (int j = 0; j < photoDtos.size(); j++) {
                if (reviewDtos.get(i).revIdx() == photoDtos.get(j).reviewDto().revIdx()) {
                    String orgNm = photoDtos.get(j).orgNm();
                    String savedNm = photoDtos.get(j).savedNm();
                    String savedPath = photoDtos.get(j).savedPath();
                    ReviewDto reviewDto = photoDtos.get(j).reviewDto();
                    ReviewPhotoDto real = ReviewPhotoDto.of(orgNm, savedNm, savedPath, reviewDto);
                    photoList.add(real);
                }
            }
            boolean isReview = false;
            for (ReviewDto login : loginReview) {
                if(reviewDtos.get(i).profileDto().prIdx() == login.profileDto().prIdx()) {
                    isReview = true;
                }
            }

            if (photoList.isEmpty() || reviewDtos.get(i).updateDate() == null) {
                ReviewResponse response = new ReviewResponse(reviewDtos.get(i).revIdx(), reviewDtos.get(i).profileDto(), reviewDtos.get(i).revContent(), reviewDtos.get(i).revScore(),
                        reviewDtos.get(i).resAdminDto(), null, reviewDtos.get(i).reserveDto().resIdx(),
                        reviewDtos.get(i).regDate(), null, isReview);
                reviewList.add(response);
            } else {
                ReviewResponse response = new ReviewResponse(reviewDtos.get(i).revIdx(), reviewDtos.get(i).profileDto(), reviewDtos.get(i).revContent(), reviewDtos.get(i).revScore(),
                        reviewDtos.get(i).resAdminDto(), photoList, reviewDtos.get(i).reserveDto().resIdx(),
                        reviewDtos.get(i).regDate()
                        , reviewDtos.get(i).updateDate(), isReview);
                reviewList.add(response);
            }
            System.out.println("i" + i + reviewList.get(i).photo());
        }

        reviewList.sort((o1, o2) -> o2.revIdx().compareTo(o1.revIdx()));
        final int start = (int) pageable.getOffset();
        final int end = Math.min((start + pageable.getPageSize()), reviewList.size());
        PageImpl<ReviewResponse> reviewResponsePage = new PageImpl<>(reviewList.subList(start, end), pageable, reviewList.size());
        return reviewResponsePage;
    }

    // 전체 리뷰 조회
    public List<ReviewDto> reviews() {
        return reviewRepository.findAll().stream().map(ReviewDto::from).toList();
    }

    // 리뷰별 댓글 리스트
    public List<CommentDto> getComments() {
        List<CommentDto> commentDtos = commentRepository.findAll().stream().map(CommentDto::from).toList();
        return commentDtos;
    }

    public CommentDto getComment(Long comIdx) {
        CommentDto comment = commentRepository.findById(comIdx).map(CommentDto::from).orElseThrow();
        return comment;
    }


    // 컬렉션 리스트
    public List<MyCollectionDto> getCollection(Long timeLineIdx) {
        List<MyCollectionDto> collectionDtos = collectionRepository.findAllByProfile_PrIdxAndColLock(timeLineIdx, true).stream().map(MyCollectionDto::from).toList();
        return collectionDtos;
    }

    // 컬렉션 상세의 식당리스트
    public List<BistroSaveDto> getCollectionDetail(Long colIdx) {
        List<BistroSaveDto> bistroSaveDtos = bistroSaveRepository.findAllByColIdx(colIdx).stream().map(BistroSaveDto::from).toList();
        return bistroSaveDtos;
    }

    // 컬렉션 상세
    public MyCollectionDto collection(Long colIdx) {
        MyCollectionDto collectionDto = collectionRepository.findById(colIdx).map(MyCollectionDto::from).orElseThrow();
        return collectionDto;
    }

    public PhotoDto bistroPhoto(String resaBisName) {
        System.out.println(resaBisName);
        PhotoDto bisPhoto = photoRepository.findByResAdmin_ResaBisName(resaBisName).map(PhotoDto::from).orElseThrow();
        return bisPhoto;
    }

    // 팔로잉 리스트
    public List<FollowDto> getFollowingList(Long prIdx) {
        List<FollowDto> followingList = followRepository.findByFollowing_prIdx(prIdx)
                .stream().map(FollowDto::from).toList();
        return followingList;
    }

    // 팔로워 리스트
    public List<FollowDto> getFollowerList(Long prIdx) {
        List<FollowDto> followerList = followRepository.findByFollower_prIdx(prIdx)
                .stream().map(FollowDto::from).toList();
        return followerList;
    }

    // 로그인한 회원의 리뷰 좋아요리스트
    public List<ReviewHeartDto> getReviewHeart(Long prIdx) {
        List<ReviewHeartDto> heartDtos = reviewHeartRepository.findAllByProfile_prIdx(prIdx)
                .stream().map(ReviewHeartDto::from).toList();

        return heartDtos;
    }

    //로그인한 회원의 댓글 좋아요 리스트
    public List<CommentHeartDto> getComHeart(Long prIdx) {
        List<CommentHeartDto> commentHeartDtos = commentHeartRepository.findAllByProfile_PrIdx(prIdx).stream().map(CommentHeartDto::from).toList();
        return commentHeartDtos;
    }

    // 팔로우 기능
    public Follow follow(FollowRequest request) {
        Long followerIdx = request.follower();       // 팔로우 하는 회원의 idx
        Long followingIdx = request.following();     // 팔로우 당하는 회원의 idx
        System.out.println(request.toDto().toEntity());
        return followRepository.save(request.toDto().toEntity());
    }

    // 언팔로우 기능
    @Transactional
    public Optional<Follow> unfollow(FollowRequest request) {
        Long followerIdx = request.follower();       // 팔로우 하는 회원의 idx
        Long followingIdx = request.following();     // 팔로우 당하는 회원의 idx
        return followRepository.deleteByFollower_prIdxAndFollowing_prIdx(followerIdx, followingIdx);
    }

    // 좋아요 기능
    public Long newHeart(ReviewHeartRequest request) {
        Long revIdx = request.revIdx();
        Long revLike = request.revLike();
        Review findReview = reviewRepository.findById(revIdx).orElse(null);
        findReview.setRevLike(revLike + 1);
        Long newLike = reviewRepository.save(findReview).getRevLike();
        System.out.println("❤️" + newLike);
        reviewHeartRepository.save(request.toDto().toEntity());
        return newLike;
    }

    // 좋아요 삭제
    @Transactional
    public Long delHeart(ReviewHeartRequest request) {
        Long revIdx = request.revIdx();
        Long prIdx = request.prIdx();
        Long revLike = request.revLike();
        Review findReview = reviewRepository.findById(revIdx).orElse(null);
        if (revLike == 0) {
            findReview.setRevLike(revLike);
        } else {
            findReview.setRevLike(revLike - 1);
        }
        Long newLike = reviewRepository.save(findReview).getRevLike();

        System.out.println("💙" + newLike);
        reviewHeartRepository.deleteByProfile_PrIdxAndReview_RevIdx(prIdx, revIdx);
        return newLike;
    }

    // 댓글 등록
    public Long newComment(CommentHeartRequest request) {
        Long revIdx = request.revIdx();
        Long revCom = reviewRepository.findById(revIdx).get().getRevComm();
        Review findReview = reviewRepository.findById(revIdx).orElse(null);
        // 리뷰 댓글 수 업데이트
        findReview.setRevComm(revCom + 1);
        reviewRepository.save(findReview);
        // 댓글 저장
        System.out.println(request.toDto());
        System.out.println(request.toDto().toEntity());
        Long saveCom = commentRepository.save(request.toDto().toEntity()).getComIdx();
        return saveCom;
    }

    // 댓글 삭제
    @Transactional
    public Long delComment(Long comIdx, Long revIdx) {
        Review findReview = reviewRepository.findById(revIdx).orElse(null);
        findReview.setRevComm(findReview.getRevComm() - 1);

        Long com = reviewRepository.save(findReview).getRevComm();
        System.out.println("📒" + com);
        commentRepository.deleteById(comIdx);
        return com;
    }

    // 댓글 좋아요
    public Long newComHeart(CommentHeartRequest request) {
        Long comIdx = request.comIdx();
        Long comLike = request.comLike();
        Comment findComment = commentRepository.findById(comIdx).orElse(null);
        findComment.setComLike(comLike + 1);
        Long newLike = commentRepository.save(findComment).getComLike();
        System.out.println("❤️" + newLike);
        CommentHeart newHeart = new CommentHeart(Profile.ofIdx(request.prIdx()), Review.ofIdx(request.revIdx()), Comment.ofIdx(request.comIdx()));
        commentHeartRepository.save(newHeart);
        return newLike;
    }

    // 댓글 좋아요 삭제
    @Transactional
    public Long delComHeart(CommentHeartRequest request) {
        Long comIdx = request.comIdx();
        Long prIdx = request.prIdx();
        Long comLike = request.comLike();

        Long findLike = commentRepository.findById(comIdx).get().getComLike();
        Comment findComment = commentRepository.findById(comIdx).orElseThrow();
        System.out.println("디비에서 찾은 좋아요 개수 :" + findLike);
        findComment.setComLike(findLike - 1);

        Long newLike = commentRepository.save(findComment).getComLike();
        System.out.println("삭제 후 좋아요 수 💙" + newLike);
        commentHeartRepository.deleteByProfile_PrIdxAndComment_comIdx(prIdx, comIdx);
        return newLike;
    }

    @Transactional
    public void delReview(Long revIdx, Long resIdx, Long prIdx) {
        Profile findProfile = profileRepository.findById(prIdx).orElse(null);
        if(findProfile.getPrReview() != 0) {
            findProfile.setPrReview(findProfile.getPrReview() - 1);
        } else {
            findProfile.setPrReview(0);
        }

        Reserve findReserve = reserveRepository.findById(resIdx).orElse(null);
        findReserve.setRevStatus(false);

        try {
            reviewRepository.deleteById(revIdx);
        } catch(EmptyResultDataAccessException e) {
            e.printStackTrace();
        }
    }

}
