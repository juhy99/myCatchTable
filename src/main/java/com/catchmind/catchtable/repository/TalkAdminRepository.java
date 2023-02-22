package com.catchmind.catchtable.repository;

import com.catchmind.catchtable.domain.TalkAdmin;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface TalkAdminRepository extends JpaRepository<TalkAdmin, Long> {

    List<TalkAdmin> findAllByProfile_PrIdx(Long prIdx, Sort taaIdx);
}
