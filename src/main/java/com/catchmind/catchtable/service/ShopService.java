package com.catchmind.catchtable.service;

import com.catchmind.catchtable.dto.BistroDetailDto;
import com.catchmind.catchtable.dto.BistroSaveDto;
import com.catchmind.catchtable.dto.ReviewDto;
import com.catchmind.catchtable.dto.ReviewPhotoDto;
import com.catchmind.catchtable.dto.network.response.ReviewResponse;
import com.catchmind.catchtable.dto.network.response.ShopListResponse;
import com.catchmind.catchtable.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ShopService {
    private final ReviewRepository reviewRepository;
    private final BistroInfoRepository bistroInfoRepository;
    private final PhotoRepository photoRepository;
    private final ReviewPhotoRepository reviewPhotoRepository;
    private final BistroSaveRepository bistroSaveRepository;
    private final BistroDetailRepository bistroDetailRepository;

    // 식당 별 리뷰 리스트
    public Page<ReviewResponse> getBisNameReviews(Pageable pageable, String resaBisName, Long prIdx, String sort) {
        List<ReviewDto> bisNameReview = reviewRepository.findAllByResAdmin_ResaBisName(resaBisName, Sort.by(Sort.Direction.DESC, "revIdx")).stream().map(ReviewDto::from).toList();
        List<ReviewDto> loginReview = reviewRepository.findAllByProfile_PrIdx(prIdx, Sort.by(Sort.Direction.DESC, "revIdx")).stream().map(ReviewDto::from).toList();
        List<ReviewResponse> reviewList = new ArrayList<>();
        List<ReviewPhotoDto> photoDtos = reviewPhotoRepository.findAll().stream().map(ReviewPhotoDto::from).toList();

        if (prIdx == null) {
            // 리뷰 별 사진 리스트
            for (int i = 0; i < bisNameReview.size(); i++) {
                List<ReviewPhotoDto> photoList = new ArrayList<>();
                for (int j = 0; j < photoDtos.size(); j++) {
                    if (bisNameReview.get(i).revIdx() == photoDtos.get(j).reviewDto().revIdx()) {
                        String orgNm = photoDtos.get(j).orgNm();
                        String savedNm = photoDtos.get(j).savedNm();
                        String savedPath = photoDtos.get(j).savedPath();
                        ReviewDto reviewDto = photoDtos.get(j).reviewDto();
                        ReviewPhotoDto real = ReviewPhotoDto.of(orgNm, savedNm, savedPath, reviewDto);
                        photoList.add(real);
                    }
                }
                boolean isReview = false;
                if (photoList.isEmpty() || bisNameReview.get(i).updateDate() == null) {
                    ReviewResponse response = new ReviewResponse(bisNameReview.get(i).revIdx(), bisNameReview.get(i).profileDto(), bisNameReview.get(i).revContent(), bisNameReview.get(i).revScore(),
                            bisNameReview.get(i).resAdminDto(), null, bisNameReview.get(i).reserveDto().resIdx(),
                            bisNameReview.get(i).regDate(), null, isReview);
                    reviewList.add(response);
                } else {
                    ReviewResponse response = new ReviewResponse(bisNameReview.get(i).revIdx(), bisNameReview.get(i).profileDto(), bisNameReview.get(i).revContent(), bisNameReview.get(i).revScore(),
                            bisNameReview.get(i).resAdminDto(), photoList, bisNameReview.get(i).reserveDto().resIdx(),
                            bisNameReview.get(i).regDate()
                            , bisNameReview.get(i).updateDate(), isReview);
                    reviewList.add(response);
                }
            }
        } else {
            // 리뷰 별 사진 리스트
            for (int i = 0; i < bisNameReview.size(); i++) {
                List<ReviewPhotoDto> photoList = new ArrayList<>();
                for (int j = 0; j < photoDtos.size(); j++) {
                    if (bisNameReview.get(i).revIdx() == photoDtos.get(j).reviewDto().revIdx()) {
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
                    if (bisNameReview.get(i).profileDto().prIdx() == login.profileDto().prIdx()) {
                        isReview = true;
                    }
                }
                if (photoList.isEmpty() || bisNameReview.get(i).updateDate() == null) {
                    ReviewResponse response = new ReviewResponse(bisNameReview.get(i).revIdx(), bisNameReview.get(i).profileDto(), bisNameReview.get(i).revContent(), bisNameReview.get(i).revScore(),
                            bisNameReview.get(i).resAdminDto(), null, bisNameReview.get(i).reserveDto().resIdx(),
                            bisNameReview.get(i).regDate(), null, isReview);
                    reviewList.add(response);
                } else {
                    ReviewResponse response = new ReviewResponse(bisNameReview.get(i).revIdx(), bisNameReview.get(i).profileDto(), bisNameReview.get(i).revContent(), bisNameReview.get(i).revScore(),
                            bisNameReview.get(i).resAdminDto(), photoList, bisNameReview.get(i).reserveDto().resIdx(),
                            bisNameReview.get(i).regDate()
                            , bisNameReview.get(i).updateDate(), isReview);
                    reviewList.add(response);
                }
            }
        }
        if (sort != null) {
            switch (sort) {
                case "regDate":
                    reviewList.sort((o1, o2) -> o2.regDate().compareTo(o1.regDate()));
                    break;
                case "highScore":      // 높은순
                    reviewList.sort((o1, o2) -> o2.revScore().compareTo(o1.revScore()));
                    break;
                case "lowScore":        // 낮은순
                    reviewList.sort((o1, o2) -> o1.revScore().compareTo(o2.revScore()));
            }
        }

        System.out.println("검증 후 리뷰 리스트 : " + reviewList);
        final int start = (int) pageable.getOffset();
        final int end = Math.min((start + pageable.getPageSize()), reviewList.size());
        PageImpl<ReviewResponse> reviewResponsePage = new PageImpl<>(reviewList.subList(start, end), pageable, reviewList.size());
        return reviewResponsePage;
    }

    // 식당 전체리스트
    public Page<ShopListResponse> shopList(Pageable pageable, Long prIdx, String sort) {
        List<ShopListResponse> shopListResponses = new ArrayList<>();
        List<BistroDetailDto> bistroDetailDtos = bistroDetailRepository.findAll().stream().map(BistroDetailDto::from).toList();
        if (prIdx == null) {
            List<ReviewDto> reviewDtos = new ArrayList<>();

            for (BistroDetailDto bistroDetailDto : bistroDetailDtos) {
                reviewDtos = reviewRepository.findAllByResAdmin_ResaBisName(bistroDetailDto.resAdminDto().resaBisName(), Sort.by(Sort.Direction.DESC, "revIdx")).stream().map(ReviewDto::from).toList();
                Long reviewCnt = reviewRepository.countByResAdmin_ResaBisName(bistroDetailDto.resAdminDto().resaBisName());
                if (reviewDtos.isEmpty()) {
                    ShopListResponse response = new ShopListResponse("0.0", reviewCnt, bistroDetailDto.resAdminDto().resaBisName(), bistroDetailDto,
                            bistroDetailDto.bistroInfoDto().photoDto(), false,
                            bistroDetailDto.bistroInfoDto().regDate());
                    shopListResponses.add(response);
                } else {
                    double totalScore = 0;
                    double avg = 0;
                    for (ReviewDto reviewDto : reviewDtos) {
                        System.out.println(reviewDto.revScore());
                        totalScore += reviewDto.revScore();
                    }
                    avg = totalScore / reviewDtos.size();
                    System.out.println(totalScore);
                    System.out.println("❤️" + avg);
                    ShopListResponse response = new ShopListResponse(String.format("%.1f", avg), reviewCnt, bistroDetailDto.resAdminDto().resaBisName(), bistroDetailDto,
                            bistroDetailDto.bistroInfoDto().photoDto(), false,
                            bistroDetailDto.bistroInfoDto().regDate());
                    shopListResponses.add(response);
                }
            }
        } else {
            List<BistroSaveDto> bistroSaveDtos = bistroSaveRepository.findAllByProfile_PrIdx(prIdx).stream().map(BistroSaveDto::from).toList();
            List<ReviewDto> reviewDtos = new ArrayList<>();

            for (BistroDetailDto bistroDetailDto : bistroDetailDtos) {
                reviewDtos = reviewRepository.findAllByResAdmin_ResaBisName(bistroDetailDto.resAdminDto().resaBisName(), Sort.by(Sort.Direction.DESC, "revIdx")).stream().map(ReviewDto::from).toList();
                Long reviewCnt = reviewRepository.countByResAdmin_ResaBisName(bistroDetailDto.resAdminDto().resaBisName());
                boolean isSaved = false;
                for (BistroSaveDto bistroSaveDto : bistroSaveDtos) {
                    if (bistroDetailDto.bdIdx() == bistroSaveDto.bistroDetailDto().bdIdx()) {
                        isSaved = true;
                    }
                }
                if (reviewDtos.isEmpty()) {
                    ShopListResponse response = new ShopListResponse("0.0", reviewCnt, bistroDetailDto.resAdminDto().resaBisName(), bistroDetailDto,
                            bistroDetailDto.bistroInfoDto().photoDto(), isSaved,
                            bistroDetailDto.bistroInfoDto().regDate());
                    shopListResponses.add(response);
                } else {
                    double totalScore = 0;
                    double avg = 0;
                    for (ReviewDto reviewDto : reviewDtos) {
                        System.out.println(reviewDto.revScore());
                        totalScore += reviewDto.revScore();
                    }
                    avg = totalScore / reviewCnt;
                    System.out.println("💙" + avg);
                    ShopListResponse response = new ShopListResponse(String.format("%.1f", avg), reviewCnt, bistroDetailDto.resAdminDto().resaBisName(), bistroDetailDto,
                            bistroDetailDto.bistroInfoDto().photoDto(), isSaved,
                            bistroDetailDto.bistroInfoDto().regDate()
                    );
                    shopListResponses.add(response);
                }
            }

        }
        if (sort != null) {
            switch (sort) {
                case "regDate":
                    shopListResponses.sort((o1, o2) -> o2.regDate().compareTo(o1.regDate()));
                    break;
                case "revCnt":
                    shopListResponses.sort((o1, o2) -> o2.revCnt().compareTo(o1.revCnt()));
                    break;
                case "revScore":
                    shopListResponses.sort((o1, o2) -> o2.avgScore().compareTo(o1.avgScore()));
            }

        }
        final int start = (int) pageable.getOffset();
        final int end = Math.min((start + pageable.getPageSize()), shopListResponses.size());
        PageImpl<ShopListResponse> shopListResponsePage = new PageImpl<>(shopListResponses.subList(start, end), pageable, shopListResponses.size());
        return shopListResponsePage;
    }

    // 카테고리
    public Page<ShopListResponse> categoryList(Pageable pageable, Long prIdx, String sort, String bisCategory, String bisRegion) {
        List<ShopListResponse> shopListResponses = new ArrayList<>();
        List<BistroDetailDto> bistroDetailDtos = new ArrayList<>();
        if (bisCategory != null) {
            bistroDetailDtos = bistroDetailRepository.findAllByBistroInfo_BisCategoryContaining(bisCategory).stream().map(BistroDetailDto::from).toList();
        }
        if(bisRegion != null) {
            bistroDetailDtos = bistroDetailRepository.findAllByBistroInfo_BisRegionContaining(bisRegion).stream().map(BistroDetailDto::from).toList();
        }
        if (prIdx == null) {
            List<ReviewDto> reviewDtos = new ArrayList<>();
            for (BistroDetailDto bistroDetailDto : bistroDetailDtos) {
                reviewDtos = reviewRepository.findAllByResAdmin_ResaBisName(bistroDetailDto.resAdminDto().resaBisName(), Sort.by(Sort.Direction.DESC, "revIdx")).stream().map(ReviewDto::from).toList();
                Long reviewCnt = reviewRepository.countByResAdmin_ResaBisName(bistroDetailDto.resAdminDto().resaBisName());
                if (reviewDtos.isEmpty()) {
                    ShopListResponse response = new ShopListResponse("0.0", reviewCnt, bistroDetailDto.resAdminDto().resaBisName(), bistroDetailDto,
                            bistroDetailDto.bistroInfoDto().photoDto(), false,
                            bistroDetailDto.bistroInfoDto().regDate());
                    shopListResponses.add(response);
                } else {
                    double totalScore = 0;
                    double avg = 0;
                    for (ReviewDto reviewDto : reviewDtos) {
                        System.out.println(reviewDto.revScore());
                        totalScore += reviewDto.revScore();
                    }
                    avg = totalScore / reviewDtos.size();
                    System.out.println(totalScore);
                    System.out.println("❤️" + avg);
                    ShopListResponse response = new ShopListResponse(String.format("%.1f", avg), reviewCnt, bistroDetailDto.resAdminDto().resaBisName(), bistroDetailDto,
                            bistroDetailDto.bistroInfoDto().photoDto(), false,
                            bistroDetailDto.bistroInfoDto().regDate());
                    shopListResponses.add(response);
                }
            }
        } else {
            List<BistroSaveDto> bistroSaveDtos = bistroSaveRepository.findAllByProfile_PrIdx(prIdx).stream().map(BistroSaveDto::from).toList();
            List<ReviewDto> reviewDtos = new ArrayList<>();

            for (BistroDetailDto bistroDetailDto : bistroDetailDtos) {
                reviewDtos = reviewRepository.findAllByResAdmin_ResaBisName(bistroDetailDto.resAdminDto().resaBisName(), Sort.by(Sort.Direction.DESC, "revIdx")).stream().map(ReviewDto::from).toList();
                Long reviewCnt = reviewRepository.countByResAdmin_ResaBisName(bistroDetailDto.resAdminDto().resaBisName());
                boolean isSaved = false;
                for (BistroSaveDto bistroSaveDto : bistroSaveDtos) {
                    if (bistroDetailDto.bdIdx() == bistroSaveDto.bistroDetailDto().bdIdx()) {
                        isSaved = true;
                    }
                }
                if (reviewDtos.isEmpty()) {
                    ShopListResponse response = new ShopListResponse("0.0", reviewCnt, bistroDetailDto.resAdminDto().resaBisName(), bistroDetailDto,
                            bistroDetailDto.bistroInfoDto().photoDto(), isSaved,
                            bistroDetailDto.bistroInfoDto().regDate());
                    shopListResponses.add(response);
                } else {
                    double totalScore = 0;
                    double avg = 0;
                    for (ReviewDto reviewDto : reviewDtos) {
                        System.out.println(reviewDto.revScore());
                        totalScore += reviewDto.revScore();
                    }
                    avg = totalScore / reviewCnt;
                    System.out.println("💙" + avg);
                    ShopListResponse response = new ShopListResponse(String.format("%.1f", avg), reviewCnt, bistroDetailDto.resAdminDto().resaBisName(), bistroDetailDto,
                            bistroDetailDto.bistroInfoDto().photoDto(), isSaved,
                            bistroDetailDto.bistroInfoDto().regDate()
                    );
                    shopListResponses.add(response);
                }
            }

        }
        if (sort != null) {
            switch (sort) {
                case "regDate":
                    shopListResponses.sort((o1, o2) -> o2.regDate().compareTo(o1.regDate()));
                    break;
                case "revCnt":
                    shopListResponses.sort((o1, o2) -> o2.revCnt().compareTo(o1.revCnt()));
                    break;
                case "revScore":
                    shopListResponses.sort((o1, o2) -> o2.avgScore().compareTo(o1.avgScore()));
                    break;
            }

        }
        final int start = (int) pageable.getOffset();
        final int end = Math.min((start + pageable.getPageSize()), shopListResponses.size());
        PageImpl<ShopListResponse> shopListResponsePage = new PageImpl<>(shopListResponses.subList(start, end), pageable, shopListResponses.size());
        return shopListResponsePage;
    }



}



