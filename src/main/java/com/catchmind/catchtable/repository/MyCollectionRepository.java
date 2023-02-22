package com.catchmind.catchtable.repository;

import com.catchmind.catchtable.domain.MyCollection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface MyCollectionRepository extends JpaRepository<MyCollection,Long> {
    List<MyCollection> findAllByProfile_PrIdxAndColLock(Long prIdx,boolean colLock);
    List<MyCollection> findAllByProfile_PrIdx(Long prIdx);
    MyCollection findByColIdxAndProfile_PrIdx(Long colIdx, Long prIdx);
}
