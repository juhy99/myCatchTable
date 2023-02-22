package com.catchmind.catchtable.repository;

import com.catchmind.catchtable.domain.Facility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface FacilityRepository extends JpaRepository<Facility,Long> {
    List<Facility> findAllByResAdmin_ResaBisName(String resaBisName);
}
