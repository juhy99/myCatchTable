package com.catchmind.catchtable.service;

import com.catchmind.catchtable.domain.BistroSave;
import com.catchmind.catchtable.domain.MyCollection;
import com.catchmind.catchtable.domain.Profile;
import com.catchmind.catchtable.domain.Sns;
import com.catchmind.catchtable.dto.*;
import com.catchmind.catchtable.dto.network.request.MyCollectionRequest;
import com.catchmind.catchtable.dto.network.request.SnsRequest;
import com.catchmind.catchtable.dto.network.response.ReviewResponse;
import com.catchmind.catchtable.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
@Service
@RequiredArgsConstructor
@Slf4j
public class ProfileLogicService {
    private final SnsRepository snsRepository;
    private final ProfileRepository profileRepository;
    private final BistroSaveRepository bistroSaveRepository;
    private final MyCollectionRepository myCollectionRepository;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final ReviewRepository reviewRepository;
    private final ReviewPhotoRepository reviewPhotoRepository;

    public boolean login(String prHp, String prUserpw) {
        ProfileDto profileDto = profileRepository.findByPrHp(prHp).map(ProfileDto::from).orElseThrow();
        boolean isTrue = passwordEncoder.matches(prUserpw, profileDto.prUserpw());
        System.out.println(isTrue);
        return isTrue;
    }

    public ProfileDto getProfileElements(Long prIdx) {
        return profileRepository.findById(prIdx)
                .map(ProfileDto::from)
                .orElseThrow(() -> new EntityNotFoundException("유저가 없는데여 - prIdx :" + prIdx));
    }

    public List<BistroSaveDto> getList(Long prIdx) {
        return bistroSaveRepository.findAllByProfile_PrIdx(prIdx).stream().map(BistroSaveDto::from).collect(Collectors.toList());
    }

    public List<BistroSaveDto> getSaveList(Long colIdx) {
        return bistroSaveRepository.findAllByColIdx(colIdx).stream().map(BistroSaveDto::from).collect(Collectors.toList());
    }

    public Profile saveMember(Profile profile) {
        System.out.println(profile);
        return profileRepository.save(profile);
    }

    public void updateProfile(Long prIdx, ProfileDto profileDto) {
        try {
            System.out.println(profileDto);
            Optional<Profile> profile = profileRepository.findById(prIdx);
            profile.ifPresent(
                    member -> {
                        if (profileDto.prUserpw() != null && profileDto.prUserpw() != "") {
                            member.setPrUserpw(passwordEncoder.encode(profileDto.prUserpw()));
                        }
                        if (profileDto.prHp() != null) {
                            member.setPrHp(profileDto.prHp());
                        }
                        if (profileDto.prGender() != null) {
                            member.setPrGender(profileDto.prGender());
                        }
                        if (profileDto.prIntro() != null) {
                            member.setPrIntro(profileDto.prIntro());
                        }
                        if (profileDto.prNick() != null) {
                            member.setPrNick(profileDto.prNick());
                        }
                        if (profileDto.prRegion() != null) {
                            member.setPrRegion(profileDto.prRegion());
                        }
                        if (profileDto.prName() != null) {
                            member.setPrName(profileDto.prName());
                        }
                    }
            );
        } catch (EntityNotFoundException e) {
            log.warn("업데이트 실패..!!!, 공지사항을 찾을 수 없음  - exNoticeDTO {} ", profileDto);
        }
    }
    // 비밀번호찾기 > 비밀번호 수정
    public void updatePassword(String prHp, ProfileDto profileDto) {
        try {
            System.out.println(profileDto);
            Optional<Profile> profile = profileRepository.findByPrHp(prHp);
            profile.ifPresent(
                    member -> {
                        if(profileDto.prUserpw() != null && profileDto.prUserpw() != "") {member.setPrUserpw(passwordEncoder.encode(profileDto.prUserpw()));}
                    }
            );
        } catch (EntityNotFoundException e) {
            log.warn("업데이트 실패..!!! ", profileDto);
        }
    }

    //    회원탈퇴
    public void delete(Long prIdx) {
        profileRepository.deleteById(prIdx);
    }

    //    컬렉션 삭제
    public void delMyCollection(Long colIdx) {
        myCollectionRepository.deleteById(colIdx);
    }

    public void delRes(Long saveIdx) {
        bistroSaveRepository.deleteById(saveIdx);
    }

    public void createCollection(MyCollectionRequest request) {
        System.out.println(request);
        System.out.println(request.toDto());
        System.out.println(request.toDto().toEntity());
        myCollectionRepository.save(request.toDto().toEntity());
    }

    public List<MyCollectionDto> getColList(Long prIdx) {
        return myCollectionRepository.findAllByProfile_PrIdx(prIdx).stream().map(MyCollectionDto::from).collect(Collectors.toList());
    }

    public MyCollectionDto getMyCollectionElements(Long colIdx) {
        return myCollectionRepository.findById(colIdx)
                .map(MyCollectionDto::from)
                .orElseThrow(() -> new EntityNotFoundException("유저가 없는데여 - prIdx :" + colIdx));
    }

    public void updateMyCollection(Long colIdx, MyCollectionDto myCollectionDto) {
        try {
            System.out.println(myCollectionDto);
            Optional<MyCollection> myCollection = myCollectionRepository.findById(colIdx);
            myCollection.ifPresent(
                    collection -> {
                        if (myCollectionDto.colTitle() != null) {
                            collection.setColTitle(myCollectionDto.colTitle());
                        }
                        if (myCollectionDto.colContent() != null) {
                            collection.setColContent(myCollectionDto.colContent());
                        }
                        if(myCollectionDto.colLock() != true) {collection.setColLock(false);}
                        if(myCollectionDto.colLock() != false) {collection.setColLock(true);}
                    }
            );
        } catch (EntityNotFoundException e) {
            log.warn("업데이트 실패..!!!, 공지사항을 찾을 수 없음  - myCollectionDTO {} ", myCollectionDto);
        }
    }

    public MyCollection updateMyCollectionSave(Long colIdx, String bisNames) {
        MyCollection myCollection = myCollectionRepository.findById(colIdx).orElse(null);
//        MyCollection myCollection = myCollectionRepository.findByColIdxAndProfile_PrIdx(colIdx, prIdx);
        myCollection.setBisNames(bisNames);
        return myCollectionRepository.save(myCollection);
    }

    @Transactional
    public void updateBistroSave(Long colIdx, String bisNames, Long prIdx) {
        String[] saveIdx = bisNames.split(",");
        for (String idx : saveIdx) {
            System.out.println(idx);
            BistroSave bistroSave = bistroSaveRepository.findByResAdmin_ResaBisNameAndProfile_PrIdx(idx, prIdx);
            System.out.println("🐒" + bistroSave);
            bistroSave.setColIdx(colIdx);
//            bistroSaveRepository.save(bistroSave);
        }
    }

    public BistroSave delCollectionSave(Long saveIdx) {
        BistroSave bistroSave = bistroSaveRepository.findById(saveIdx).orElse(null);
        bistroSave.setColIdx(null);
        return bistroSaveRepository.save(bistroSave);
    }


    public Optional<Profile> checkNick(String prNick) {
        Optional<Profile> profile = profileRepository.findByPrNick(prNick);
        return profile;
    }
    // 비밀번호 찾기
    public Optional<Profile> findPw(String prHp, String prName){
        Optional<Profile> profile = profileRepository.findByPrHpAndPrName(prHp,prName);
        return profile;
    }

    //sns추가
    public String saveSNS(SnsRequest snsRequest, Long prIdx, String arr1, String arr2) {
        SnsDto newSns = snsRequest.of(prIdx, arr1, arr2).toDto();
        snsRepository.save(newSns.toEntity());
        return null;
    }
    // sns업데이트
    public void snsUpdate(Long prIdx, String snsType, String snsAddr){
        try {
            Optional<Sns> sns = snsRepository.findByProfile_PrIdxAndSnsType(prIdx, snsType);
            sns.ifPresent(
                    collection -> {
                        collection.setSnsAddr(snsAddr);
                    }
            );
        } catch (EntityNotFoundException e) {
            log.warn("업데이트 실패..!!!, 공지사항을 찾을 수 없음  - myCollectionDTO {} ", prIdx);
        }

    }

    public Page<ReviewResponse> getReview(Long prIdx, Pageable pageable) {
        List<ReviewResponse> reviewList = new ArrayList<>();
        List<ReviewDto> reviewDtos = reviewRepository.findAllByProfile_PrIdx(prIdx,Sort.by(Sort.Direction.DESC, "revIdx")).stream().map(ReviewDto::from).toList();
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

            if (photoList.isEmpty() || reviewDtos.get(i).updateDate() == null) {
                ReviewResponse response = new ReviewResponse(reviewDtos.get(i).revIdx(), reviewDtos.get(i).profileDto(), reviewDtos.get(i).revContent(), reviewDtos.get(i).revScore(),
                        reviewDtos.get(i).resAdminDto(), null, reviewDtos.get(i).reserveDto().resIdx(),
                        reviewDtos.get(i).regDate(), null, true);
                reviewList.add(response);
            } else {
                ReviewResponse response = new ReviewResponse(reviewDtos.get(i).revIdx(), reviewDtos.get(i).profileDto(), reviewDtos.get(i).revContent(), reviewDtos.get(i).revScore(),
                        reviewDtos.get(i).resAdminDto(), photoList, reviewDtos.get(i).reserveDto().resIdx(),
                        reviewDtos.get(i).regDate()
                        , reviewDtos.get(i).updateDate(), true);
                reviewList.add(response);
            }
        }

        final int start = (int) pageable.getOffset();
        final int end = Math.min((start + pageable.getPageSize()), reviewList.size());
        PageImpl<ReviewResponse> reviewResponsePage = new PageImpl<>(reviewList.subList(start, end), pageable, reviewList.size());
        return reviewResponsePage;
    }
}
