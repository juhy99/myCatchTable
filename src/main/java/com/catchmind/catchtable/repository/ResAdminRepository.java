package com.catchmind.catchtable.repository;

import com.catchmind.catchtable.domain.ResAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ResAdminRepository extends JpaRepository<ResAdmin, String> {
    Optional<ResAdmin> findAllByResaBisName(String resaBisName);
}
