package com.catchmind.catchtable.service;

import com.catchmind.catchtable.domain.Profile;
import com.catchmind.catchtable.domain.Reserve;
import com.catchmind.catchtable.domain.type.ReservationType;
import com.catchmind.catchtable.dto.ReserveDto;
import com.catchmind.catchtable.dto.ReviewDto;
import com.catchmind.catchtable.dto.ReviewPhotoDto;
import com.catchmind.catchtable.dto.network.request.ReviewPhotoRequest;
import com.catchmind.catchtable.dto.network.request.ReviewRequest;
import com.catchmind.catchtable.repository.ProfileRepository;
import com.catchmind.catchtable.repository.ReserveRepository;
import com.catchmind.catchtable.repository.ReviewPhotoRepository;
import com.catchmind.catchtable.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Slf4j  // 로그를 찍기 위해서 사용하는 어노테이션
@Transactional
@RequiredArgsConstructor
@Service
public class MydiningService {
    private final ReserveRepository reserveRepository;
    private final ReviewRepository reviewRepository;
    private final ReviewPhotoRepository reviewPhotoRepository;
    private final ProfileRepository profileRepository;

    @Transactional
    public ReserveDto getDetail(Long resIdx) {
        return reserveRepository.findById(resIdx).map(ReserveDto::from)
                .orElseThrow();
    }

    @Transactional
    public void updateCancel(Long resIdx){
        Optional<Reserve> reserve = reserveRepository.findById(resIdx);
        reserve.ifPresent(
                newRes -> {
                    newRes.setResStatus(ReservationType.CANCEL);
                    reserveRepository.save(newRes);
                }
        );

    }

    public Long saveReview(ReviewRequest reviews) {
        ReviewRequest request = reviews;
        System.out.println(request);
        ReviewDto newReview = request.of(request.prIdx(), request.revContent(), (request.revScore()*0.5), request.resaBisName(), request.resIdx()).toDto();
        // 프로필에 리뷰 갯수 증가
        Optional<Profile> findProfile =  profileRepository.findById(request.prIdx());
        findProfile.ifPresent(
                profile -> {
                    profile.setPrReview(profile.getPrReview() + 1);
                }
        );
        Long saveIdx = reviewRepository.save(newReview.toEntity()).getRevIdx();

        if (saveIdx != null) {
            Optional<Reserve> reserve = reserveRepository.findById(request.resIdx());
            reserve.ifPresent(
                    newRes -> {
                        newRes.setRevStatus(true);
                        reserveRepository.save(newRes);
                    }
            );
        }
        return saveIdx;
    }


    public Long saveFile(MultipartFile files, Long saveIdx) throws IOException {
        if (files.isEmpty()) {
            return null;
        }
        Long phIdx = 0L;
        String origName = files.getOriginalFilename();
        String uuid = UUID.randomUUID().toString();
        String extension = origName.substring(origName.lastIndexOf("."));
        String savedName = uuid + extension;
        // 파일을 불러올 때 사용할 파일 경로
        String savedPath = "D:\\test/" + savedName;
        files.transferTo(new File(savedPath));
        if (saveIdx != null) {
            System.out.println(saveIdx);
            ReviewPhotoDto reviewRequest = new ReviewPhotoRequest(origName, savedName, savedPath, saveIdx).toDto();
            phIdx = reviewPhotoRepository.save(reviewRequest.toEntity()).getPhIdx();
        }
        return phIdx;
    }
}
