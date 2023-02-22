package com.catchmind.catchtable.service;


import com.catchmind.catchtable.dto.ReviewDto;
import com.catchmind.catchtable.dto.ReviewPhotoDto;
import com.catchmind.catchtable.dto.network.response.IndexResponse;
import com.catchmind.catchtable.dto.network.response.ShopResponse;
import com.catchmind.catchtable.repository.ReviewPhotoRepository;
import com.catchmind.catchtable.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewLogicService {
    private final ReviewRepository reviewRepository;
    private final ReviewPhotoRepository reviewPhotoRepository;

    public List<ReviewDto> reviewList(String resaBisName) {
        return reviewRepository.findAllByResAdmin_ResaBisName(resaBisName, Sort.by(Sort.Direction.DESC, "revIdx")).stream().map(ReviewDto::from).toList();
    }

    public List<ShopResponse> reviewPhotoList(String resaBisName) {
        List<ReviewDto> reviewDtos = reviewRepository.findAllByResAdmin_ResaBisName(resaBisName, Sort.by(Sort.Direction.DESC, "revIdx")).stream().map(ReviewDto::from).toList();
        List<ReviewPhotoDto> photoDtos = new ArrayList<>();
        List<ShopResponse> shopResponseList = new ArrayList<>();
        String prNick = null;
        LocalDateTime regDate = null;
        double revScore = 0.0;
        String revContent = null;

        for (int i = 0; i < reviewDtos.size(); i++) {
            photoDtos = reviewPhotoRepository.findAllByReview_RevIdx(reviewDtos.get(i).revIdx()).stream().map(ReviewPhotoDto::from).toList();

            ReviewPhotoDto real = null;
            if (!photoDtos.isEmpty()) {
                for (int j = 0; j < photoDtos.size(); j++) {
                    String orgNm = photoDtos.get(0).orgNm();
                    String savedNm = photoDtos.get(0).savedNm();
                    String savedPath = photoDtos.get(0).savedPath();
                    ReviewDto reviewDto = photoDtos.get(0).reviewDto();
                    real = ReviewPhotoDto.of(orgNm, savedNm, savedPath, reviewDto);
                }
            }
            prNick = reviewDtos.get(i).profileDto().prNick();
            regDate = reviewDtos.get(i).regDate();
            revScore = reviewDtos.get(i).revScore();
            revContent = reviewDtos.get(i).revContent();
            ShopResponse shopResponse = new ShopResponse(prNick, regDate, revScore, revContent, real);
            shopResponseList.add(shopResponse);
        }
        return shopResponseList;
    }


    public List<IndexResponse> indexReviewList() {
        List<ReviewDto> reviewDtos = reviewRepository.findTop6By().stream().map(ReviewDto::from).toList();
        List<ReviewPhotoDto> photoDtos = new ArrayList<>();
        List<IndexResponse> reviewList = new ArrayList<>();


        // 리뷰 별 사진 리스트
        for (int i = 0; i < reviewDtos.size(); i++) {
            photoDtos = reviewPhotoRepository.findAllByReview_RevIdx(reviewDtos.get(i).revIdx()).stream().map(ReviewPhotoDto::from).toList();

            ReviewPhotoDto real = null;
            if (!photoDtos.isEmpty()) {
                for (int j = 0; j < photoDtos.size(); j++) {
                    String orgNm = photoDtos.get(0).orgNm();
                    String savedNm = photoDtos.get(0).savedNm();
                    String savedPath = photoDtos.get(0).savedPath();
                    ReviewDto reviewDto = photoDtos.get(0).reviewDto();
                    real = ReviewPhotoDto.of(orgNm, savedNm, savedPath, reviewDto);
                }
            }

            IndexResponse response = new IndexResponse(reviewDtos.get(i).revIdx(), reviewDtos.get(i).profileDto(), reviewDtos.get(i).revContent(), reviewDtos.get(i).revScore(),
                    reviewDtos.get(i).resAdminDto(), real, reviewDtos.get(i).reserveDto());
            reviewList.add(response);

        }
        System.out.println("검증 후 리뷰 리스트 : " + reviewList);
        return reviewList;
    }
}
