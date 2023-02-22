package com.catchmind.catchtable.repository;

import com.catchmind.catchtable.domain.TotalTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource
public interface TotalTableRepository extends JpaRepository<TotalTable,Long> {
    Optional<TotalTable> findByResAdmin_ResaBisName(String resaBisName);
}
