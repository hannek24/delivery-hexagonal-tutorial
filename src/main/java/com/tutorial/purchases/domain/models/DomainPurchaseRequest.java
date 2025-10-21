package com.tutorial.purchases.domain.models;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class DomainPurchaseRequest {
    private String id;
    private String product;
    private String sku;
    private int quantity;
    private BigDecimal price;
}
