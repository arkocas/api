package com.alirizakocas.api;

import com.alirizakocas.api.entity.BrowsingHistoryEntity;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TestSupport {

    public static List<BrowsingHistoryEntity> dummyBrowsingHistory(String userId) {
        return IntStream.range(0, 5).mapToObj(i ->
             new BrowsingHistoryEntity(
                     "message-dummy" + i,
                     userId,
                     "product-dummy" + i,
                     "web",
                    new Timestamp(System.currentTimeMillis()))).collect(Collectors.toList());

    }

    public static BrowsingHistoryEntity dummyBrowsingHistoryItem(String userId) {
        return new BrowsingHistoryEntity(
                        "message-dummy",
                        userId,
                        "product-dummy",
                        "web",
                        new Timestamp(System.currentTimeMillis()));

    }
}
