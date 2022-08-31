package com.alirizakocas.api.model;

import java.io.Serializable;
import java.util.List;

public class RecommendationResponseModel implements Serializable {
    private String userId;
    private List<String> products;
    private String type;

    public RecommendationResponseModel() {
    }

    public RecommendationResponseModel(String userId, List<String> products, String type) {
        this.userId = userId;
        this.products = products;
        this.type = type;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<String> getProducts() {
        return products;
    }

    public void setProducts(List<String> products) {
        this.products = products;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
