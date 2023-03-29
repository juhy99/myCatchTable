package com.catchmind.catchtable.repository;

import com.catchmind.catchtable.domain.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource
public interface PhotoRepository extends JpaRepository <Photo,Long>{
    Optional<Photo> findByResAdmin_ResaBisName(String resaBisName);
}
