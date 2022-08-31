package com.alirizakocas.api.service.impl;


import com.alirizakocas.api.model.RecommendationResponseModel;

public interface IBrowsingHistoryService {
    RecommendationResponseModel getLastTenProductsViewed(String userId);
    boolean checkUserBrowsingHistory(String userId);
    Long deleteProductFromHistory(String userId, String productId);
}
