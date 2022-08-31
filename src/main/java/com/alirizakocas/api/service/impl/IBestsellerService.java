package com.alirizakocas.api.service.impl;

import com.alirizakocas.api.model.RecommendationResponseModel;

public interface IBestsellerService {
    RecommendationResponseModel getBestsellers(String userId);
}
