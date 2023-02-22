package com.catchmind.catchtable.service;


import com.catchmind.catchtable.dto.FacilityDto;
import com.catchmind.catchtable.repository.FacilityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FacilityLogicService {
    private final FacilityRepository facilityRepository;

    public List<FacilityDto> facilityList(String resaBisName){
        return facilityRepository.findAllByResAdmin_ResaBisName(resaBisName).stream().map(FacilityDto::from).toList();
    }
}

