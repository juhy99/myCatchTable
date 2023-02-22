package com.catchmind.catchtable.repository;

import com.catchmind.catchtable.domain.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<Notice, Long> {
//    List<Notice> findAllByAdmin_AdName(String adName);
}
