package com.alirizakocas.api.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(schema = "public", name = "browsing_history")
public class BrowsingHistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "message_id", nullable = false, unique = true)
    private String messageId;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "product_id", nullable = false)
    private String productId;

    @Column(name = "source", nullable = false)
    private String source;

    @Column(name = "optime", nullable = false)
    private Timestamp optime;

    public BrowsingHistoryEntity() {
    }

    public BrowsingHistoryEntity(String messageId, String userId, String productId, String source, Timestamp optime) {
        this.messageId = messageId;
        this.userId = userId;
        this.productId = productId;
        this.source = source;
        this.optime = optime;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessageId() {
        return messageId;
    }

    public String getUserId() {
        return userId;
    }

    public String getProductId() {
        return productId;
    }

    public String getSource() {
        return source;
    }

    public Timestamp getOptime() {
        return optime;
    }
}