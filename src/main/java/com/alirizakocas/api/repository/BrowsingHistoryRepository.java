package com.alirizakocas.api.repository;

import com.alirizakocas.api.entity.BrowsingHistoryEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BrowsingHistoryRepository extends CrudRepository<BrowsingHistoryEntity, Long> {

    List<BrowsingHistoryEntity> findTop10ByUserIdOrderByOptimeDesc(String userId);
    Optional<BrowsingHistoryEntity> findTopByUserId(String userId);
    Long deleteByUserIdAndProductId(String userId, String productId);
    Optional<BrowsingHistoryEntity> findByUserIdAndProductId(String userId, String productId);
}
