package com.catchmind.catchtable.repository;

import com.catchmind.catchtable.domain.DeclareComment;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeclareCommentRepository extends JpaRepository<DeclareComment, Long> {
    List<DeclareComment> findAllByProfile_PrIdx(Long prIdx, Sort decIdx);
}
