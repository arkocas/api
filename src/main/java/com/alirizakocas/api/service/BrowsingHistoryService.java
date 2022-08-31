package com.alirizakocas.api.service;

import com.alirizakocas.api.entity.BrowsingHistoryEntity;
import com.alirizakocas.api.model.RecommendationResponseModel;
import com.alirizakocas.api.repository.BrowsingHistoryRepository;
import com.alirizakocas.api.service.impl.IBrowsingHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BrowsingHistoryService implements IBrowsingHistoryService {

    private final BrowsingHistoryRepository browsingHistoryRepository;

    @Autowired
    public BrowsingHistoryService(BrowsingHistoryRepository browsingHistoryRepository) {
        this.browsingHistoryRepository = browsingHistoryRepository;
    }

    @Override
    public RecommendationResponseModel getLastTenProductsViewed(String userId) {
        List<BrowsingHistoryEntity> historyList = browsingHistoryRepository.findTop10ByUserIdOrderByOptimeDesc(userId);

        List<String> productIds = new ArrayList<>();

        historyList.stream().forEach(i -> productIds.add(i.getProductId()));

        return new RecommendationResponseModel(userId, productIds, "personalized");
    }

    @Override
    public boolean checkUserBrowsingHistory(String userId) {
        Optional<BrowsingHistoryEntity> optionalEntity = browsingHistoryRepository.findTopByUserId(userId);

        return optionalEntity.isPresent();
    }

    @Override
    public Long deleteProductFromHistory(String userId, String productId) {
        return browsingHistoryRepository.deleteByUserIdAndProductId(userId, productId);
    }
}
