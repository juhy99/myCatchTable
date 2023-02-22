package com.catchmind.catchtable.repository;

import com.catchmind.catchtable.domain.ShopResTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource
public interface ShopResTableRepository extends JpaRepository<ShopResTable,Long> {
    Optional<ShopResTable> findByTotalTable_TotTableIdAndShopResMonthAndShopResDayAndShopResTime(Long totalTableId, String month, String day, String time);

    List<ShopResTable> findByTotalTable_TotTableIdAndShopResMonthAndShopResDayAndShopResStatus(Long totalTableId, String month, String day,boolean resStatus);
}
