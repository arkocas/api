package com.alirizakocas.api.repository;

import com.alirizakocas.api.entity.BestsellerEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BestsellerRepository extends CrudRepository<BestsellerEntity, Long> {

    @Query(value = "select b.product_id as productId from bestseller as b, products as p \n" +
            "where b.product_id = p.product_id \n" +
            "and p.category_id in \n" +
            "(select popularCategories.category_id \n" +
            "from \n" +
            "(select p.category_id, count(*) \n" +
            "from browsing_history as bh, products as p \n" +
            "where bh.product_id = p.product_id \n" +
            "and bh.user_id = ?1 \n" +
            "group by p.category_id \n" +
            "order by count(*) desc limit 3) as popularCategories) \n" +
            "order by b.quantity desc limit 10;", nativeQuery = true)
    List<String> personalizedBestsellers(String userId);

    @Query(value = "select b.product_id as productId from bestseller as b, products as p \n" +
            "where b.product_id = p.product_id \n" +
            "order by b.quantity desc limit 10;", nativeQuery = true)
    List<String> nonPersonalizedBestsellers();
}
