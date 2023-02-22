package com.catchmind.catchtable.repository;

import com.catchmind.catchtable.domain.Ask;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AskRepository extends JpaRepository<Ask, Long> {

    Ask findByAskIdx(Long AskIdx);
    List<Ask> findAllByProfile_PrIdx(Long prIdx, Sort askIdx);

}
