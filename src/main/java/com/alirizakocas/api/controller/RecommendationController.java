package com.alirizakocas.api.controller;

import com.alirizakocas.api.model.RecommendationResponseModel;
import com.alirizakocas.api.service.impl.IBrowsingHistoryService;
import com.alirizakocas.api.service.impl.IBestsellerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class RecommendationController {

    private final IBrowsingHistoryService browsingHistoryService;
    private final IBestsellerService bestsellerService;

    public RecommendationController(IBrowsingHistoryService browsingHistoryService, IBestsellerService bestsellerService) {
        this.browsingHistoryService = browsingHistoryService;
        this.bestsellerService = bestsellerService;
    }

    @GetMapping("/getBrowsingHistory")
    public @ResponseBody RecommendationResponseModel getLastTenProductsViewed(@RequestParam("user-id") String userId) {
        return browsingHistoryService.getLastTenProductsViewed(userId);
    }

    @GetMapping("/getBestsellers")
    public @ResponseBody RecommendationResponseModel getBestSellers(@RequestParam("user-id") String userId) {
        return bestsellerService.getBestsellers(userId);
    }

    @DeleteMapping("/deleteProductFromHistory")
    public ResponseEntity<Void> deleteProductFromHistory(@RequestParam("user-id") String userId, @RequestParam("product-id") String productId) {
        Long response = browsingHistoryService.deleteProductFromHistory(userId, productId);
        if(response == 1L)
            return ResponseEntity.ok().build();
        else
            return ResponseEntity.noContent().build();
    }
}
