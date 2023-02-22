package com.catchmind.catchtable.service;

import com.catchmind.catchtable.domain.Pending;
import com.catchmind.catchtable.dto.PendingDto;
import com.catchmind.catchtable.repository.PendingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j  // 로그를 찍기 위해서 사용하는 어노테이션
@Transactional
@RequiredArgsConstructor
@Service
public class PendingService {
    private final PendingRepository pendingRepository;

    public Pending createResAdmin(PendingDto pendingDto) {
        return pendingRepository.save(pendingDto.toPendingEntity());
    }
}
