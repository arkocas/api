package com.alirizakocas.api.entity;

import javax.persistence.*;

@Entity
@Table(schema = "public", name = "bestseller")
public class BestsellerEntity {
    @Id
    @Column(name = "product_id", nullable = false, unique = true)
    private String productId;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
