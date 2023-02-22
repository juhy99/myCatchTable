package com.catchmind.catchtable.service;


import com.catchmind.catchtable.domain.BistroInfo;
import com.catchmind.catchtable.dto.BistroInfoDto;
import com.catchmind.catchtable.dto.ReviewDto;
import com.catchmind.catchtable.dto.network.response.ShopReviewResponse;
import com.catchmind.catchtable.repository.BistroInfoRepository;
import com.catchmind.catchtable.repository.PhotoRepository;
import com.catchmind.catchtable.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class  BistroInfoLogicService {
    private final BistroInfoRepository bistroInfoRepository;
    private final PhotoRepository photoRepository;
    private final ReviewRepository reviewRepository;

    public BistroInfoDto infoList(String resaBisName) {
        return bistroInfoRepository.findByResAdmin_ResaBisName(resaBisName).map(BistroInfoDto::from).orElseThrow();
    }

    public Page<BistroInfoDto> shopCategoryList(String bisCategory,Pageable pageable) {
        //List<PhotoDto> photoDtos = photoRepository.findAll().stream().map(PhotoDto::from).toList();
        //컬럼 추가 안하고싶을때  //Page 타입은 .map까지만
//        double total = 0;
//        double avg = 0;
//        List<ReviewDto> reviewDtos = new ArrayList<>();
        List<BistroInfoDto> bistroInfoDtos = bistroInfoRepository.findAllByBisCategoryContaining(bisCategory).stream().map(BistroInfoDto::from).toList();
        List<BistroInfoDto> bistroInfoDtoList=null;
//        for(BistroInfoDto bistroInfoDto:bistroInfoDtos){
//            BistroInfo bistroInfo = (BistroInfo)bistroInfoDto;
//            bistroInfoDtoList.add(BistroInfoDto.from(bistroInfoDto,reviewRepository.findAllByResAdmin_ResaBisName(bistroInfoDto.resAdminDto().resaBisName()).stream().toList()));
//        }

        Page<BistroInfoDto> page = new PageImpl<>(bistroInfoDtos);

//        for(int i = 0; i < bistroInfoDtos.size(); i++) {
//            reviewDtos = reviewRepository.findAllByResAdmin_ResaBisName
//                    (bistroInfoDtos.get(i).resAdminDto().resaName()).stream().map(ReviewDto::from).toList();
//            total += reviewDtos.get(i).revScore();
//        }
//        avg = total / reviewDtos.size();




//        final int start = (int) pageable.getOffset();
//        final int end = Math.min((start + pageable.getPageSize()), reviewDtos.size());
//        PageImpl<ReviewDto> reviewResponse = new PageImpl<>(reviewDtos.subList(start, end), pageable, reviewDtos.size());
//        return reviewResponse;
        return page;

    }

    public Page<BistroInfoDto> shopRegionList(String bisRegion,Pageable pageable) {
        //List<PhotoDto> photoDtos = photoRepository.findAll().stream().map(PhotoDto::from).toList();
        //컬럼 추가 안하고싶을때  //Page 타입은 .map까지만
//        double total = 0;
//        double avg = 0;
//        List<ReviewDto> reviewDtos = new ArrayList<>();
        List<BistroInfoDto> bistroInfoDtos = bistroInfoRepository.findAllByBisRegionContaining(bisRegion).stream().map(BistroInfoDto::from).toList();
        List<BistroInfoDto> bistroInfoDtoList=null;
//        for(BistroInfoDto bistroInfoDto:bistroInfoDtos){
//            BistroInfo bistroInfo = (BistroInfo)bistroInfoDto;
//            bistroInfoDtoList.add(BistroInfoDto.from(bistroInfoDto,reviewRepository.findAllByResAdmin_ResaBisName(bistroInfoDto.resAdminDto().resaBisName()).stream().toList()));
//        }

        Page<BistroInfoDto> page = new PageImpl<>(bistroInfoDtos);

//        for(int i = 0; i < bistroInfoDtos.size(); i++) {
//            reviewDtos = reviewRepository.findAllByResAdmin_ResaBisName
//                    (bistroInfoDtos.get(i).resAdminDto().resaName()).stream().map(ReviewDto::from).toList();
//            total += reviewDtos.get(i).revScore();
//        }
//        avg = total / reviewDtos.size();




//        final int start = (int) pageable.getOffset();
//        final int end = Math.min((start + pageable.getPageSize()), reviewDtos.size());
//        PageImpl<ReviewDto> reviewResponse = new PageImpl<>(reviewDtos.subList(start, end), pageable, reviewDtos.size());
//        return reviewResponse;
        return page;

    }


    public List<BistroInfo> shopCountList(String bisCategory) {
        List<BistroInfo> bistroInfoDtos = bistroInfoRepository.findAllByBisCategoryContaining(bisCategory);
        return bistroInfoDtos;
    }

    public List<BistroInfo> shopRegionCountList(String bisRegion) {
        List<BistroInfo> bistroInfoDtos = bistroInfoRepository.findAllByBisRegionContaining(bisRegion);
        return bistroInfoDtos;
    }

    public Page<BistroInfo> searchList(String resaBisName, Pageable pageable) {
        return bistroInfoRepository.findByResAdmin_ResaBisNameContaining(resaBisName,pageable);
    }






}


