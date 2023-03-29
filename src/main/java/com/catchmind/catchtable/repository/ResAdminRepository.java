package com.catchmind.catchtable.repository;

import com.catchmind.catchtable.domain.ResAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResAdminRepository extends JpaRepository<ResAdmin, String> {
}
