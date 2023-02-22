package com.catchmind.catchtable.repository;

import com.catchmind.catchtable.domain.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource
public interface ProfileRepository extends JpaRepository<Profile, Long> {
    Optional<Profile> findByPrHp(String prHp);

    Optional<Profile> findByPrHpAndPrUserpw(String prHp, String prUserpw);

    Optional<Profile> findByPrNick(String prNick);
    Optional<Profile> findByPrHpAndPrName(String prHp,String prName);
}
