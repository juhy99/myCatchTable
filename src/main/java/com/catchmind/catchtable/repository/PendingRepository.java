package com.catchmind.catchtable.repository;

import com.catchmind.catchtable.domain.Pending;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PendingRepository extends JpaRepository<Pending, Long> {
}
