package com.catchmind.catchtable.service;

import com.catchmind.catchtable.domain.BistroInfo;
import com.catchmind.catchtable.domain.BistroSave;
import com.catchmind.catchtable.dto.*;
import com.catchmind.catchtable.dto.network.request.BistroSaveRequest;
import com.catchmind.catchtable.dto.network.response.ReviewAndPhotoResponse;
import com.catchmind.catchtable.dto.network.response.ReviewResponse;
import com.catchmind.catchtable.dto.network.response.ShopDetailResponse;
import com.catchmind.catchtable.dto.network.response.ShopListResponse;
import com.catchmind.catchtable.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ShopService {
    private final ReviewRepository reviewRepository;
    private final ReviewPhotoRepository reviewPhotoRepository;
    private final BistroInfoRepository bistroInfoRepository;
    private final BistroDetailRepository bistroDetailRepository;
    private final BistroSaveRepository bistroSaveRepository;
    private final MenuRepository menuRepository;
    private final PhotoRepository photoRepository;
    private final BisNoticeRepository bisNoticeRepository;
    private final FacilityRepository facilityRepository;

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
                        break;
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

    // 식당 카테고리 리스트
    public Page<ShopListResponse> categoryList(Pageable pageable, Long prIdx, String sort, String bisCategory, String bisRegion) {
        List<ShopListResponse> shopListResponses = new ArrayList<>();
        List<BistroDetailDto> bistroDetailDtos = new ArrayList<>();
        if (bisCategory != null) {
            bistroDetailDtos = bistroDetailRepository.findAllByBistroInfo_BisCategoryContaining(bisCategory).stream().map(BistroDetailDto::from).toList();
        }
        if (bisRegion != null) {
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
                        break;
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

    // 식당 검색
    public Page<BistroInfo> searchList(String resaBisName, Pageable pageable) {
        return bistroInfoRepository.findByResAdmin_ResaBisNameContaining(resaBisName, pageable);
    }


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
                        break;
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

    // 컨트롤러 프로그래스 계산
    public List<ReviewDto> reviewList(String resaBisName) {
        return reviewRepository.findAllByResAdmin_ResaBisName(resaBisName, Sort.by(Sort.Direction.DESC, "revIdx")).stream().map(ReviewDto::from).toList();
    }

    // 식당 메뉴 리스트
    public List<MenuDto> menuList(String resaBisName) {
        return menuRepository.findAllByResAdmin_ResaBisName(resaBisName).stream().map(MenuDto::from).toList();
    }


    // 식당 상세 페이지
    public ShopDetailResponse getDetail(String resaBisName) {
        Double avg = 0.0;
        Long revCnt = 0L;
        BistroDetailDto bistroDetailDto = bistroDetailRepository.findByResAdmin_ResaBisName(resaBisName).map(BistroDetailDto::from).orElseThrow();
        PhotoDto photoDto = photoRepository.findByResAdmin_ResaBisName(resaBisName).map(PhotoDto::from).orElseThrow();
        List<BisNoticeDto> bisNoticeDtos = bisNoticeRepository.findAllByResAdmin_ResaBisName(resaBisName).stream().map(BisNoticeDto::from).toList();
        List<FacilityDto> facilityDtos = facilityRepository.findAllByResAdmin_ResaBisName(resaBisName).stream().map(FacilityDto::from).toList();
        List<MenuDto> menuDtos = menuRepository.findAllByResAdmin_ResaBisName(resaBisName).stream().map(MenuDto::from).toList();
        List<ReviewDto> reviewDtos = reviewRepository.findAllByResAdmin_ResaBisName(resaBisName, Sort.by(Sort.Direction.DESC, "revIdx")).stream().map(ReviewDto::from).toList();

        List<ReviewPhotoDto> photoDtos = new ArrayList<>();
        List<ReviewAndPhotoResponse> reviewAndPhotoResponseList = new ArrayList<>();
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
            ReviewAndPhotoResponse reviewAndPhotoResponse = new ReviewAndPhotoResponse(prNick, regDate, revScore, revContent, real);
            reviewAndPhotoResponseList.add(reviewAndPhotoResponse);
        }
        Double sum = 0.0;
        // 리뷰 점수
        for (ReviewDto review : reviewDtos) {
            sum += review.revScore();
            revCnt += 1;
        }
        avg = sum / revCnt;
        String revAvg = "";
        if (avg.isNaN()) {
            revAvg = "0";
        } else {
            revAvg = String.format("%.1f", avg);
        }
        ShopDetailResponse shopDetailResponse = new ShopDetailResponse(bistroDetailDto, photoDto, bisNoticeDtos, facilityDtos, menuDtos, revAvg, revCnt, reviewAndPhotoResponseList);
        return shopDetailResponse;

    }

    // 북마크 저장 create
    public BistroSave newBookmark(BistroSaveRequest request) {
        BistroDetailDto bistroDetailDto = bistroDetailRepository.findById(request.bdIdx()).map(BistroDetailDto::from).orElseThrow();
        String resaBisName = bistroDetailDto.resAdminDto().resaBisName();
        BistroSaveRequest saveRequest = new BistroSaveRequest(request.saveIdx(), resaBisName, request.prIdx(), request.bdIdx(), null);
        return bistroSaveRepository.save(saveRequest.toDto().toEntity());
    }

    // 북마크 삭제
    @Transactional
    public Optional<BistroSave> delBookmark(BistroSaveRequest request) {
        System.out.println("서비스 진입");
        return bistroSaveRepository.deleteByProfile_PrIdxAndBistroDetail_BdIdx(request.prIdx(), request.bdIdx());
    }

}
