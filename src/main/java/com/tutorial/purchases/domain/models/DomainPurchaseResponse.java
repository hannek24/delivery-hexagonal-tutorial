package com.tutorial.purchases.domain.models;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class DomainPurchaseResponse {
    private String id;
    private BigDecimal price;
}
