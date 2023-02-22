package com.catchmind.catchtable.service;

import com.catchmind.catchtable.dto.BisNoticeDto;
import com.catchmind.catchtable.repository.BisNoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BisNoticeLogicService {
    private final BisNoticeRepository bisNoticeRepository;

    public List<BisNoticeDto> noticeList(String resaBisName) {
        return bisNoticeRepository.findAllByResAdmin_ResaBisName(resaBisName)
                .stream().map(BisNoticeDto::from).toList();
    }
}
