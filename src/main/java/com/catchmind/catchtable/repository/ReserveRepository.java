package com.catchmind.catchtable.repository;

import com.catchmind.catchtable.domain.Reserve;
import com.catchmind.catchtable.domain.type.ReservationType;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface ReserveRepository extends JpaRepository<Reserve, Long> {

    List<Reserve> findAllByresStatusAndProfile_PrIdx(ReservationType resStatus, Long prIdx, Sort updateDate);
    List<Reserve> findAllByresStatus(ReservationType resStatus);

    Reserve findByResIdx(Long resIdx);

    List<Reserve> findAllByResMonthAndResDayAndResAdmin_ResaBisName(String month, String day, String resaBisName);

}
