package com.catchmind.catchtable.repository;

import com.catchmind.catchtable.domain.BisNotice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface BisNoticeRepository extends JpaRepository<BisNotice,Long> {
    List<BisNotice> findAllByResAdmin_ResaBisName(String resaBisName);
}
