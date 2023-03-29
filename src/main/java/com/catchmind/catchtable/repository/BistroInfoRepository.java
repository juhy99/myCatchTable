package com.catchmind.catchtable.repository;

import com.catchmind.catchtable.domain.BistroInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface BistroInfoRepository extends JpaRepository<BistroInfo, Long> {
    Page<BistroInfo> findByResAdmin_ResaBisNameContaining(String resaBisName, Pageable pageable);

}
