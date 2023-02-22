package com.catchmind.catchtable.service;

import com.catchmind.catchtable.domain.BistroSave;
import com.catchmind.catchtable.dto.BistroDetailDto;
import com.catchmind.catchtable.dto.network.request.BistroSaveRequest;
import com.catchmind.catchtable.repository.BistroDetailRepository;
import com.catchmind.catchtable.repository.BistroSaveRepository;
import com.catchmind.catchtable.repository.ProfileRepository;
import com.catchmind.catchtable.repository.ResAdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BistroSaveService {
    private final BistroSaveRepository bistroSaveRepository;
    private final BistroDetailRepository bistroDetailRepository;
    private final ProfileRepository profileRepository;
    private final ResAdminRepository resAdminRepository;


    // 북마크 저장 create
    public BistroSave newBookmark(BistroSaveRequest request) {
        BistroDetailDto bistroDetailDto = bistroDetailRepository.findById(request.bdIdx()).map(BistroDetailDto::from).orElseThrow();
        String resaBisName = bistroDetailDto.resAdminDto().resaBisName();
        BistroSaveRequest saveRequest = new BistroSaveRequest(request.saveIdx(), resaBisName, request.prIdx(), request.bdIdx(), null);
        return bistroSaveRepository.save(saveRequest.toDto().toEntity());
    }

    // 북마크 삭제
    @Transactional
    public Optional<BistroSave> delBookmark(BistroSaveRequest request){
        System.out.println("서비스 진입");
        return bistroSaveRepository.deleteByProfile_PrIdxAndBistroDetail_BdIdx(request.prIdx(), request.bdIdx());
    }

}
