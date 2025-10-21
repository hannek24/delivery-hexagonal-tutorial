package com.tutorial.purchases.infra.purchases.models;

import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;

@Data
@Builder(toBuilder = true)
public class PurchaseRequestEntity {
    private String id;
    private String product;
    private String sku;
    private int quantity;
    private BigDecimal price;
}
