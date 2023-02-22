package com.catchmind.catchtable.repository;

import com.catchmind.catchtable.domain.Sns;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource
public interface SnsRepository extends JpaRepository<Sns, Long> {
    List<Sns> findAllByProfile_PrIdx(Long prIdx);
    Optional<Sns> findByProfile_PrIdxAndSnsType(Long prIdx, String snsType);
}
