package com.alirizakocas.api.service;

import com.alirizakocas.api.model.RecommendationResponseModel;
import com.alirizakocas.api.repository.BestsellerRepository;
import com.alirizakocas.api.service.impl.IBestsellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class BestsellerService implements IBestsellerService {

    private final BestsellerRepository bestsellerRepository;
    private final BrowsingHistoryService browsingHistoryService;

    @Autowired
    public BestsellerService(BestsellerRepository bestSellerRepository, BrowsingHistoryService browsingHistoryService) {
        this.bestsellerRepository = bestSellerRepository;
        this.browsingHistoryService = browsingHistoryService;
    }

    @Override
    public RecommendationResponseModel getBestsellers(String userId) {
        RecommendationResponseModel resp = new RecommendationResponseModel();
        List<String> productIds;

        resp.setUserId(userId);

        if(browsingHistoryService.checkUserBrowsingHistory(userId)) {
            productIds = bestsellerRepository.personalizedBestsellers(userId);
            resp.setType("personalized");
        } else {
            productIds = bestsellerRepository.nonPersonalizedBestsellers();
            resp.setType("non-personalized");
        }

        if(productIds.size() >= 5)
            resp.setProducts(productIds);
        else
            resp.setProducts(Collections.emptyList());

        return resp;
    }
}
