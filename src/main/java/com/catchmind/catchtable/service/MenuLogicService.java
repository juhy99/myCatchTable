package com.catchmind.catchtable.service;

import com.catchmind.catchtable.dto.MenuDto;
import com.catchmind.catchtable.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuLogicService {
    private final MenuRepository menuRepository;

    public List<MenuDto> menuList(String resaBisName){
        return menuRepository.findAllByResAdmin_ResaBisName(resaBisName).stream().map(MenuDto::from).toList();

    }

}
