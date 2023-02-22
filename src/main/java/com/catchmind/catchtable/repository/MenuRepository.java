package com.catchmind.catchtable.repository;


import com.catchmind.catchtable.domain.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface MenuRepository extends JpaRepository<Menu,Long> {
    List<Menu> findAllByResAdmin_ResaBisName(String resaBisName);


}
