package com.alirizakocas.api.service;

import com.alirizakocas.api.TestSupport;
import com.alirizakocas.api.entity.BrowsingHistoryEntity;
import com.alirizakocas.api.model.RecommendationResponseModel;
import com.alirizakocas.api.repository.BrowsingHistoryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class BrowsingHistoryServiceTest extends TestSupport {
	@InjectMocks
	private BrowsingHistoryService browsingHistoryService;
	@Mock
	private BrowsingHistoryRepository browsingHistoryRepository;

	@Test
	public void testGetLastTenProductsViewed() {
		String userId = "user-275";

		List<BrowsingHistoryEntity> repoResp = browsingHistoryRepository.findTop10ByUserIdOrderByOptimeDesc(userId);

		RecommendationResponseModel serviceResp = browsingHistoryService.getLastTenProductsViewed(userId);

		List<String> products = new ArrayList<>();

		repoResp.forEach(i -> products.add(i.getProductId()));

		assertLinesMatch(serviceResp.getProducts(), products);

		verify(browsingHistoryRepository).findTop10ByUserIdOrderByOptimeDesc(userId);
	}

	@Test
	public void testCheckBrowsingHistory() {
		String userId = "user-78";

		assertEquals(browsingHistoryRepository.findTopByUserId(userId).isPresent(),
				browsingHistoryService.checkUserBrowsingHistory(userId));

		verify(browsingHistoryRepository).findTopByUserId(userId);
	}

	@Test
	public void testDeleteProductFromHistory() {
		String userId = "user-dummy";
		String productId = "product-dummy";

		browsingHistoryService.deleteProductFromHistory(userId, productId);

		BrowsingHistoryEntity existRepo = null;

		Optional<BrowsingHistoryEntity> optionalEntity = browsingHistoryRepository.findByUserIdAndProductId(userId, productId);

		if(optionalEntity.isPresent()) {
			existRepo = optionalEntity.get();
		}

		Assertions.assertNull(existRepo);

		verify(browsingHistoryRepository).findByUserIdAndProductId(userId, productId);
	}

}
