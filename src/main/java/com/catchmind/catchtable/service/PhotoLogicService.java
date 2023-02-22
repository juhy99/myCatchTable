package com.catchmind.catchtable.service;

import com.catchmind.catchtable.dto.PhotoDto;
import com.catchmind.catchtable.dto.ReviewDto;
import com.catchmind.catchtable.dto.ReviewPhotoDto;
import com.catchmind.catchtable.dto.network.response.ReviewResponse;
import com.catchmind.catchtable.repository.PhotoRepository;
import com.catchmind.catchtable.repository.ReviewPhotoRepository;
import com.catchmind.catchtable.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PhotoLogicService {
    private final PhotoRepository photoRepository;
    private final ReviewRepository reviewRepository;
    private final ReviewPhotoRepository reviewPhotoRepository;

public PhotoDto photoDto(String resaBisName){
    return photoRepository.findByResAdmin_ResaBisName(resaBisName).map(PhotoDto::from).orElseThrow();
}


    // 전체 리뷰 페이지
    public Page<ReviewResponse> getReviews(Pageable pageable) {
        List<ReviewResponse> reviewList = new ArrayList<>();
        List<ReviewDto> reviewDtos = reviewRepository.findAll(Sort.by(Sort.Direction.DESC, "bisIdx"))
                .stream().map(ReviewDto::from).toList();
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
                        reviewDtos.get(i).regDate(), null, true);
                reviewList.add(response);
            } else {
                ReviewResponse response = new ReviewResponse(reviewDtos.get(i).revIdx(), reviewDtos.get(i).profileDto(), reviewDtos.get(i).revContent(), reviewDtos.get(i).revScore(),
                        reviewDtos.get(i).resAdminDto(), photoList, reviewDtos.get(i).reserveDto().resIdx(),
                        reviewDtos.get(i).regDate(), reviewDtos.get(i).updateDate(), true);
                reviewList.add(response);
            }
        }

        final int start = (int) pageable.getOffset();
        final int end = Math.min((start + pageable.getPageSize()), reviewList.size());
        PageImpl<ReviewResponse> reviewResponsePage = new PageImpl<>(reviewList.subList(start, end), pageable, reviewList.size());
        return reviewResponsePage;
    }
}


