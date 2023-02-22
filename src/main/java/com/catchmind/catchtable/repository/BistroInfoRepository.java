package com.catchmind.catchtable.repository;

import com.catchmind.catchtable.domain.BistroInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource
public interface BistroInfoRepository extends JpaRepository<BistroInfo, Long> {
    Optional<BistroInfo> findByResAdmin_ResaBisName(String resaBisName);
    Page<BistroInfo> findByResAdmin_ResaBisNameContaining(String resaBisName, Pageable pageable);

    List<BistroInfo> findAllByBisCategoryContaining(String bisCategory);

    List<BistroInfo> findAllByBisRegionContaining(String bisRegion);

    Page<BistroInfo> findAllByBisRegionContaining(String bisRegion,Pageable pageable);

    Page<BistroInfo> findAllByBisCategoryContaining(String bisCategory,Pageable pageable);
}
